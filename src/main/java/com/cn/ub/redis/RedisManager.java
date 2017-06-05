package com.cn.ub.redis;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.Jedis;

public class RedisManager {
	
	private int liveTimeSeconds = 60 * 60 * 24; // 过期时间(秒),负数表示永久，如-1
	
	@Autowired
	@Qualifier("jedisConnectionFactory")
	private JedisConnectionFactory jedisConnectionFactory; // redis工厂

	public int getLiveTimeSeconds() {
		return liveTimeSeconds;
	}

	public void setLiveTimeSeconds(int liveTimeSeconds) {
		this.liveTimeSeconds = liveTimeSeconds;
	}

	public RedisManager() {
	}
	
	/**
	 * get value from redis
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public byte[] get(byte[] key) throws Exception {
		byte[] value = null;
		RedisConnection jedisConnection = null;
		Jedis jedis = null;
		try {
			jedisConnection = jedisConnectionFactory.getConnection();  
	        jedis = (Jedis)jedisConnection.getNativeConnection();
//			jedis = jedisConnectionFactory.getShardInfo().createResource();
			value = jedis.get(key);
		} catch (Exception ex) {
//			if (null != key){
//				System.out.println("+++++++++++get key=" + new String(key));
//			}
			
			ex.printStackTrace();
		} finally {
			if (null != jedisConnection) {
				jedisConnection.close();
			}
		}
		return value;
	}
	
	/**
	 * lpush value to redis
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public byte[] lpush(byte[] key, byte[] value) throws Exception {
	    return lpush(key,value,this.liveTimeSeconds);
	}
	
	/**
	 * lpush value to redis
	 * 
	 * @param key
	 * @param value
	 * @param expireSeconds 过期秒数
	 * @return
	 * @throws Exception
	 */
	public byte[] lpush(byte[] key, byte[] value,int expireSeconds) throws Exception {
		Jedis jedis = null;
		RedisConnection jedisConnection = null;
		try {
			jedisConnection = jedisConnectionFactory.getConnection();  
	        jedis = (Jedis)jedisConnection.getNativeConnection();
	        jedis.lpush(key, value);
			if (expireSeconds != 0) {
				jedis.expire(key, expireSeconds);
			}
		} catch (Exception ex) {
			throw new Exception();
		} finally {
			if (null != jedisConnection) {
				jedisConnection.close();
			}
		}
		return value;
	}

