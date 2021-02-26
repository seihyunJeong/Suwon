package com.postlab.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

public class Resource {
	public static String getAPIFilePath(String market, String name) {
		
		return market + "/API/" + name + ".json";
	}
	
	public static String getAPIFile(String filePath) {
		ClassPathResource resource = new ClassPathResource(filePath);
		StringBuffer  ret = new StringBuffer();
		try {
		    Path path = Paths.get(resource.getURI());
		    List<String> content = Files.readAllLines(path);
		    
		    for(String str : content) {
		    	ret.append(str);
		    }
		} catch (IOException e) {
		    System.out.println(e.getMessage());
		}
		return ret.toString();
	}
	
	public static String getDatabaseFilePath(String market, String name) {
		
		return market + "/Database/" + name + ".json";
	}
	
	public static String getDatabaseFile(String filePath) {
		ClassPathResource resource = new ClassPathResource(filePath);
		StringBuffer  ret = new StringBuffer();
		try {
		    Path path = Paths.get(resource.getURI());
		    List<String> content = Files.readAllLines(path);
		    
		    for(String str : content) {
		    	ret.append(str);
		    }
		} catch (IOException e) {
		    System.out.println(e.getMessage());
		}
		return ret.toString();
	}
	
}
