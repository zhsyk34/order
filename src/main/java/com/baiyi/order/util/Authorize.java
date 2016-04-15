package com.baiyi.order.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

public class Authorize implements Runnable {

	public void run() {
		while (true) {
			// WebContext.empower = StringUtils.isNotBlank(WebContext.serverid)
			// && confirm();
			if (StringUtils.isNotBlank(WebContext.serverid)) {
				WebContext.empower = confirm();
				System.out.println(WebContext.empower ? "===confirm success===" : "===confirm fail===");
			} else {
				WebContext.empower = false;
				System.out.println("===confirm error===");
			}
			try {
				Thread.sleep(30 * 60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean confirm() {
		boolean flag = false;

		String path = WebContext.classRootPath + File.separator + "authorize.properties";
		PropertiesConfiguration config = null;

		// result/prop key
		String serverid = null;
		String count = null;
		String success = null;
		String isOpen = null;

		File file = new File(path);

		try {
			config = new PropertiesConfiguration(path);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		if (file.exists()) {
			try {
				serverid = config.getString("serverid");
				String maxCount = config.getString("maxCount");
				if (StringUtils.isNotBlank(maxCount)) {
					count = DESPlus.decrypt(maxCount);// 加密过
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (WebContext.serverid.equals(serverid)) {
				flag = true;
				WebContext.maxCount = Integer.parseInt(count);
			}
		} else {
			file.mkdirs();
			try {
				String param = "serverid=" + URLEncoder.encode(WebContext.serverid, "UTF-8") + "&type=1";
				String response = HttpSend.sendRequest(WebContext.mirror, param);
				// {"terminalNum":"069726005eaf18c1","serverid":"AL0001","success":true,"end":"4ca2ab63835298d6caa441dc77dd93ab","begin":"4ca2ab63835298d66b5d676ed0fb4c30"}
				JSONObject json = JSONObject.fromObject(response);
				success = json.getString("success");
				isOpen = json.getString("isOpen");

				if ("true".equals(success) && "true".equals(isOpen)) {
					flag = true;
					serverid = json.getString("serverId");
					count = json.getString("terminalNum");

					try {
						config.setProperty("serverid", serverid);
						config.setProperty("maxCount", count);
						config.save();
					} catch (ConfigurationException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
}
