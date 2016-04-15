package com.baiyi.order.util;

public class RandomUtil {

	public static int randomInteger(int min, int max) {
		// SecureRandom random = new SecureRandom();
		return (int) (Math.random() * (max - min + 1)) + min;
	}

	public static double randomDouble(int min, int max) {
		return Math.random() * (max - min + 1) + min;
	}

	public static char randomChar() {
		String charStr = "abcdefghijklmnopqrstuvwxyz";
		return charStr.charAt((int) (Math.random() * 26));
	}

	public static String randomString(int length) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < length; i++) {
			s.append(randomChar());
		}
		return s.toString();
	}
}
