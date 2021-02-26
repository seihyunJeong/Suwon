package com.postlab.naver;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.postlab.keepa.Database;
import com.postlab.naver.Enum.APIType;
import com.postlab.request.Request;
import com.postlab.utils.Resource;
import com.postlab.utils.Util;

public class APIHandler {
	String requestUrl = null;
	public void messageHandler(JSONObject jObj){
		System.out.println(jObj);
		
		Util u = new Util();
		Resource resource = new Resource();
		String market = jObj.get("market").toString();
		String type = jObj.get("type").toString();
		String filePath = resource.getAPIFilePath(market, type);
		APIType e = APIType.stringToEnum(type);
		System.out.println("enum type: " + e);
		
		String contents = resource.getAPIFile(filePath);
		JSONObject o = u.stringToJSON(contents);
		requestUrl = o.get("url").toString();
		String delimeter = o.get("delimeter").toString();
		System.out.println("delimeter: " + delimeter);
		String subDelimeter = o.get("subDelimeter").toString();
		System.out.println("subDelimeter: " + subDelimeter);
		
		String pkUrI = resource.getAPIFilePath(market, "privateKey");
		String pkContents = resource.getAPIFile(pkUrI);
		JSONObject pkObject = u.stringToJSON(pkContents);
		
		JSONArray headers = new JSONArray();
		JSONObject clientId = new JSONObject();
		JSONObject clientSecret = new JSONObject();
		
		//String clientId = pkObject.get("X-Naver-Client-Id").toString();
		//String clientSecret = pkObject.get("X-Naver-Client-Secret").toString();
		clientId.put("X-Naver-Client-Id", pkObject.get("X-Naver-Client-Id").toString());
		headers.add(clientId);

		clientSecret.put("X-Naver-Client-Secret", pkObject.get("X-Naver-Client-Secret").toString());
		headers.add(clientSecret);

		System.out.println(headers);
		//requestUrl += "clientId=" + clientId + "&clientSecret=" + clientSecret;
			
		jObj.keySet().forEach(key ->
	    {
	    	if(key.toString().compareTo("market") != 0 && key.toString().compareTo("type") != 0) {
	    		Object value = jObj.get(key);
		        requestUrl += "&" + key + "=" + value;
	    	}
	    });
		
		System.out.println("request url: " + requestUrl);
		
		Request r = new Request();
		String response = null;
		try {
			response = r.get(requestUrl, headers);
			//System.out.println(response);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//for(int i=0; i< 400; i++) {
		//	System.out.print(response.charAt(i));
		//}
		JSONArray items = new JSONArray();
		if(e == APIType.SEARCH) {
			JSONObject target = u.stringToJSON(response);
			items = u.retrieveByDelimeterAsJSONArray(target, delimeter);
		}

		if(e == APIType.SEARCH) {
			Database db = new Database();
			
			//get db api = host + port + uri
			String hostFilePath = resource.getDatabaseFilePath(market, "localAddress");
			String hostFile = resource.getAPIFile(hostFilePath);
			System.out.println(hostFile);
			JSONObject jHost = u.stringToJSON(hostFile);
			String host = jHost.get("host").toString();
			String port = jHost.get("port").toString();
			
			String databaseFilePath = resource.getDatabaseFilePath(market, type);
			String databaseFile = resource.getAPIFile(databaseFilePath);
			System.out.println(databaseFile);
			JSONObject jUri = u.stringToJSON(databaseFile);
			String uri = jUri.get("create").toString();
			
			String databaseAddress = host + ":" + port + uri;
			System.out.println(databaseAddress);
			System.out.println(items.size());
			//System.out.println(items);
			
			for(int i=0; i< items.size(); i++) {
				JSONObject item = (JSONObject)items.get(i);
				db.insertToDatabase(databaseAddress, item);
				System.out.println(item);
			}
			
		}
	}
}
