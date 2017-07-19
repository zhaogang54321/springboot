package com.turing.manage.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	 /**
	  * 定义常见的时间格式
	  * 
	  */
	 private static String[] dateFormat = { "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy年MM月dd日HH时mm分ss秒", "yyyy-MM-dd", "yyyy/MM/dd", "yy-MM-dd", "yy/MM/dd", "yyyy年MM月dd日",
	   "HH:mm:ss", "yyyyMMddHHmmss", "yyyyMMdd", "yyyy.MM.dd", "yy.MM.dd", "MM月dd日HH时mm分" };

	 /**
	  * 将日期格式从 java.util.Calendar 转到 java.sql.Timestamp 格式
	  * 
	  * @param date
	  *            java.util.Calendar 格式表示的日期
	  * 
	  * @return java.sql.Timestamp 格式表示的日期
	  * 
	  */
	 public static Timestamp convUtilCalendarToSqlTimestamp(Calendar date) {
	  if (date == null)
	   return null;
	  else
	   return new Timestamp(date.getTimeInMillis());
	 }

	 /**
	  * 将日期格式从 java.util.Timestamp 转到 java.util.Calendar 格式
	  * 
	  * @param date
	  *            java.sql.Timestamp 格式表示的日期
	  * 
	  * @return java.util.Calendar 格式表示的日期
	  * 
	  */
	 public static Calendar convSqlTimestampToUtilCalendar(Timestamp date) {
	  if (date == null)
	   return null;
	  else {
	   java.util.GregorianCalendar gc = new java.util.GregorianCalendar();
	   gc.setTimeInMillis(date.getTime());
	   return gc;
	  }
	 }

	 /**
	  * 解析一个字符串，形成一个Calendar对象，适应各种不同的日期表示法
	  * 
	  * @param dateStr
	  *            期望解析的字符串，注意，不能传null进去，否则出错
	  * 
	  * @return 返回解析后的Calendar对象 <br>
	  *         <br>
	  *         可输入的日期字串格式如下：
	  * 
	  * <br>
	  * "yyyy-MM-dd HH:mm:ss", <br>
	  * "yyyy/MM/dd HH:mm:ss", <br>
	  * "yyyy年MM月dd日HH时mm分ss秒", <br>
	  * "yyyy-MM-dd", <br>
	  * "yyyy/MM/dd", <br>
	  * "yy-MM-dd", <br>
	  * "yy/MM/dd", <br>
	  * "yyyy年MM月dd日", <br>
	  * "HH:mm:ss", <br>
	  * "yyyyMMddHHmmss", <br>
	  * "yyyyMMdd", <br>
	  * "yyyy.MM.dd", <br>
	  * "yy.MM.dd"
	  */
	 public static Calendar parseDate(String dateStr) {
	  if (dateStr == null || dateStr.trim().length() == 0)
	   return null;

	  Date result = parseDate(dateStr, 0);
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(result);

	  return cal;
	 }

	 /**
	  * 将一个日期转成日期时间格式，格式这样 2002-08-05 21:25:21
	  * 
	  * @param date
	  *            期望格式化的日期对象
	  * @return 返回格式化后的字符串 <br>
	  *         <br>
	  *         例： <br>
	  *         调用：
	  * 
	  * <br>
	  * Calendar date = new GregorianCalendar(); <br>
	  * String ret = DateUtils.toDateTimeStr(date); <br>
	  * 返回：
	  * 
	  * <br>
	  * ret = "2002-12-04 09:13:16";
	  */
	 public static String toDateTimeStr(Calendar date) {
	  if (date == null)
	   return null;
	  return new SimpleDateFormat(dateFormat[0]).format(date.getTime());
	 }

	 /**
	  * 将一个日期转成日期时间格式，格式这样 2002-08-05 21:25:21
	  * 
	  * @param date
	  *            期望格式化的日期对象
	  * @return 返回格式化后的字符串 <br>
	  *         <br>
	  *         例： <br>
	  *         调用：
	  * 
	  * <br>
	  * Calendar date = new GregorianCalendar(); <br>
	  * String ret = DateUtils.toDateTimeStr(date); <br>
	  * 返回：
	  * 
	  * <br>
	  * ret = "2002-12-04 09:13:16";
	  */
	 public static String toDateTimeStr(int format, Calendar date) {
	  if (date == null)
	   return null;

	  return new SimpleDateFormat(dateFormat[format]).format(date.getTime());
	 }

	 /**
	  * 将一个日期转成日期格式，格式这样 2002-08-05
	  * 
	  * @param date
	  *            期望格式化的日期对象
	  * @return 返回格式化后的字符串 <br>
	  *         <br>
	  *         例： <br>
	  *         调用：
	  * 
	  * <br>
	  * Calendar date = new GregorianCalendar(); <br>
	  * String ret = DateUtils.toDateStr(calendar); <br>
	  * 返回：
	  * 
	  * <br>
	  * ret = "2002-12-04";
	  */
	 public static String toDateStr(Calendar date) {
	  if (date == null)
	   return null;
	  return new SimpleDateFormat(dateFormat[3]).format(date.getTime());
	 }

	 public static String toDateStrByFormatIndex(Calendar date, int formatIndex) {
	  if (date == null)
	   return null;
	  return new SimpleDateFormat(dateFormat[formatIndex]).format(date.getTime());
	 }

	 public static int calendarMinus(Calendar d1, Calendar d2) {
	  if (d1 == null || d2 == null) {
	   return 0;
	  }

	  d1.set(Calendar.HOUR_OF_DAY, 0);
	  d1.set(Calendar.MINUTE, 0);
	  d1.set(Calendar.SECOND, 0);

	  d2.set(Calendar.HOUR_OF_DAY, 0);
	  d2.set(Calendar.MINUTE, 0);
	  d2.set(Calendar.SECOND, 0);

	  long t1 = d1.getTimeInMillis();
	  long t2 = d2.getTimeInMillis();
	  long daylong = 3600 * 24 * 1000;
	  t1 = t1 - t1 % (daylong);
	  t2 = t2 - t2 % (daylong);

	  long t = t1 - t2;
	  int value = (int) (t / (daylong));

	  return value;
	 }

	 /**
	  * 
	  * @param d1
	  * @param d2
	  * @return
	  */
	 public static long calendarminus(Calendar d1, Calendar d2) {
	  if (d1 == null || d2 == null) {
	   return 0;
	  }
	  return (d1.getTimeInMillis() - d2.getTimeInMillis()) / (3600 * 24000);
	 }

	 /**
	  * 内部方法，根据某个索引中的日期格式解析日期
	  * 
	  * @param dateStr
	  *            期望解析的字符串
	  * @param index
	  *            日期格式的索引
	  * 
	  * @return 返回解析结果
	  */
	 public static Date parseDate(String dateStr, int index) {
	  DateFormat df = null;
	  try {
	   df = new SimpleDateFormat(dateFormat[index]);

	   return df.parse(dateStr);
	  } catch (ParseException pe) {
	   return parseDate(dateStr, index + 1);
	  } catch (ArrayIndexOutOfBoundsException aioe) {
	   return null;
	  }
	 }

	 /**
	  * 字符转日期,字符串格式："yyyy-MM-dd"，例如2006-01-01
	  * 
	  * @param dateStr
	  * @return
	  */
	 public static Date StringToDate(String dateStr) {
	  if (dateStr == null || dateStr.trim().length() == 0) {
	   return null;
	  }
	  return parseDate(dateStr, 3);
	 }

	 /**
	  * DATE to String，支持多种格式
	  * 
	  * @param date
	  * @return
	  */
	 public static String dateToString(Date date, int index) {
	  if (date == null) {
	   return null;
	  }
	  return new SimpleDateFormat(dateFormat[index]).format(date);
	 }

	 /**
	  * DATE to String，转换结果格式为："yyyy-MM-dd"，例如2006-01-01
	  * 
	  * @param date
	  * @return
	  */
	 public static String dateToString(Date date) {
	  if (date == null) {
	   return null;
	  }
	  return new SimpleDateFormat(dateFormat[3]).format(date);
	 }

	 /**
	  * 将日期格式从 java.util.Date 转到 java.sql.Timestamp 格式 convUtilDateToSqlTimestamp
	  * <br>
	  * 
	  * @param date
	  *            java.util.Date 格式表示的日期
	  * @return Timestamp java.sql.Timestamp 格式表示的日期
	  */
	 public static Timestamp convUtilDateToSqlTimestamp(Date date) {
	  if (date == null)
	   return null;
	  else
	   return new Timestamp(date.getTime());
	 }

	 public static Calendar convUtilDateToUtilCalendar(Date date) {
	  if (date == null)
	   return null;
	  else {
	   java.util.GregorianCalendar gc = new java.util.GregorianCalendar();
	   gc.setTimeInMillis(date.getTime());
	   return gc;
	  }
	 }

	 /**
	  * 内部方法，根据某个索引中的日期格式解析日期
	  * 
	  * @param dateStr
	  *            期望解析的字符串
	  * @param index
	  *            日期格式的索引
	  * @return 返回解析结果
	  */
	 public static Timestamp parseTimestamp(String dateStr, int index) {
	  DateFormat df = null;
	  try {
	   df = new SimpleDateFormat(dateFormat[index]);

	   return new Timestamp(df.parse(dateStr).getTime());
	  } catch (ParseException pe) {
	   return new Timestamp(parseDate(dateStr, index + 1).getTime());
	  } catch (ArrayIndexOutOfBoundsException aioe) {
	   return null;
	  }
	 }

	 /**
	  * 内部方法，根据默认的日期格式“yyyy-MM-dd”解析日期
	  * 
	  * @param dateStr
	  *            期望解析的字符串
	  * @return 返回解析结果
	  */
	 public static Timestamp parseTimestamp(String dateStr) {
	  DateFormat df = null;
	  try {
	   df = new SimpleDateFormat(dateFormat[3]);
	   return new Timestamp(df.parse(dateStr).getTime());
	  } catch (ParseException pe) {
	   return null;
	  } catch (ArrayIndexOutOfBoundsException aioe) {
	   return null;
	  }
	 }
}
