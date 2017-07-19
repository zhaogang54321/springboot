package com.turing.redisConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * redis连接池配置文件
 * @author zhaogang
 * @date 2017年7月13日 14:44:40
 */
@Configuration
public class RedisConfiguration {

	@Bean(name= "jedis")  
    @Autowired
    public JedisPool jedisPool(@Qualifier("jedis.pool") JedisPoolConfig config,   
                @Value("${jedis.host}")String host,   
                @Value("${jedis.port}")int port,
                @Value("${jedis.timeout}")int timeout,
                @Value("${jedis.database}")int database,
                @Value("${jedis.password}")String password) {  
        return new JedisPool(config, host, port, timeout, password, database);
    }  
      
    @Bean(name= "jedis.pool")  
    public JedisPoolConfig jedisPoolConfig (@Value("${jedis.pool.maxTotal}")int maxTotal,  
                                @Value("${jedis.pool.maxIdle}")int maxIdle,  
                                @Value("${jedis.pool.maxWaitMillis}")int maxWaitMillis,
                                @Value("${jedis.pool.testOnBorrow}")boolean testOnBorrow) {  
        JedisPoolConfig config = new JedisPoolConfig();  
        config.setMaxTotal(maxTotal);  
        config.setMaxIdle(maxIdle);  
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(testOnBorrow);
        return config;  
    } 
}
