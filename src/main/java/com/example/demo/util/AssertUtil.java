
package com.example.demo.util;
import java.util.Collection;
import java.util.Map;

public class AssertUtil {

	/**
	 * 判断String是否有值， 如果字符串为null 或者为 ""（空字符串）则返回false
	 * @param val String
	 * @return boolean
	 */
	public static boolean isVal(String val) {
		if (val == null || val.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断Long 类型数值是否有值，如果val = null 或者val ==0 则返回false; 否则返回true
	 * @param val
	 * @return
	 */
	public static boolean isVal(Long val) {
		if (val == null || val.longValue() == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断
	 * @param val
	 * @return
	 */
	public static boolean isVal(Object val) {
		if (val == null) {
			return false;
		}
		if (val instanceof String && val.toString().isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断Map是否为空
	 * @param val
	 * @return boolean
	 */
	public static boolean isVal(Map<?,?> val) {
		if (val == null || val.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断集合是否为空
	 * @param val
	 * @return boolean
	 */
	public static boolean isVal(Collection<?> val) {
		if (val == null || val.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断byte[]是否为空
	 * @param val
	 * @return boolean
	 */
	public static boolean isVal(byte[] val) {
		if (val == null || val.length <= 0) {
			return false;
		}
		return true;
	}
}
