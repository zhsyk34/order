package com.baiyi.order.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

//数据加密解密
public class DESPlus {

	private static final String BASE_SEED = "national";

	// 信息摘要算法(指定算法),不可逆加密
	public static String digest(String info, String algorithm) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] src = null;
		try {
			src = info.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		messageDigest.update(src);
		byte[] dest = messageDigest.digest();
		// return Hex.encodeHexString(dest);
		return formatBytes(dest);
	}

	// 采用默认SHA-1算法进行不可逆加密
	public static String digest(String info) {
		return digest(info, "SHA-1");
	}

	// 可逆加密(指定seed)
	public static String encrypt(String str, String seed) throws Exception {
		Cipher cipher = getInstance(seed, Cipher.ENCRYPT_MODE);
		byte[] result = cipher.doFinal(str.getBytes("UTF-8"));
		return result == null ? null : byte2Hex(result);
	}

	// 默认seed进行加密
	public static String encrypt(String str) throws Exception {
		return encrypt(str, null);
	}

	// 解密
	public static String decrypt(String str, String seed) throws Exception {
		Cipher cipher = getInstance(seed, Cipher.DECRYPT_MODE);
		byte[] result = cipher.doFinal(hex2Byte(str));
		return result == null ? null : new String(result);
	}

	// 默认seed解密
	public static String decrypt(String str) throws Exception {
		return decrypt(str, null);
	}

	// 获取加密 解密算法
	private static Cipher getInstance(String seed, int type) throws Exception {
		seed = StringUtils.isBlank(seed) ? BASE_SEED : seed;
		Key key = new SecretKeySpec(cut(seed.getBytes("UTF-8")), "DES");

		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(type, key);

		return cipher;
	}

	// 格式化字节数组
	private static String formatBytes(byte[] bytes) {
		String result = "";
		for (int n = 0, length = bytes.length; n < length; n++) {
			String temp = Integer.toHexString(bytes[n] & 0xFF);
			if (temp.length() == 1) {
				temp = "0" + temp;
			}
			if (n < length - 1) {
				temp = temp + ":";
			}
			result += temp.toUpperCase();
		}
		return result;
	}

	// byte[] --> string
	public static String byte2Hex(byte[] bytes) {
		// Hex.encodeHexString
		int length = bytes.length;
		StringBuffer result = new StringBuffer(length * 2);

		for (int n = 0; n < length; n++) {
			String temp = Integer.toHexString(bytes[n] & 0xFF);
			if (temp.length() == 1) {
				temp = "0" + temp;
			}
			result.append(temp);
		}

		return result.toString();
	}

	// string --> byte[]
	public static byte[] hex2Byte(String str) throws Exception {
		byte[] src = str.getBytes("UTF-8");

		int length = src.length / 2;

		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
			String temp = new String(src, i * 2, 2);
			result[i] = (byte) Integer.parseInt(temp, 16);
		}
		return result;
	}

	// 截取字节数组
	private static byte[] cut(byte[] input) {
		byte[] dest = new byte[8];
		for (int i = 0; i < input.length && i < dest.length; i++) {
			dest[i] = input[i];
		}
		return dest;
	}

}
