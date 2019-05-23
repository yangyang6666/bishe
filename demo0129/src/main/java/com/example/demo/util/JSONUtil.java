package com.example.demo.util;

import com.alibaba.fastjson.JSON;

/**
 * JSON工具类
 * @author gy
 */
public class JSONUtil {

	/**
	 * 将java bean转换为json字符串
	 * @param javaBean java bean
	 * @return
	 */
	public static String toString(Object javaBean) {
		return JSON.toJSONString(javaBean);
	}
	
	/**
	 * 将json字符串转换为java bean
	 * @param jsonString json字符串
	 * @param javaBeanClass java bean类型
	 * @return
	 */
	public static <T> T toJavaBean(String jsonString, Class<T> javaBeanClass) {
		return JSON.parseObject(jsonString, javaBeanClass);
	}
	
	/**
	 * 验证json字符串格式是否正确
	 * @param jsonString json字符串
	 * @return
	 */
	public static boolean validate(String jsonString) {
    	return new JSONValidator().validate(jsonString.trim());
    }
}
