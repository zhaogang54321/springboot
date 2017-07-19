package com.turing.redisConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * 操作Redis的各个方法
 * @author zhaogang
 * @date 2017年7月13日 14:44:26
 */
@Component
public class RedisClient {

	@Autowired
    private JedisPool jedisPool; 
	/**
	 * 获取value值
	 * @param key
	 * @return
	 * @throws Exception
	 */
    public String get(String key) throws Exception  {  
        Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            return jedis.get(key);  
        } finally {  
            //返还到连接池  
            jedis.close();  
        } 
    }
    /**
     * 判断缓存中是否存在此key
     * @param key
     * @return boolean 返回类型
     */
    public boolean exists(String key){
    	Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            return jedis.exists(key);
        } finally {  
            //返还到连接池  
            jedis.close();  
        } 
    }
    /**
     * 删除指定key的value
     * @param key
     */
    public void del(String key){
    	Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            if (jedis.exists(key)) {
				jedis.del(key);
			}
        } finally {  
            //返还到连接池  
            jedis.close();  
        } 
    }
    /**
     * 批量删除对应的value 
     * @param keys
     */
    public void del(String... keys){
    	Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();
            for (String key : keys) {
            	if (jedis.exists(key)) {
    				jedis.del(key);
    			}
            }
        } finally {  
            //返还到连接池  
            jedis.close();  
        } 
    }
    /**
     * 批量删除key
     * @param pattern
     */
    public void delPattern(final String pattern){
    	Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();
            Set<String> keys = jedis.keys(pattern);
            if (keys.size()>0) {
				for (String key : keys) {
					jedis.del(key);
				}
			}
        } finally {  
            //返还到连接池  
            jedis.close();  
        }
   }
    /**
	 * 写入缓存
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void set(String key, String value) throws Exception {  
        Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            jedis.set(key, value);  
        } finally {  
            //返还到连接池  
            jedis.close();  
        }  
    }
	/**
	 * 写入缓存并设置此缓存过期时间
	 * @param key
	 * @param value
	 * @param expireTime 生存时间（秒）
	 * @throws Exception
	 */
	public void setex(String key, String value,int expireTime) throws Exception {  
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.set(key, value); 
			jedis.expire(key, expireTime);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}  
	}
	/**
	 * 将 key 中储存的数字值增一
	 * @param key
	 * @throws Exception
	 */
	public void incr(String key)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.incr(key);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 将 key 将 key 中储存的数字值减一。
	 * @param key
	 * @throws Exception
	 */
	public void decr(String key)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.decr(key);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
	 * @param key
	 * @param hash
	 * @throws Exception
	 */
	public void hmset(String key,Map<String, String> hash)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.hmset(key, hash);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 将哈希表 key 中的域 field 的值设为 value 。
	 * @param key
	 * @param field
	 * @param value
	 * @throws Exception
	 */
	public void hset(String key,String field,String value)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.hset(key, field, value);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 返回哈希表 key 中，所有的域和值。
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> hgetall(String key)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			Map<String, String> map = jedis.hgetAll(key);
			return map;
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
	 * @param key
	 * @param strings
	 * @throws Exception
	 */
	public void rpush(String key,String...strings)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.rpush(key, strings);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 将一个或多个值 value 插入到列表 key 的表头
	 * @param key
	 * @param strings
	 * @throws Exception
	 */
	public void lpush(String key,String...strings)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.lpush(key, strings);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 返回列表 key 的长度,如果 key 不存在，则 key 被解释为一个空列表，返回 0
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Long llen(String key)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			Long len = jedis.llen(key);
			return len;
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<String> lrange(String key,long start, long end)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			List<String> list = jedis.lrange(key, start, end);
			return list;
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 根据参数 count 的值，移除列表中与参数 value 相等的元素
	 * @param key
	 * @param count
	 * 		count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count
	 * 		count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值
	 * 		count = 0 : 移除表中所有与 value 相等的值。
	 * @param value
	 * @throws Exception
	 */
	public void lrem(String key, long count,String value)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.lrem(key, count, value);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 移除并返回列表 key 的头元素
	 * @param key
	 * @throws Exception
	 */
	public void lpop(String key)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.lpop(key);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
	 * @param key
	 * @param members
	 * @throws Exception
	 */
	public void sadd(String key,String...members)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.sadd(key, members);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 返回集合 key 中的所有成员。不存在的 key 被视为空集合
	 * @param key
	 * @throws Exception
	 */
	public void smembers(String key)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.smembers(key);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 返回集合 key 的基数(集合中元素的数量)。
	 * @param key
	 * @throws Exception
	 */
	public void scard(String key)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.scard(key);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合的交集
	 * @param keys 多个集合的key值，
	 * @throws Exception
	 */
	public void sinter(String...keys)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.sinter(keys);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合的并集。
	 * @param keys
	 * @throws Exception
	 */
	public void sunion(String...keys)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.sunion(keys);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合之间的差集
	 * @param keys
	 * @throws Exception
	 */
	public void sdiff(String...keys)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.sdiff(keys);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 * @param key
	 * @param scoreMembers
	 * @throws Exception
	 */
	public void zadd(String key ,Map<String, Double> scoreMembers)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.zadd(key, scoreMembers);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 将一个 member 元素及其 score 值加入到有序集 key 当中
	 * @param key
	 * @param score
	 * @param member
	 */
	public void zadd(String key,Double score,String member){
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.zadd(key, score, member);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * @param key
	 * @param start
	 * @param end
	 * @throws Exception
	 */
	public Set<String> zrange(String key ,long start, long end)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			return jedis.zrange(key, start, end);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 返回有序集 key 中，指定区间内的成员。倒序
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public Set<String> zrevrange(String key ,long start, long end)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			return jedis.zrevrange(key, start, end);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 在zset里，删除一个或者多个成员
	 * @param key
	 * @param members
	 */
	public void zrem(String key,String...members)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.zrem(key, members);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 返回有序集 key 的基数。
	 * @param key
	 * @return
	 */
	public long zcard(String key){
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			return jedis.zcard(key);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 设置生存时间
	 * @param key
	 * @param seconds 秒
	 * @throws Exception
	 */
	public void expire(String key ,int seconds)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.expire(key, seconds);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}
	/**
	 * 移除 key 的生存时间
	 * @param key
	 * @throws Exception
	 */
	public void persist(String key)throws Exception{
		Jedis jedis = null;  
		try {  
			jedis = jedisPool.getResource();  
			jedis.persist(key);
		} finally {  
			//返还到连接池  
			jedis.close();  
		}
	}

}
