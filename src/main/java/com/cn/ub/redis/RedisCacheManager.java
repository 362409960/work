package com.cn.ub.redis;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

public class RedisCacheManager implements CacheManager {

	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

	private String keyPrefix = "shirosess-";

	private RedisManager redisManager;

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		if (null == name) {
			name = null;
		}

		Cache c = caches.get(name);

		if (c == null) {
			// create a new cache instance
			c = new RedisCache<K, V>(redisManager, keyPrefix, name);

			// add it to the cache collection
			caches.put(name, c);
		}
		return c;
	}

}
