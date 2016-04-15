package com.baiyi.order.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.baiyi.order.model.User;
import com.baiyi.order.service.TerminalService;
import com.baiyi.order.service.UserService;
import com.baiyi.order.vo.Record;

@Component
public class WebContext implements ServletContextAware, InitializingBean {

	/* DI */
	@Resource
	private UserService userService;
	@Resource
	private TerminalService terminalService;

	/* 系统配置 */
	public final static String UPLOAD = "upload";// 上传素材目录
	public final static String FFMPEG = "tool" + File.separator + "convert" + File.separator + "ffmpeg.exe";// ffmpeg
	public final static String MENCODER = "tool" + File.separator + "convert" + File.separator + "mencoder.exe";// mencoder
	public final static String CAPTURE = "capture";// 上传截图路径
	public final static String TEMP = "temp";// 临时目录

	/* 初始化数据 */
	public static String os;// 系统
	public static String webRootPath;// 项目根目录
	public static String classRootPath;// 项目根目录
	public static String version;// 版本
	public static String serverid;// 授权码
	public static String mirror;// 授权地址

	public static boolean empower = false;// 是否取得授权
	public static int maxCount = 0;// 最大连接数
	public static boolean isDog = false;// 是否有加密狗(未启用)

	/* 容器全局变量 */
	public final static Map<String, Record> ConnectMap = new HashMap<>();// 终端连线key:terminalNo
	// 远程控制
	public final static Map<String, Boolean> checkTimeMap = new HashMap<>();
	public final static Map<String, Boolean> rebootMap = new HashMap<>();
	public final static Map<String, Boolean> shutDownMap = new HashMap<>();
	public final static Map<String, Boolean> bootTeamViewer = new HashMap<>();
	public final static Map<String, Boolean> closeTeamViewer = new HashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		// OS
		os = System.getProperty("os.name");
		System.out.println(os);
		if (os.toLowerCase().startsWith("windows")) {
			os = "windows";
		} else if (os.toLowerCase().startsWith("linux")) {
			os = "linux";
		}

		// webRootPath
		if (servletContext != null) {
			webRootPath = servletContext.getRealPath("/");
		}

		// classRootPath = rootPath + "WEB-INF" + File.separator + "classes";
		classRootPath = this.getClass().getClassLoader().getResource("").getFile();
		System.out.println("webRootPath: " + webRootPath + " ,classRootPath: " + classRootPath);

		// read prop
		Configuration config = new PropertiesConfiguration("config.properties");
		mirror = config.getString("mirror");
		version = config.getString("version");
		serverid = config.getString("serverid");
		servletContext.setAttribute("version", version);
		servletContext.setAttribute("serverid", serverid);
		System.out.println("mirror: " + mirror);
		System.out.println("version: " + version);
		System.out.println("serverid: " + serverid);

		// empower
		Thread authorize = new Thread(new Authorize());
		authorize.start();

		// base user
		User user = userService.find("root");
		if (user == null) {
			user = new User();
			user.setName("root");
			user.setPassword(DESPlus.digest("root"));
			userService.save(user);
		}

		// TODO:测试
		// Record record = new Record();
		// Terminal t = terminalService.find(1);
		// record.setTerminalId(t.getId());
		// record.setTerminalNo(t.getTerminalNo());
		// record.setLocation(t.getLocation());
		// record.setIp("127.0.0.1");
		// record.setOnline(true);
		// record.setImage("abc");
		// record.setDate(new Date());

		// WebContext.ConnectMap.put(t.getTerminalNo(), record);
	}

	private ServletContext servletContext;

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
