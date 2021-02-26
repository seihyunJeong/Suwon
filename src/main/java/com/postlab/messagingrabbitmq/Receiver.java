package com.postlab.messagingrabbitmq;

import java.util.concurrent.CountDownLatch;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.postlab.utils.Util;

@Component
public class Receiver {

	private CountDownLatch latch = new CountDownLatch(1);

	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
		
		Util u = new Util();
		JSONObject jObj = u.stringToJSON(message);
		//System.out.println(jObj.get("market"));
		if(jObj.get("market").toString().compareTo("keepa") == 0) {
			com.postlab.keepa.APIHandler f = new com.postlab.keepa.APIHandler();
			f.messageHandler(jObj);
		}else if(jObj.get("market").toString().compareTo("naver") == 0) {
			com.postlab.naver.APIHandler f = new com.postlab.naver.APIHandler();
			f.messageHandler(jObj);
		}
		
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
