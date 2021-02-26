package com.postlab.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Util {
	public JSONObject stringToJSON(String s) {
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObj = (JSONObject) obj;

		return jsonObj;
	}

	public JSONArray retrieveByDelimeterAsJSONArray(JSONObject target, String type) {
		return (JSONArray) target.get(type);
	}

	public JSONObject retrieveByDelimeterAsJSONObject(JSONObject target, String type) {
		return (JSONObject) target.get(type);
	}

}
