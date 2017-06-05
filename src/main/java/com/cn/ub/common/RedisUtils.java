package com.cn.ub.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {
	//redis 服务器ip
	private static String ADDR = "localhost";
	//redis的端口号
	private static int PORT = 6379;
	//访问密码
	private static String AUTH = "123456";
	//可用连接实例的最大数目，默认位8
	private static int MAX_TOTAL = 512;
	 //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。
	private static int MAX_WAIT = 10000;
	 //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 50;
	
	private static int TIMEOUT = 10000;
	//在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    
    private static JedisPool jedisPool = null;
    
    static{
    	try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_TOTAL);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
   /**
    * 获取Jedis实例
    * @return
    */
    public synchronized static Jedis getJedis(){
    	if (null != jedisPool){
    		Jedis jedis =  jedisPool.getResource();
    		return jedis;
    	}
		return null;
    }
	/**
	 * 释放jedis资源
	 * @param jedis
	 */
    public static void returnResource(final Jedis jedis){
    	if (null !=  jedis){
    		jedisPool.returnResource(jedis);
    	}
    }
    
    public static String get(String key){  
        String value = null;  
        Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            value = jedis.get(key);  
        } catch (Exception e) {  
            //释放redis对象  
        	jedisPool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(jedis);  
        }  
          
        return value;  
    }  
}
