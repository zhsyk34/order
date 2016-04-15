package com.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.baiyi.order.model.Cashbox;
import com.baiyi.order.util.FormatUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONTest {

	public static void main(String[] args) throws Exception {
		// str2obj();
		reflect2();

	}

	@SuppressWarnings("rawtypes")
	public static void reflect() {
		Cashbox cashbox = new Cashbox();
		Class clazz = cashbox.getClass();
		Method[] ms = clazz.getDeclaredMethods();
		for (Method m : ms) {
			System.out.println(m.getName());

		}
		Field[] fs = clazz.getDeclaredFields();

		int count = 0;
		for (Field f : fs) {
			String fname = f.getName();
			if (fname.matches("^(nv9|hopper|nd100)\\S+$")) {
				System.out.println(fname);
				String s = "set" + Character.toUpperCase(fname.charAt(0)) + fname.substring(1);

				System.out.println(s);
				count++;
			} else {
				System.out.println("========" + fname);
			}

		}
		System.out.printf("filed count: %d filter count: %d", fs.length, count);
	}

	@SuppressWarnings("rawtypes")
	public static void reflect2() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Cashbox cashbox = new Cashbox();
		Class clazz = cashbox.getClass();
		Method[] ms = clazz.getDeclaredMethods();

		int i = 1;
		for (Method method : ms) {
			String name = method.getName();
			if (name.matches("^set(Nd100|Nv9|Hopper)\\S+$")) {
				String field = FormatUtil.getField(name);
				System.out.println(field);

				method.invoke(cashbox, i++);
			}
		}

		System.out.println(JSONObject.fromObject(cashbox));
	}

	public static void str2obj() {
		String params = "{terminalId:'tno10004',nd100tw100:55}";
		JSONObject json = JSONObject.fromObject(params);
		System.out.println(json);
		System.out.println(json.getString("nd100tw100"));

		try {
			String a = json.getString("a");
			System.out.println(a);
		} catch (Exception e) {
		}

		System.out.println(json.getString("terminalId"));
	}

	public static void str2arr() {
		String params = "[{terminalId:'tno10004',foodId:4,send:99},{terminalId:'tno10004',foodId:5,send:124}]";

		JSONArray array = JSONArray.fromObject(params);
		System.out.println(array);

		for (int i = 0; i < array.size(); i++) {
			JSONObject json = array.getJSONObject(i);
			// System.out.println(json);

			String terminalId = json.getString("terminalId");
			Integer foodId = json.getInt("foodId");
			int send = json.getInt("send");

			System.out.printf("%s %d %d\n", terminalId, foodId, send);
		}
	}

}
