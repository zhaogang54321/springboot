package com.turing.redisConfig;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.turing.manage.entity.Student;
import com.turing.manage.mapper.StudentMapper;
import com.turing.manage.util.BeanUtils;
/**
 * 初始化Redis用，删除redis里数据，添加新的数据
 * @author zhaogang
 *
 */
@Configuration
@EnableConfigurationProperties(CacheTable.class)
public class InitRedis implements CommandLineRunner{

	@Autowired
    private JedisPool jedisPool; 
	@Autowired
	private StudentMapper mapper;
	@Resource
	private CacheTable cacheTable;

	@Override
	public void run(String... arg0) throws Exception {
		List<String> cacheList = cacheTable.getCacheList();
		Jedis jedis=null;
		try {  
	    	jedis = jedisPool.getResource();  
	    	for (int i = 0; i < cacheList.size(); i++) {
				String tableName = cacheList.get(i);
				//删除redis里脏数据
				jedis.del(tableName+"zset");
				Set<String> keys = jedis.keys(tableName+"hash*");
	            if (keys.size()>0) {
					for (String key : keys) {
						jedis.del(key);
					}
				}
	    	}
			//将table表里的所有数据读取到redis里，用Sorted Set集合存储，用orderby做为score，具体信息用hash存储
			List<Student> list = mapper.queryAll();
			for (Student student : list) {
				jedis.zadd("studentzset", Double.parseDouble( String.valueOf(student.getLsh()!=null?student.getLsh().getTime():new Date().getTime()) ), student.getId()	);
				jedis.hmset("studenthash"+student.getId(), BeanUtils.beanToMap(student));
			}
			
	    } finally {  
	    	//返还到连接池 
	    	if (jedis != null) {
	    		jedis.close();
			}
	    }
	}
	
	
}
