package com.turing.manage.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MyRandom {
	/**
	 * 获取一个不重复的随机数
	 * @param length 16位时间数字后，连接的随机数的位数
	 * @return
	 */
	public static String getValue(int length){
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		Random random = new Random();
		int num = 1;
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return s+new Random().nextInt(num);
	}
}
