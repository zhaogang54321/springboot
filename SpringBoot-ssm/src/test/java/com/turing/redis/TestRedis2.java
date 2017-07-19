package com.turing.redis;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.turing.manage.entity.Student;
import com.turing.manage.util.BeanUtils;
import com.turing.manage.util.DateUtils;

public class TestRedis2 {

	public static void main(String[] args) throws ParseException {
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("id", "67767");
//		map.put("name", "张飞");
//		map.put("age", "23");
//		map.put("lsh", "2017-07-12 00:52:52");
//		map.put("lsh", "Thu Jul 13 16:53:17 CST 2017");
//		Student stu = mapToBean(map,Student.class);
//		System.out.println(stu.getId()+" "+stu.getName()+" "+stu.getAge()+" "+stu.getLsh());
		
		Student stu = new Student();
		stu.setId("123");
		stu.setName("张飞");
		stu.setAge(67);
		stu.setLsh(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-07-14 22:29:55")  );
		
		//Map<String, String> map = beanToMap(stu);
		Map<String, String> map = BeanUtils.beanToMap( stu);
		System.out.println(map);
	}
	
	
	public static <T> T mapToBean(Map<String, String> map, Class<T> class1) {
		T obj=null;
		try {
			Field[] fields = class1.getDeclaredFields();
			obj = class1.newInstance();
			for (Entry<String, String> entry : map.entrySet() ) {
				Field field = class1.getDeclaredField( entry.getKey() );
				field.setAccessible(true);
				if (field.getType() == Integer.class) {
	              field.set(obj, Integer.parseInt( entry.getValue() ));
	           } else if (field.getType() == String.class) {
	              field.set(obj, entry.getValue());
	           } else if (field.getType() == Date.class) {
	              field.set(obj, DateUtils.parseDate(entry.getValue(), 0) );
	           } else if (field.getType() == Double.class) {          
	              field.set(obj,Double.parseDouble(entry.getValue()));       //由于时间类型比较特殊，所以我自己写了个方法处理时间类型，这一段不能直接用，可以自己写个方法替掉。
	           } else if (field.getType() == Float.class) {
	              field.set(obj, Float.parseFloat( entry.getValue()));
	           } else if (field.getType() == Long.class) {
	              field.set(obj, Long.parseLong( entry.getValue()));
	           } else if (field.getType() == Boolean.class) {
	              field.set(obj,Boolean.parseBoolean( entry.getValue()));
	           } else if (field.getType() == Short.class) {
	              field.set(obj, Short.parseShort( entry.getValue()));
	           }
	        }
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return obj;
	}
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
				map.put(name, String.valueOf(value));
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} 
		return map;
	}
}
