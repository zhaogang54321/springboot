package com.turing.manage.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
	/**
	 * map转bean对象
	 * @param map
	 * @param class1
	 * @return
	 * @author zhaogang
	 * @date 2017年7月13日 16:39:52
	 */
	public static <T> T mapToBean(Map<String, String> map, Class<T> class1) {
		T obj=null;
		try {
			Field[] fields = class1.getDeclaredFields();
			obj = class1.newInstance();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				int mod = field.getModifiers();    
	            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
	                continue;    
	            }
				String name = field.getName();
				field.setAccessible(true);
				String value = map.get(name);
				if (value != null) {
					if (field.getType() == Integer.class) {
			              field.set(obj, Integer.parseInt( value ));
					}else if (field.getType() == String.class) {
			              field.set(obj, value);
					}else if (field.getType() == Date.class) {
			              field.set(obj, DateUtils.parseDate(value, 0) );
					}else if (field.getType() == Double.class) {          
			              field.set(obj,Double.parseDouble(value));
					}else if (field.getType() == Float.class) {
			              field.set(obj, Float.parseFloat( value));
					}else if (field.getType() == Long.class) {
			              field.set(obj, Long.parseLong( value));
					}else if (field.getType() == Boolean.class) {
			              field.set(obj,Boolean.parseBoolean( value));
					}else if (field.getType() == Short.class) {
			              field.set(obj, Short.parseShort( value));
			        }
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return obj;
	}
	/**
	 * bean对象转map
	 * @param obj
	 * @return
	 * @author zhaogang
	 * @date 2017年7月13日 22:40:33
	 */
	public static Map<String,String> beanToMap(Object obj){
		Map<String, String> map = new HashMap<String, String>();
		try {
			Class<? extends Object> clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String name =field.getName();

				field.setAccessible(true);
				Object fieldValue = field.get(obj);
				String value = "";
				if(field.getType() == Date.class){
					value = fieldValue!=null ? DateUtils.dateToString((Date) fieldValue, 0) : "";
				}else{
					value = fieldValue!=null ? String.valueOf(fieldValue) : "";
				}
				
//				if(field.getType() == Date.class){
//					Method m = (Method) clazz.getMethod( "get" + getMethodName( name ));
//					System.out.println(m.getName());
//					Object fieldValue = m.invoke(obj);
//					System.out.println("fieldValue:"+fieldValue);
//					value = fieldValue!=null ? DateUtils.dateToString((Date) fieldValue, 0) : "";
//				}
//				if(field.getType() == String.class){
//					Method m = (Method) clazz.getMethod( "get" + getMethodName( name ));
//					value = (String) m.invoke(obj);// 调用getter方法获取属性值
//				}
//				if(field.getType() == Integer.class){
//					Method m = (Method) clazz.getMethod( "get" + getMethodName( name ));
//					Integer val = (Integer) m.invoke(obj);
//					value = String.valueOf(val!=null?val:"");
//				}
//				if(field.getType() == Double.class){
//					Method m = (Method) clazz.getMethod( "get" + getMethodName( name ));
//					Double val = (Double) m.invoke(obj);
//					value = String.valueOf(val!=null?val:"");
//				}
//				if(field.getType() == Boolean.class){
//					Method m = (Method) clazz.getMethod( name );
//					Boolean val = (Boolean) m.invoke(obj);
//					value = String.valueOf(val!=null?val:"");
//				}
//				if(field.getType() == Short.class){
//					Method m = (Method) clazz.getMethod( "get" + getMethodName( name ));
//					Short val = (Short) m.invoke(obj);
//					value = String.valueOf(val!=null?val:"");
//				}
//				if(field.getType() == Long.class){
//					Method m = (Method) clazz.getMethod( "get" + getMethodName( name ));
//					Long val = (Long) m.invoke(obj);
//					value = String.valueOf(val!=null?val:"");
//				}
				
				map.put(name, String.valueOf(value));
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return map;
	}
	// 把一个字符串的第一个字母大写、效率是最高的、
	private static String getMethodName(String fildeName) throws Exception{
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}
}
