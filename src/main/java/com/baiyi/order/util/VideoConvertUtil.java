package com.baiyi.order.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.baiyi.order.util.EnumList.MaterialConvertEnum;

public class VideoConvertUtil {

	private static String FFMPEG = WebContext.webRootPath + WebContext.FFMPEG;

	private static String MENCODER = WebContext.webRootPath + WebContext.MENCODER;

	public static void convert(String src) throws Exception {
		convert(src, null);
	}

	public static void convert(String src, String dest) throws Exception {
		MaterialConvertEnum type = ValidateUtil.checkConvertType(src);
		String dir = dest == null ? src.substring(0, src.lastIndexOf(".")) : dest;
		String flv = dir + ".flv";

		switch (type) {
		case IMAGE:
			System.out.println("图片格式,直接保存");
			break;
		case AUDIO:
			System.out.println("音频格式,暂未处理");
			break;
		case ORIGINAL:
			System.out.println("视频格式(flv不需转换)");
			captureImg(src);
			break;
		case DIRECT:
			System.out.println("视频格式(可直接转换)");
			convertToFlv(src, flv);
			captureImg(flv);
			break;
		case INDIRECT:
			System.out.println("视频格式(不可直接转换)");
			String temp = dir + "_temp.avi";
			convertToAvi(src, temp);
			convertToFlv(temp, flv);
			captureImg(flv);

			File tempFile = new File(temp);
			if (tempFile.exists() && tempFile.isFile()) {
				tempFile.delete();
			}
			break;
		case UNKNOW:
			System.out.println("未知格式");
			break;
		}

	}

	public static void convertToAvi(String src, String dest) throws Exception {
		List<String> command = new ArrayList<>();
		if (WebContext.os.equals("windows")) {
			command.add(MENCODER);
		} else {
			command.add("mencoder");
		}
		command.add(src);
		command.add("-srate");
		command.add("32000");
		command.add("-vf-add");
		command.add("scale=800:600");
		command.add("-ofps");
		command.add("24");
		command.add("-oac");// 音频编码器
		command.add("mp3lame");
		command.add("-lameopts");
		command.add("cbr:br=32:mode=0");
		command.add("-ovc");
		command.add("xvid");
		command.add("-xvidencopts");
		command.add("bitrate=800");
		command.add("-o");
		command.add(dest);

		ProcessBuilder builder = new ProcessBuilder();
		builder.redirectErrorStream(true);
		builder.command(command);
		Process process = builder.start();
		printInfo(process.getInputStream());
		process.waitFor();
	}

	public static void convertToAvi(String src) throws Exception {
		String dest = src.substring(0, src.lastIndexOf(".")) + ".avi";
		convertToAvi(src, dest);
	}

	public static void convertToFlv(String src, String dest) throws Exception {
		List<String> command = new ArrayList<>();
		if (WebContext.os.equals("windows")) {
			command.add(FFMPEG);
		} else {
			command.add("ffmpeg");
		}

		command.add("-i");// 指定要转换的文件路径
		command.add(src);
		// 音频
		command.add("-ab");// 声音比特率
		command.add("128");
		command.add("-acodec");//
		command.add("libmp3lame");
		command.add("-ac");// 声道数量2双声道
		command.add("2");
		command.add("-ar");// 设定声音采样率
		command.add("22050");
		// 视频
		command.add("-b");// 画面压缩比特率
		command.add("512");
		command.add("-r");// 帧速率(非标准数值会导致音画不同步标准值为15或29.97)
		command.add("29.97");
		command.add("-y");// 覆盖输出文件
		// command.add("-sameq");// 使用与源视频相同的质量
		command.add("-qscale");// 视频输出质量,值越小质量越高
		command.add("10");
		command.add(dest);

		ProcessBuilder builder = new ProcessBuilder();
		builder.redirectErrorStream(true);
		builder.command(command);
		Process process = builder.start();
		printInfo(process.getInputStream());
		process.waitFor();
	}

	public static void convertToFlv(String src) throws Exception {
		String dest = src.substring(0, src.lastIndexOf(".")) + ".flv";
		convertToFlv(src, dest);
	}

	public static void captureImg(String src, String dest) throws Exception {
		List<String> command = new ArrayList<>();
		if (WebContext.os.equals("windows")) {
			command.add(FFMPEG);
		} else {
			command.add("ffmpeg");
		}

		command.add("-i");
		command.add(src);
		command.add("-y");
		command.add("-f");
		command.add("mjpeg");
		command.add("-ss");// 时间
		command.add("00:00:8");
		command.add("-t");
		command.add("00:00:10");
		command.add("-s");
		command.add("320x240");
		command.add("-vframes");
		command.add("10");
		command.add(dest);

		ProcessBuilder builder = new ProcessBuilder();
		builder.redirectErrorStream(true);
		builder.command(command);
		Process process = builder.start();
		printInfo(process.getInputStream());
		process.waitFor();

	}

	public static void captureImg(String src) throws Exception {
		System.out.println("正在截图");
		String dest = src.substring(0, src.lastIndexOf(".")) + ".jpg";
		captureImg(src, dest);
	}

	private static void printInfo(InputStream input) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
