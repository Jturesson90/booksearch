package com.sigma.jesperturessonbooksearch;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerConnection {

	public static final String TAG = "ServerConnection";
	HttpURLConnection connection;

	public ServerConnection() {
	}
	public void jsonGet(final String url, Parser parser) {
		OutputStream out = null;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int statusCode = connection.getResponseCode();
			String message = connection.getResponseMessage();
			if (statusCode == HttpURLConnection.HTTP_OK) {
				InputStream input = connection.getInputStream();
                parser.parse(input);
         	}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	public static String readStream(InputStream input) {
		String textToReturn = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			textToReturn = stringBuilder.toString();
		} catch (Exception e) {
		} finally {
			try {
				input.close();
			} catch (Exception e) {
			}
		}
		return textToReturn;
	}
}