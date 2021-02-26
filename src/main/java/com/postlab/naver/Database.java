package com.postlab.naver;

import java.io.IOException;

import org.json.simple.JSONObject;

import com.postlab.request.Request;

public class Database {
	public static void insertToDatabase(String address, JSONObject item) {
		Request r = new Request();
		try {
			r.post(address, item);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
