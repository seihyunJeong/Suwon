package com.postlab.naver;

public class Enum {
	public enum APIType { 
		SEARCH, PRIVATEKEY;
	
	
		public static String enumToString(APIType type){
			String ret = null;
			switch(type) {
				case  SEARCH:
					ret = "search";
					break;
				case PRIVATEKEY:
					ret = "privateKey";
					break;
				default:
					break;
			}
			return ret;
		}
		
		public static APIType stringToEnum(String type){
			APIType ret = null;
			switch(type) {
				case  "search":
					ret = SEARCH;
					break;
				case "privateKey":
					ret = PRIVATEKEY;
					break;
				default:
					break;
			}
			return ret;
		}
	}
}