	/**
	 * set value to redis
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public byte[] set(byte[] key, byte[] value) throws Exception {
		return set(key,value,this.liveTimeSeconds);
	}

	/**
	 * set value to redis
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 * @throws Exception
	 */
	public byte[] set(byte[] key, byte[] value, int expireSeconds) throws Exception {
		RedisConnection jedisConnection = null;
		Jedis jedis = null;
		try {
//			if (null != key){
//				System.out.println("+++++++++++222set key=" + new String(key));
//			}
			jedisConnection = jedisConnectionFactory.getConnection();  
	        jedis = (Jedis)jedisConnection.getNativeConnection();
			jedis.set(key, value);
			if (expireSeconds != 0) {
				jedis.expire(key, expireSeconds);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (null != jedisConnection) {
				jedisConnection.close();
			}
		}
		return value;
	}

	/**
	 * 返回数组长度
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public long lrem(byte[] key) throws Exception {
		RedisConnection jedisConnection = null;
		Jedis jedis = null;
		long ret_value = 0;
		try {
			jedisConnection = jedisConnectionFactory.getConnection();  
	        jedis = (Jedis)jedisConnection.getNativeConnection();
	        ret_value = jedis.llen(key);
		} catch (Exception ex) {
			throw new Exception();
		} finally {
			if (null != jedisConnection) {
				jedisConnection.close();
			}
		}
		return ret_value;
	}
	
	/**
	 * 移除列表中与参数 value相等的全部元素
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void lrem(byte[] key, byte[] value) throws Exception {
		lrem(key,0,value);
	}
	
	/**
	 * 移除列表中与参数 value相等的元素
	 * @param key
	 * @param count	移除个数
	 *        count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT
	 *        count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值
	 *        count = 0 : 移除表中所有与 VALUE 相等的值 
	 * @param value
	 * @throws Exception
	 */
	public void lrem(byte[] key, int count, byte[] value) throws Exception {
		RedisConnection jedisConnection = null;
		Jedis jedis = null;
		try {
			jedisConnection = jedisConnectionFactory.getConnection();  
	        jedis = (Jedis)jedisConnection.getNativeConnection();
			jedis.lrem(key, count, value);
		} catch (Exception ex) {
			throw new Exception();
		} finally {
			if (null != jedisConnection) {
				jedisConnection.close();
			}
		}
	}

	/**
	 * del value from redis
	 * 
	 * @param key
	 * @throws Exception
	 */
	public void del(byte[] key) throws Exception {
		RedisConnection jedisConnection = null;
		Jedis jedis = null;
		try {
			jedisConnection = jedisConnectionFactory.getConnection();  
	        jedis = (Jedis)jedisConnection.getNativeConnection();
			jedis.del(key);
		} catch (Exception ex) {
			throw new Exception();
		} finally {
			if (null != jedisConnection) {
				jedisConnection.close();
			}
		}
	}

	/**
	 * flush value to redis
	 * 
	 * @throws Exception
	 */
	public void flushDB() throws Exception {
		RedisConnection jedisConnection = null;
		Jedis jedis = null;
		try {
			jedisConnection = jedisConnectionFactory.getConnection();  
	        jedis = (Jedis)jedisConnection.getNativeConnection();
			jedis.flushDB();
		} catch (Exception ex) {
			throw new Exception();
		} finally {
			if (null != jedisConnection) {
				jedisConnection.close();
			}
		}
	}

	/**
	 * get redis size
	 * 
	 * @throws Exception
	 */
	public Long dbSize() throws Exception {
		Long dbSize = 0L;
		RedisConnection jedisConnection = null;
		Jedis jedis = null;
		try {
			jedisConnection = jedisConnectionFactory.getConnection();  
	        jedis = (Jedis)jedisConnection.getNativeConnection();
			dbSize = jedis.dbSize();
		} catch (Exception ex) {
			throw new Exception();
		} finally {
			if (null != jedisConnection) {
				jedisConnection.close();
			}
		}
		return dbSize;
	}
	
	/**
	 * 返回所有(一个或多个)给定 key 的值
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public List<byte[]> lrange(byte[][] keys) throws Exception {
		List<byte[]> values = null;
		RedisConnection jedisConnection = null;
		Jedis jedis = null;
		try {
			jedisConnection = jedisConnectionFactory.getConnection();  
	        jedis = (Jedis)jedisConnection.getNativeConnection();
	        values = jedis.mget(keys);
		} catch (Exception ex) {
			throw new Exception();
		} finally {
			if (null != jedisConnection) {
				jedisConnection.close();
			}
		}
		return values;
	}
	
	/**
	 * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定
	 * @param key
	 * @param start 开始位置
	 * @param end 结束位置
	 * @return
	 * @throws Exception
	 */
	public List<byte[]> lrange(byte[] key,long start,long end) throws Exception {
		List<byte[]> keys = null;
		RedisConnection jedisConnection = null;
		Jedis jedis = null;
		try {
			jedisConnection = jedisConnectionFactory.getConnection();  
	        jedis = (Jedis)jedisConnection.getNativeConnection();
			keys = jedis.lrange(key, start, end);
		} catch (Exception ex) {
			throw new Exception();
		} finally {
			if (null != jedisConnection) {
				jedisConnection.close();
			}
		}
		return keys;
	}

	/**
	 * set value[] to redis
	 * 
	 * @param regex
	 * @return
	 * @throws Exception
	 */
	public Set<byte[]> keys(String pattern) throws Exception {
		Set<byte[]> keys = null;
		RedisConnection jedisConnection = null;
		Jedis jedis = null;
		try {
			jedisConnection = jedisConnectionFactory.getConnection();  
	        jedis = (Jedis)jedisConnection.getNativeConnection();
			keys = jedis.keys(pattern.getBytes());
		} catch (Exception ex) {
			throw new Exception();
		} finally {
			if (null != jedisConnection) {
				jedisConnection.close();
			}
		}
		return keys;
	}


}
