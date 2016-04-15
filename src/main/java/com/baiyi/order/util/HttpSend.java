package com.baiyi.order.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpSend {

	public static String sendRequest(String url, String param) throws IOException {

		StringBuffer result = new StringBuffer();
		HttpURLConnection connection = null;

		DataOutputStream out = null;
		BufferedReader reader = null;

		try {
			connection = (HttpURLConnection) new URL(url).openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.connect();

			out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(param);
			out.flush();
			out.close();

			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line = "";

			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (connection != null) {
				connection.disconnect();
			}
		}
		return result.toString();
	}
}
