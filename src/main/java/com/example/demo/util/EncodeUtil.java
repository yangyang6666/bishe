package com.example.demo.util;

public class EncodeUtil {

	private static String encode;

	public static final String DEFAULT_ENCODE = "UTF-8";

	/**
	 * encode
	 * 
	 * @return String
	 */
	public static String getEncode() {
		if (AssertUtil.isVal(encode))
			return encode;
		return DEFAULT_ENCODE;
	} 

	/**
	 * 构造方法
	 * 
	 * @param encode
	 */
	public EncodeUtil(String encode) {
		super();
		EncodeUtil.encode = encode;
	}
}
