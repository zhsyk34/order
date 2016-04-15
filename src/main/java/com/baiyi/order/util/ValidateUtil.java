package com.baiyi.order.util;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.baiyi.order.util.EnumList.MaterialConvertEnum;
import com.baiyi.order.util.EnumList.MaterialTypeEnum;

public class ValidateUtil {

	/* Array */
	public static boolean isEmpty(Object[] array) {
		return array == null || array.length == 0;
	}

	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}

	/* primary key */
	public static boolean isPK(Integer key) {
		return key != null && key > 0;
	}

	/* array contains obj */// TODO
	public static boolean indexOf(Object[] array, Object obj) {
		if (isEmpty(array) || obj == null) {
			return false;
		}
		for (Object each : array) {
			if (each.equals(obj)) {
				return true;
			}
		}
		return false;
	}

	/* date */
	public static boolean isSimpleDate(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}$");
		return pattern.matcher(str).matches();
	}

	public static boolean isWholeDate(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}$");
		return pattern.matcher(str).matches();
	}

	public static boolean isDate(String str) {
		return isSimpleDate(str) || isWholeDate(str);
	}

	/* file type */
	public static MaterialTypeEnum checkType(String path) {
		Pattern image = Pattern.compile(".*\\S+.*\\.(?i)(jpg|jpeg|bmp|png|gif)$");
		if (image.matcher(path).matches()) {
			return MaterialTypeEnum.IMAGE;
		}
		Pattern audio = Pattern.compile(".*\\S+.*\\.(?i)(mp3)$");
		if (audio.matcher(path).matches()) {
			return MaterialTypeEnum.AUDIO;
		}
		Pattern original = Pattern.compile(".*\\S+.*\\.(?i)(flv|asx|asf|mpg|wmv|3gp|mp4|mov|avi|wmv9|rm|rmvb|vob)$");
		if (original.matcher(path).matches()) {
			return MaterialTypeEnum.VIDEO;
		}
		return null;

	}

	/* file type */
	public static MaterialConvertEnum checkConvertType(String path) {
		Pattern image = Pattern.compile(".*\\S+.*\\.(?i)(jpg|jpeg|bmp|png|gif)$");
		if (image.matcher(path).matches()) {
			return MaterialConvertEnum.IMAGE;
		}
		Pattern audio = Pattern.compile(".*\\S+.*\\.(?i)(mp3)$");
		if (audio.matcher(path).matches()) {
			return MaterialConvertEnum.AUDIO;
		}
		Pattern original = Pattern.compile(".*\\S+.*\\.(?i)(flv)$");
		if (original.matcher(path).matches()) {
			return MaterialConvertEnum.ORIGINAL;
		}
		Pattern direct = Pattern.compile(".*\\S+.*\\.(?i)(asx|asf|mpg|wmv|3gp|mp4|mov|avi)$");
		if (direct.matcher(path).matches()) {
			return MaterialConvertEnum.DIRECT;
		}
		Pattern indirect = Pattern.compile(".*\\S+.*\\.(?i)(wmv9|rm|rmvb|vob)$");
		if (indirect.matcher(path).matches()) {
			return MaterialConvertEnum.INDIRECT;
		}
		return MaterialConvertEnum.UNKNOW;
	}

}
