package com.example.demo.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.HttpResult;


/**
 * 公用工具
 * 
 * @author gaoye
 *
 */

public class CommonUtil {

	/**
	 * 将序列后json字符串反序列HttpResult
	 * 
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static HttpResult getHttpResultByStringJson(String json) throws Exception {
		Map map1 = JSON.parseObject(json, Map.class);
		return new HttpResult<>(Integer.parseInt(map1.get("code").toString()), (String) map1.get("msg"),
				map1.get("data"));
	}

	/**
	 * 执行POST方式的请求
	 * 
	 * @param urlStr
	 * @param urlStr
	 * @param bean
	 * @return
	 * @throws Exception
	 * @
	 */
	public static HttpResult post(String baseurl, String urlStr, Object bean) throws Exception {
		StringBuffer str = new StringBuffer(baseurl);

		str.append(urlStr);
		Map<String, Object> beanmap = null;

		if (AssertUtil.isVal(bean) && bean instanceof List) {
			beanmap = new HashMap<>();
			beanmap.put("data", bean);
		} else if (AssertUtil.isVal(bean) && bean instanceof Map) {
			beanmap = (Map<String, Object>) bean;
		} else if (AssertUtil.isVal(bean))
			beanmap = BeanToMapUtils.toMap(bean);

		HttpResult res = getHttpResultByStringJson(HttpClientUtil.post(str.toString(), beanmap).toString());
		return res;
	}

	/**
	 * 把枚举对象转成json对象
	 * 
	 * @param enumArr
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static JSONArray enumToJson(Object[] enumArr) {
		JSONArray jsonArr = new JSONArray();
		for (Object e : enumArr) {
			Class clasz = e.getClass();
			JSONObject json = new JSONObject();
			Field[] fieldArr = clasz.getDeclaredFields();
			for (int i = 0; i < fieldArr.length; i++) {
				String filedName = fieldArr[i].getName();
				if (filedName.startsWith("ENUM")) {
					continue;
				}
				try {
					String methodName = "get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
					Method m = clasz.getMethod(methodName);
					Object value = m.invoke(e);
					json.put(filedName, value);
				} catch (Exception ex) {
					continue;
				}
			}
			jsonArr.add(json);
		}
		return jsonArr;
	}

	public static boolean isEmpty(Object v) {
		return isEmpty(v, true);
	}

	public static boolean isEmpty(Object v, boolean trim) {
		if (v == null)
			return true;
		if (v instanceof String) {
			String sv = (String) v;
			return trim ? sv.trim().length() == 0 : sv.length() == 0;
		} else {
			return false;
		}
	}

}
