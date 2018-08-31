package com.metro.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.expression.Resolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/***
 * po vo转换
 * 
 * 1.日期转换器格式暂时支持8种格式 yyyy-MM-dd yyyy-MM-dd HH yyyy-MM-dd HH:mm yyyy-MM-dd
 * HH:mm:ss yyyy/MM/dd yyyy/MM/dd HH yyyy/MM/dd HH:mm yyyy/MM/dd HH:mm:ss
 *
 * 2.json(jsonArray)转换器暂时支持4中json net.json fastjson gson org.json
 * 
 * 3.枚举目前暂时只支持自定义Enum注解解析 key -> value value -> key ClassName:BeanUtils.java
 * 
 * @author Sam Tan
 * @Description TODO
 * @date 2017年11月20日
 */
public class BeanUtils extends BeanUtilsBean {

	private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	private static BeanUtils instance = new BeanUtils();

	private static List<CustomConvert> converts;

	static {
		converts = new ArrayList<CustomConvert>();
		converts.add(new DateConvert());
		//下面三种暂时不开放
//		converts.add(new JsonConvert());
//		converts.add(new JsonArrayConvert());
//		converts.add(new EnumConvert());
	}

	public static BeanUtils getInstance() {
		return instance;
	}

	public static <T> List<T> transfers(List<?> pos, Class<T> voType)
			throws TransferException {
		return getInstance().transfers(pos, voType, null);

	}

	public static <T> T transfer(Object pos, Class<T> voType)
			throws TransferException {
		T t = null;
		if (pos != null) {
			try {
				t = voType.newInstance();
				getInstance().transfer(pos, t);
			} catch (Exception e) {
				throw new TransferException(e);
			}
		}
		return t;
	}

