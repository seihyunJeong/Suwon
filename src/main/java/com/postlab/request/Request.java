package com.postlab.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Request {
	public String get(String _url) throws IOException {
	    URL url = new URL (_url);
	    
	    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
	    connection.setRequestMethod("GET");
	    //connection.setDoOutput(true);
	    connection.setRequestProperty("Accept-Charset", "UTF-8");
	    connection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
	    connection.setRequestProperty("Accept", "application/json");

	    StringBuilder response = new StringBuilder ();
	    BufferedReader in = new BufferedReader(
	        new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
	    
	    String line;

	    while ((line = in.readLine()) != null) {
	    	
	    	response.append(line);
	    }

	    //System.out.println(name);
	    in.close();

	    return response.toString();
	}
	
	public String get(String _url, JSONArray headers) throws IOException {
	    URL url = new URL (_url);
	    
	    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
	    connection.setRequestMethod("GET");
	    //connection.setDoOutput(true);
	    connection.setRequestProperty("Accept-Charset", "UTF-8");
	    connection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
	    connection.setRequestProperty("Accept", "application/json");
	    for(int i=0; i< headers.size(); i++) {
	    	JSONObject header = (JSONObject)headers.get(i);
	    	String key = header.keySet().toString().replace("[", "").replace("]", "");
	    	String value = header.get(key).toString();
	    	//System.out.println(key + ", " + value);
		    connection.setRequestProperty(key, value);
	    }


	    StringBuilder response = new StringBuilder ();
	    BufferedReader in = new BufferedReader(
	        new InputStreamReader(connection.getInputStream()));
	    
	    String line;

	    while ((line = in.readLine()) != null) {
	    	
	    	response.append(line);
	    }

	    //System.out.println(name);
	    in.close();

	
	    return response.toString();
	}
	
	public String post(String _url, JSONObject data) throws IOException {
		StringBuilder response = new StringBuilder ();
		try {
	        URL url = new URL(_url);
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setDoOutput(true);

	        OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
	        osw.write(data.toString());
	        osw.flush();
	        osw.close();
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	        if (conn.getResponseCode() != 200) {
	            System.out.println("Failed: HTTP error code : " + conn.getResponseCode());
	        	throw new RuntimeException("Failed: HTTP error code : " + conn.getResponseCode());
	        } else {
	            System.out.println("Success");
	        }
	        
	        
	        String line = null;
	        while((line = br.readLine()) != null){
	        	response.append(line);
	        }
	        br.close();           
	        conn.disconnect();
	        
	    } catch (IOException e) {
	        System.out.println("RestCall Fail : " + e.getMessage());
	    }
		return response.toString();
	}
	

}
