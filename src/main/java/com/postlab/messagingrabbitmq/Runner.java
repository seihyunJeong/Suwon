package com.postlab.messagingrabbitmq;

import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;
	private final Receiver receiver;

	public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
		this.receiver = receiver;
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending message...");
		boolean q1 = false;
		boolean q2 = false;
		boolean q3 = false;
		boolean q4 = true;
		
		if(q4 == true) {
			JSONObject jObj = new JSONObject();
			jObj.put("market", "naver");
			jObj.put("type", "search");
			jObj.put("query", URLEncoder.encode(("도마").toString()));
			jObj.put("display", 10);
			jObj.put("start", 1);
			jObj.put("sort", "sim");
			rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, "foo.bar.baz", jObj.toJSONString());
		}
		
		if(q1 == true) {
			JSONObject jObj = new JSONObject();
			jObj.put("market", "keepa");
			jObj.put("type", "retrieveTokenStatus");
			rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, "foo.bar.baz", jObj.toJSONString());
		}
		
		if(q2 == true) {
			JSONObject jObj = new JSONObject();
			jObj.put("market", "keepa");
			jObj.put("type", "requestProducts");
			jObj.put("domain", "1");
			jObj.put("asin", "B0895S8XBR");
			rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, "foo.bar.baz", jObj.toJSONString());
		}
		
		if(q3 == true){
			JSONObject jObj = new JSONObject();
			jObj.put("market", "keepa");
			jObj.put("type", "browsingDeals");
			
			
	        
			
			JSONObject selection = new JSONObject();
			selection.put("page", 0);
			selection.put("domainId", 1);
			JSONArray priceTypes = new JSONArray();
			priceTypes.add(1);
			selection.put("priceTypes", priceTypes);
			selection.put("dateRange", 0);
			
			
			//selection.put("excludeCategories", "[1064954, 11091801]");
			JSONArray includeCategories = new JSONArray();
			includeCategories.add(172282);
			selection.put("includeCategories", includeCategories);
			
			JSONArray deltaRange = new JSONArray();
			deltaRange.add(250);
			deltaRange.add(2147483647);
			selection.put("deltaRange", deltaRange);
			
			JSONArray deltaPercentRange = new JSONArray();
			deltaPercentRange.clear();
			deltaPercentRange.add(20);
			deltaPercentRange.add(80);
			selection.put("deltaPercentRange", deltaPercentRange);
			
			JSONArray salesRankRange = new JSONArray();
			salesRankRange.add(-1);
			salesRankRange.add(-1);
			selection.put("salesRankRange", salesRankRange);
			JSONArray currentRange = new JSONArray();
			currentRange.add(18600);
			currentRange.add(2147483647);
			selection.put("currentRange", currentRange);
			
			
			selection.put("minRating", -1);
			selection.put("isLowest", false);
			selection.put("isLowestOffer", false);
			selection.put("isOutOfStock", false);
			selection.put("titleSearch", "");
			selection.put("isRangeEnabled", true);
			selection.put("isFilterEnabled", true);
			selection.put("filterErotic", true);
			selection.put("hasReviews", false);
			selection.put("sortType", 2);
			selection.put("isPrimeExclusive", false);
			selection.put("mustHaveAmazonOffer", false);
			selection.put("mustNotHaveAmazonOffer", false);
			
			jObj.put("selection", URLEncoder.encode(selection.toJSONString())); 
			System.out.println(jObj.toJSONString());
			rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, "foo.bar.baz", jObj.toJSONString());
		}
		
		//rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
	}

}
