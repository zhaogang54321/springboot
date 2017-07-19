package com.turing.redis;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.devtools.restart.RestartScope;

import redis.clients.jedis.Jedis;

public class TestRedis {

	private Jedis jedis = null;
	
	@Before
	public void initRedis(){
		jedis = new Jedis("127.0.0.1", 6379, 1000);
		jedis.auth("turingzhaogang_20160725");
	}
	
	@Test
	public void testString(){
		System.out.println(jedis.ping());
	}
}
