package com.example.demo.common;

import java.util.UUID;

public class Utils {
	public static String getUUID() {
		UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        return uuidStr;
	}
}
