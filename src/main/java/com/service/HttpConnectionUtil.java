package com.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import net.sf.json.JSONObject;

public class HttpConnectionUtil {
	public static final String CHARSET_UTF_8 = "utf-8";
	
	public static final int REQUEST_TIME_OUT = 50000;
	
	public static final int READ_TIME_OUT = 50000;

	
	public static void main(String[] args) throws Exception {
//		get();
//		post();
//		JSONObject a = JSONObject.fromObject("{\"test\":123}");
//		System.out.println(a.get("test"));
//		
//		JSONObject json = new JSONObject();
//		json.put("test", 123);
//		System.out.println(json.toString());
//		
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("test", "4444");
//		System.out.println(params.toString());
		JSONObject a = doPost("http://web.juhe.cn:8080/finance/exchange/rmbquot?key=774b26e7bcef93a1e656eca012af82c1", new JSONObject());
		System.out.println(a.get("error_code"));
	}
	
	public static HttpURLConnection postConnection(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(REQUEST_TIME_OUT);
		conn.setReadTimeout(READ_TIME_OUT);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestProperty("Accept-Charset", CHARSET_UTF_8);
		conn.setRequestProperty("Content-Type", "application/json");
		
		return conn;
	}
	
	public static JSONObject doPost(String path, JSONObject params) throws Exception {
		JSONObject jsonResult = new JSONObject();
		
		byte[] data = params.toString().getBytes(CHARSET_UTF_8);
		
		HttpURLConnection conn = postConnection(path);
		
		OutputStream outStream = conn.getOutputStream();
		outStream.write(data);
		outStream.flush();
		outStream.close();
		
		if (conn.getResponseCode() == 200) {
			InputStream inStream = conn.getInputStream();
			String result = new String(toByteArray(inStream), "UTF-8");
			System.out.println(result); // 响应代码 200表示成功
			
			jsonResult = JSONObject.fromObject(result);
		}
		
		return jsonResult;
	}
	
	public static void get() throws Exception {
		String path = "http://www.baidu.com";
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		InputStream inStream = conn.getInputStream();
		byte[] data = toByteArray(inStream);
		String result = new String(data, "UTF-8");
		System.out.println(result);
	}
	
	public static void post() throws Exception {
		String encoding = "UTF-8";
		//post的form参数(json兼职对)
		String params = "[{\"addTime\":\"2011-09-19 14:23:02\"[],\"iccid\":\"1111\",\"id\":0,\"imei\":\"2222\",\"imsi\":\"3333\",\"phoneType\":\"4444\",\"remark\":\"aaaa\",\"tel\":\"5555\"}]";
		String path = "http://www.baidu.com";
		byte[] data = params.getBytes(encoding);
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		
		conn.setRequestProperty("Content-Type", "application/x-javascript; charset=" + encoding);
		conn.setRequestProperty("Content-Length", String.valueOf(data.length));
		conn.setConnectTimeout(5 * 1000);
		OutputStream outStream = conn.getOutputStream();
		outStream.write(data);
		outStream.flush();
		outStream.close();
		System.out.println(conn.getResponseCode()); // 响应代码 200表示成功
		if (conn.getResponseCode() == 200) {
			InputStream inStream = conn.getInputStream();
			String result = new String(toByteArray(inStream), "UTF-8");
			System.out.println(result); // 响应代码 200表示成功
		}
	}
	
	private static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}
}
