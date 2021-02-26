package com.postlab.keepa;

public class Enum {
	public enum APIType { 
		BROWSINGDEALS, REQUESTPRODUCTS, RETRIEVETOKENSTATUS, PRIVATEKEY;
	
	
		public static String enumToString(APIType type){
			String ret = null;
			switch(type) {
				case  BROWSINGDEALS:
					ret = "browsingDeals";
					break;
				case  REQUESTPRODUCTS:
					ret = "requestProducts";
					break;
				case  RETRIEVETOKENSTATUS:
					ret = "retrieveTokenStatus";
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
				case  "browsingDeals":
					ret = BROWSINGDEALS;
					break;
				case  "requestProducts":
					ret = REQUESTPRODUCTS;
					break;
				case  "retrieveTokenStatus":
					ret = RETRIEVETOKENSTATUS;
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