	public static <T> List<T> transfersB(List<?> pos, Class<T> voType) {
		List<T> ts = null;
		try {
			ts = getInstance().transfers(pos, voType, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return ts;
	}

	public static <T> T transferB(Object pos, Class<T> voType) {
		T t = null;
		if (pos != null) {
			try {
				t = voType.newInstance();
				getInstance().transfer(pos, t);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
		return t;
	}

	public void transfer(Object po, Object vo) throws TransferException {
		try {
			copyProperties(vo, po);
		} catch (Exception e) {
			throw new TransferException(e);
		}
	}
	

	public <T> List<T> transfers(List<?> pos, Class<T> voType, T tt)
			throws TransferException {

		try {
			if (pos != null && pos.size() > 0) {
				List<T> vos = new ArrayList<T>();
				for (Object po : pos) {

					T t = (T) voType.newInstance();
					transfer(po, t);
					vos.add(t);
				}
				return vos;
			}
		} catch (Exception e) {
			throw new TransferException(e);
		}

		return new ArrayList<T>();
	}

	/***
	 * 重写父类的copyProperty 目的：兼容枚举解析
	 */
	public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

		// Resolve any nested expression to get the actual target bean
		Object target = bean;
		Resolver resolver = getPropertyUtils().getResolver();
		Field crtField = null;
		try {
			crtField = bean.getClass().getDeclaredField(name);
		} catch (NoSuchFieldException e) {
		}

		while (resolver.hasNested(name)) {
			try {
				target = getPropertyUtils().getProperty(target,
						resolver.next(name));
				name = resolver.remove(name);
			} catch (NoSuchMethodException e) {
				return; // Skip this property setter
			}
		}

		// Declare local variables we will require
		String propName = resolver.getProperty(name); // Simple name of target
														// property
		Class<?> type = null; // Java type of target property
		int index = resolver.getIndex(name); // Indexed subscript value (if any)
		String key = resolver.getKey(name); // Mapped key value (if any)

		// Calculate the target property type
		if (target instanceof DynaBean) {
			DynaClass dynaClass = ((DynaBean) target).getDynaClass();
			DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
			if (dynaProperty == null) {
				return; // Skip this property setter
			}
			type = dynaPropertyType(dynaProperty, value);
		} else {
			PropertyDescriptor descriptor = null;
			try {
				descriptor = getPropertyUtils().getPropertyDescriptor(target,
						name);
				if (descriptor == null) {
					return; // Skip this property setter
				}
			} catch (NoSuchMethodException e) {
				return; // Skip this property setter
			}
			type = descriptor.getPropertyType();
			if (type == null) {
				return;
			}
		}

		// Convert the specified value to the required type and store it
		if (index >= 0) { // Destination must be indexed
			value = convertForCopy(value, type.getComponentType(), crtField);
			try {
				getPropertyUtils().setIndexedProperty(target, propName, index,
						value);
			} catch (NoSuchMethodException e) {
				throw new InvocationTargetException(e, "Cannot set " + propName);
			}
		} else if (key != null) { // Destination must be mapped
			// Maps do not know what the preferred data type is,
			// so perform no conversions at all
			// FIXME - should we create or support a TypedMap?
			try {
				getPropertyUtils().setMappedProperty(target, propName, key,
						value);
			} catch (NoSuchMethodException e) {
				throw new InvocationTargetException(e, "Cannot set " + propName);
			}
		} else { // Destination must be simple
			value = convertForCopy(value, type, crtField);
			try {
				/***
				 * 解决 string 转原始类型,金额问题（null --> int,long...） by 2017.12.25
				 * 
				 * part2 在#57612中发现当转换前的值为null或者空字符串时bigdecimal会
				 * 被转成0，这样造成了修改值错误，所以在公共部分统一将引用类型的转换改为null
				 */
				if (value == null || "".equals(value)) {

					if (type.isAssignableFrom(Integer.class)
							|| type.isAssignableFrom(Long.class)
							|| type.isAssignableFrom(Float.class)
							|| type.isAssignableFrom(Double.class)) {
						value = null;
					} else if (type.isAssignableFrom(int.class)
							|| type.isAssignableFrom(long.class)
							|| type.isAssignableFrom(float.class)
							|| type.isAssignableFrom(double.class)) {
						value = 0;
					} else if (type.isAssignableFrom(short.class)) {
						value = (short) 0;
					} else if (type.isAssignableFrom(Short.class)) {
						value = null;
					} else if (type.isAssignableFrom(BigDecimal.class)) {
						value = null;
					} else if (type.isAssignableFrom(boolean.class)) {
						value = false;
					} else if (type.isAssignableFrom(Boolean.class)) {
						value = null;
					}
				}
				getPropertyUtils().setSimpleProperty(target, propName, value);
			} catch (NoSuchMethodException e) {
				throw new InvocationTargetException(e, "Cannot set " + propName);
			}
		}

	}

	private static Class<?> dynaPropertyType(DynaProperty dynaProperty,
			Object value) {
		if (!dynaProperty.isMapped()) {
			return dynaProperty.getType();
		}
		return (value == null) ? String.class : value.getClass();
	}

	private Object convertForCopy(Object value, Class<?> type, Field crtField) {
		return (value != null) ? convert(value, type, crtField) : value;
	}

	protected Object convert(Object value, Class<?> type, Field crtField) {
		Converter converter = getConvertUtils().lookup(type);

		Object converterVal = null;
		if (value != null && value instanceof String
				&& !String.class.isAssignableFrom(type)
				&& "".equals(value.toString().trim())) {
			return converterVal;
		}

		converterVal = value;
		if (converterVal != null && type != null) {

			boolean defaultConvert = true;// 使用默认转换
			for (CustomConvert customConvert : converts) {
				if (customConvert.support(converterVal, type, crtField)) {// 自定义
					defaultConvert = false;
					converterVal = customConvert.convert(type, value, crtField);
					break;
				}
			}
			if (converter != null && defaultConvert) {
				converterVal = converter.convert(type, converterVal);
			}
		}
		return converterVal;
	}

	/***
	 * 自定义扩展接口 暂时定义成内部类 ClassName:CustomConvert.java
	 * 
	 * @author Sam Tan
	 * @Description TODO
	 * @date 2017年11月29日
	 */
	static class CustomConvert implements Converter {

		public boolean support(Object obj, Class<?> type) {
			return false;
		}

		public boolean support(Object obj, Class<?> type, Field field) {
			if (field != null && field.getAnnotations().length == 0) {
				return support(obj, type);
			}
			return false;
		}

		public <T> T convert(Class<T> type, Object value, Field field) {
			if (field != null && field.getAnnotations().length == 0) {
				return convert(type, value);
			}
			return null;
		}

		public <T> T convert(Class<T> type, Object value) {
			return null;
		}

	}

	/***
	 * 日期自定义转换类 ClassName:BeanUtils.java
	 * 
	 * @author Sam Tan
	 * @Description TODO
	 * @date 2017年11月29日
	 */
	static class DateConvert extends CustomConvert {

		final static String SPACE = " ";

		public boolean support(Object value, Class<?> type) {
			if (Date.class.isAssignableFrom(type)
					|| Date.class.isAssignableFrom(value.getClass())) {
				return true;
			}
			return false;
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T> T convert(Class<T> type, Object value) {

			if (Date.class.isAssignableFrom(value.getClass())
					&& String.class.isAssignableFrom(type)) {
				value = DateUtil.dateToStr((Date) value,
						DateUtil.DATE_FORMAT_LONG);
			} else if (Date.class.isAssignableFrom(type)
					&& String.class.isAssignableFrom(value.getClass())) {

				String converDate = String.valueOf(value).replaceAll("/", "-");// 如果有/则替换
				converDate = converDate.indexOf(SPACE) == -1 ? converDate
						+ " 00:00:00" : converDate;
				converDate = converDate.split(":").length == 1 ? converDate
						+ ":00:00" : converDate;
				converDate = converDate.split(":").length == 2 ? converDate
						+ ":00" : converDate;
				value = DateUtil.strToDate(DateUtil.DATE_FORMAT_LONG,
						converDate);
			}
			return (T) value;
		}

	}

//	/***
//	 * json解析 ClassName:BeanUtils.java
//	 * 
//	 * @author Sam Tan
//	 * @Description TODO
//	 * @date 2017年11月29日
//	 */
//	static final class JsonConvert extends CustomConvert {
//
//		public boolean support(Object value, Class<?> type) {// json to string
//			if (String.class.isAssignableFrom(type)
//					&& (JSONObject.class.isAssignableFrom(value.getClass())
//							|| net.sf.json.JSONObject.class
//									.isAssignableFrom(value.getClass())
//							|| org.json.JSONObject.class.isAssignableFrom(value
//									.getClass()) || com.google.gson.JsonObject.class
//								.isAssignableFrom(value.getClass()))) {
//				return true;
//			} else if (String.class.isAssignableFrom(value.getClass())
//					&& // string to json
//					(com.alibaba.fastjson.JSONObject.class
//							.isAssignableFrom(type)
//							|| net.sf.json.JSONObject.class
//									.isAssignableFrom(type)
//							|| org.json.JSONObject.class.isAssignableFrom(type) || com.google.gson.JsonObject.class
//								.isAssignableFrom(type))) {
//				return true;
//			}
//			return false;
//		}
//
//		@SuppressWarnings("unchecked")
//		@Override
//		public <T> T convert(Class<T> type, Object value) {
//			// 暂时只兼容4种json, by:2017-11-29
//			if (String.class.isAssignableFrom(type)
//					|| JSONObject.class.isAssignableFrom(value.getClass())) {
//				return (T) fastjson2String(value);
//			}
//			if (String.class.isAssignableFrom(type)
//					|| net.sf.json.JSONObject.class.isAssignableFrom(value
//							.getClass())) {
//				return (T) netsfjson2String(value);
//			} else if (String.class.isAssignableFrom(type)
//					|| org.json.JSONObject.class.isAssignableFrom(value
//							.getClass())) {
//				return (T) orgjson2String(value);
//			} else if (String.class.isAssignableFrom(type)
//					|| com.google.gson.JsonObject.class.isAssignableFrom(value
//							.getClass())) {
//				return (T) gson2String(value);
//			} else if (String.class.isAssignableFrom(value.getClass())// json to
//																		// string
//					|| JSONObject.class.isAssignableFrom(type)) {
//				return (T) string2Fastjson(value);
//			} else if (String.class.isAssignableFrom(type)
//					|| net.sf.json.JSONObject.class.isAssignableFrom(value
//							.getClass())) {
//				return (T) string2Netsfjson(value);
//			} else if (String.class.isAssignableFrom(type)
//					|| org.json.JSONObject.class.isAssignableFrom(value
//							.getClass())) {
//				return (T) string2Orgjson(value);
//			} else if (String.class.isAssignableFrom(type)
//					|| com.google.gson.JsonObject.class.isAssignableFrom(value
//							.getClass())) {
//				return (T) string2Gson(value);
//			}
//			return null;
//		}
//
//		protected String fastjson2String(Object obj) {
//			return ((com.alibaba.fastjson.JSONObject) obj).toJSONString();
//		}
//
//		protected String netsfjson2String(Object obj) {
//			return ((net.sf.json.JSONObject) obj).toString();
//		}
//
//		protected String orgjson2String(Object obj) {
//			return ((org.json.JSONObject) obj).toString();
//		}
//
//		protected String gson2String(Object obj) {
//			return ((com.google.gson.JsonObject) obj).toString();
//		}
//
//		// ----字符串转json分割----
//		protected com.alibaba.fastjson.JSONObject string2Fastjson(Object value) {
//			return com.alibaba.fastjson.JSONObject
//					.parseObject(value.toString());
//		}
//
//		protected net.sf.json.JSONObject string2Netsfjson(Object value) {
//			return net.sf.json.JSONObject.fromObject(value);
//		}
//
//		protected org.json.JSONObject string2Orgjson(Object value) {
//			return new org.json.JSONObject(value.toString());
//		}
//
//		protected com.google.gson.JsonObject string2Gson(Object value) {
//			return new com.google.gson.JsonParser().parse(value.toString())
//					.getAsJsonObject();
//		}
//	}
//
//	/***
//	 * jsonArray解析 ClassName:BeanUtils.java
//	 * 
//	 * @author Sam Tan
//	 * @Description TODO
//	 * @date 2017年11月29日
//	 */
//	static final class JsonArrayConvert extends CustomConvert {
//
//		public boolean support(Object value, Class<?> type) { // jsonArray to
//																// string
//			if (String.class.isAssignableFrom(type)
//					&& (com.alibaba.fastjson.JSONArray.class
//							.isAssignableFrom(value.getClass())
//							|| net.sf.json.JSONArray.class
//									.isAssignableFrom(value.getClass())
//							|| org.json.JSONArray.class.isAssignableFrom(value
//									.getClass()) || com.google.gson.JsonArray.class
//								.isAssignableFrom(value.getClass()))) {
//				return true;
//			}
//			if (String.class.isAssignableFrom(value.getClass())
//					&& // string to jsonArray
//					(com.alibaba.fastjson.JSONArray.class
//							.isAssignableFrom(type)
//							|| net.sf.json.JSONArray.class
//									.isAssignableFrom(type)
//							|| org.json.JSONArray.class.isAssignableFrom(type) || com.google.gson.JsonArray.class
//								.isAssignableFrom(type))) {
//				return true;
//			}
//			return false;
//		}
//
//		@SuppressWarnings("unchecked")
//		@Override
//		public <T> T convert(Class<T> type, Object value) {
//			// 暂时只兼容4种json, by:2017-11-29
//			if (String.class.isAssignableFrom(type)
//					|| JSONArray.class.isAssignableFrom(value.getClass())) {
//				return (T) fastjson2String(value);
//			} else if (String.class.isAssignableFrom(type)
//					|| net.sf.json.JSONArray.class.isAssignableFrom(value
//							.getClass())) {
//				return (T) netsfjson2String(value);
//			} else if (String.class.isAssignableFrom(type)
//					|| org.json.JSONArray.class.isAssignableFrom(value
//							.getClass())) {
//				return (T) orgjson2String(value);
//			} else if (String.class.isAssignableFrom(type)
//					|| com.google.gson.JsonArray.class.isAssignableFrom(value
//							.getClass())) {
//				return (T) gson2String(value);
//			} else if (String.class.isAssignableFrom(value.getClass())// string
//																		// to
//																		// jsonArrays
//					|| JSONArray.class.isAssignableFrom(type)) {
//				return (T) string2Fastjson(value);
//			} else if (String.class.isAssignableFrom(type)
//					|| net.sf.json.JSONArray.class.isAssignableFrom(value
//							.getClass())) {
//				return (T) string2Netsfjson(value);
//			} else if (String.class.isAssignableFrom(type)
//					|| org.json.JSONArray.class.isAssignableFrom(value
//							.getClass())) {
//				return (T) string2Orgjson(value);
//			} else if (String.class.isAssignableFrom(type)
//					|| com.google.gson.JsonArray.class.isAssignableFrom(value
//							.getClass())) {
//				return (T) string2Gson(value);
//			}
//			return null;
//		}
//
//		protected String fastjson2String(Object value) {
//			return ((com.alibaba.fastjson.JSONArray) value).toJSONString();
//		}
//
//		protected String netsfjson2String(Object value) {
//			return ((net.sf.json.JSONArray) value).toString();
//		}
//
//		protected String orgjson2String(Object value) {
//			return ((org.json.JSONArray) value).toString();
//		}
//
//		protected String gson2String(Object value) {
//			return ((com.google.gson.JsonArray) value).toString();
//		}
//
//		protected com.alibaba.fastjson.JSONArray string2Fastjson(Object value) {
//			return com.alibaba.fastjson.JSONArray.parseArray(value.toString());
//		}
//
//		protected net.sf.json.JSONArray string2Netsfjson(Object value) {
//			return net.sf.json.JSONArray.fromObject(value);
//		}
//
//		protected org.json.JSONArray string2Orgjson(Object value) {
//			return new org.json.JSONArray(value.toString());
//		}
//
//		protected com.google.gson.JsonArray string2Gson(Object value) {
//			return new com.google.gson.JsonParser().parse(value.toString())
//					.getAsJsonArray();
//		}
//	}
//
//	/***
//	 * 枚举解析 ClassName:BeanUtils.java
//	 * 
//	 * @author Sam Tan
//	 * @Description TODO
//	 * @date 2017年11月29日
//	 */
//	static final class EnumConvert extends CustomConvert {
//
//		public boolean support(Object value, Class<?> type, Field crtField) {
//			if (crtField != null && crtField.getAnnotation(Enum.class) != null) {
//				return true;
//			}
//			return false;
//		}
//
//		@SuppressWarnings("unchecked")
//		public <T> T convert(Class<T> type, Object val, Field field) {
//			if (val == null || field == null) {
//				return null;
//			}
//
//			Enum e = field.getAnnotation(Enum.class);
//			if (e.enumClass().isEnum()) {
//				try {
//					Method m = e.enumClass().getMethod(e.enumExecute(),
//							String.class);
//					return (T) m.invoke(e.enumClass(), val.toString().trim());
//				} catch (Exception ex) {
//					return null;
//				}
//			}
//			return (T) val;
//		}
//	}
//
//	public static void main(String[] args) {
//		
//		try {
//			BeanUtils.transfers(new ArrayList(),Object.class);
//		} catch (TransferException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		// Test test = new Test();
//		// String json = "{\"name\":\"sam\"}";
//		// JSONArray jsonArray = new JSONArray();
//		// JSONObject jsonOject = new JSONObject();
//		// jsonOject.put("name", "sam");
//		// test.setJson(json);
//		// test.setJsonObject(jsonOject);
//		// jsonArray.add(jsonOject);
//		// test.setArrays(jsonArray);
//
//		// Test2 test = new Test2();
//		// String json = "{\"name\":\"sam\"}";
//		// test.setJson(json);
//		// test.setJsonObject(json);
//		// test.setArrays("["+json+"]");
//		// try {
//		// Test t = BeanUtils.transfer(test, Test.class);
//		// System.out.println();
//		// } catch (TransferException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//	}

}
