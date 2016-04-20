package com.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.baiyi.order.model.Cashbox;
import com.baiyi.order.util.FormatUtil;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class ReflectTest {

	public static void main(String[] args) {
		reflect2();
	}

	public static void reflect1() {
		String params = "{nd100100:55,nv9tw100:83}";

		JSONObject json = JSONObject.fromObject(params);
		System.out.println(json);
		Cashbox cashbox = new Cashbox();

		Method[] methods = cashbox.getClass().getDeclaredMethods();
		for (Method method : methods) {
			String name = method.getName();
			if (name.matches("^set(Nd100|Nv9|Hopper)\\S+$")) {
				String key = FormatUtil.getField(name);
				int count = 0;
				try {
					count = json.getInt(key);
				} catch (JSONException e) {
					// e.printStackTrace();
					// jsonData.put("warning", key + "参数错误");
					continue;// 忽视?
				}
				System.out.printf("%s %d\n", key, count);
				try {
					method.invoke(cashbox, count);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println(JSONObject.fromObject(cashbox));
	}

	public static void reflect2() {
		Cashbox cashbox = new Cashbox();
		String params = "hopper/0.1-100,0.5-10;nd100/10-100;nv9/1-35";

		String language = "tw";
		if (params.indexOf("nd100/10-") != -1 || params.indexOf("nv9/1-") != -1 || params.indexOf("hopper/0.1-") != -1) {
			language = "cn";
		}

		String[] types = params.split(";");
		for (String type : types) {
			String[] machine_maps = type.split("/");
			String machine = machine_maps[0] + language;
			String[] maps = machine_maps[1].split(",");
			for (String map : maps) {
				String[] value_count = map.split("-");
				String key = machine + value_count[0];
				int count = Integer.parseInt(value_count[1]);

				key = key.replace(".", "");

				String methodName = FormatUtil.getMethod(key, "set");
				System.out.println(key + "-" + count + ":" + methodName);
				try {
					Method method = Cashbox.class.getMethod(methodName, int.class);
					method.invoke(cashbox, count);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(JSONObject.fromObject(cashbox));
	}

}
