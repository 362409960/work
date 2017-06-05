package com.cn.ub.redis;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class RedisCache<K, V> implements Cache<K, V> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	/**
     * The wrapped Jedis instance.
     */
	@Autowired
	private RedisManager cache;
	
	/**
	 * The Redis key prefix for the sessions 
	 */
	private String keyPrefix = "shirosess-";
	
	private String cacheName = null;
	
	/**
	 * Returns the Redis session keys
	 * prefix.
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key 
	 * prefix.
	 * @param keyPrefix The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
	
	/**
	 * 通过一个JedisManager实例构造RedisCache
	 */
	public RedisCache(RedisManager cache){
		 if (cache == null) {
	         throw new IllegalArgumentException("Cache argument cannot be null.");
	     }
	     this.cache = cache;
	}
	
	/**
	 * Constructs a cache instance with the specified
	 * Redis manager and using a custom key prefix.
	 * @param cache The cache manager instance
	 * @param prefix The Redis key prefix
	 */
	public RedisCache(RedisManager cache, 
				String prefix,String cacheName){
		 
		this( cache );
		
		// set the prefix
		this.keyPrefix = prefix;
		this.cacheName = cacheName;
	}
	
	/**
	 * 获得byte[]型的key
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(K key){
		if(key instanceof String){
			String preKey = this.keyPrefix + this.cacheName + "-" + key;
    		return preKey.getBytes();
    	}else if(key instanceof byte[]){	//如果是byte[]，直接使用
    		return (byte[])key;
    	}else{
    		return SerializeUtils.serialize(key);
    	}
	}
 	
	@Override
	public V get(K key) throws CacheException {
		logger.debug("根据key从Redis(" + this.cacheName + ")中获取对象 key [" + key + "]");
		try {			
			/*if (key == null) {
				Subject subject = SecurityUtils.getSubject();
				Session session = subject.getSession();
				
				CommonUser commUser = (CommonUser)session.getAttribute(Constants.SESSION_USER);
				if (null == commUser){
					return null;
				}
				
				key = (K)commUser.getAccount();
	        }*/
			
        	byte[] rawValue = cache.get(getByteKey(key));
        	@SuppressWarnings("unchecked")
			V value = (V)SerializeUtils.deserialize(rawValue);
        	if (value != null){
        		logger.debug("======" + value.toString());
        	}else{
        		logger.debug("++++++ value is null");
        	}
        	return value;
		} catch (Throwable t) {
			throw new CacheException(t);
		}

	}

	@Override
	public V put(K key, V value) throws CacheException {
		logger.debug("根据key从Redis(" + this.cacheName + ")中存储 key [" + key + "]");

		try {/*
			    if (null == key){
			    	SimpleAuthenticationInfo principals = (SimpleAuthenticationInfo)value;			    	
			    	CommonUser commUser = (CommonUser) principals.getPrincipals().getPrimaryPrincipal();
			    	
			    	if (null != commUser){
			    	    key = (K)commUser.getAccount();
			    	}
			    }*/
			 	cache.set(getByteKey(key), SerializeUtils.serialize(value));			 	
	            return value;
	        } catch (Throwable t) {
	            throw new CacheException(t);
	        }
	}

	@Override
	public V remove(K key) throws CacheException {
		logger.debug("从redis中删除 key [" + key + "]");
		try {
            V previous = get(key);
            cache.del(getByteKey(key));
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@Override
	public void clear() throws CacheException {
	//	logger.debug("从redis中删除所有元素");
		try {
            cache.flushDB();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@Override
	public int size() {
		try {
			Long longSize = new Long(cache.dbSize());
            return longSize.intValue();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		try {
            Set<byte[]> keys = cache.keys(this.keyPrefix + this.cacheName + "-" + "*");
            if (CollectionUtils.isEmpty(keys)) {
            	return Collections.emptySet();
            }else{
            	Set<K> newKeys = new HashSet<K>();
            	for(byte[] key:keys){
            		newKeys.add((K)key);
            	}
            	return newKeys;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@Override
	public Collection<V> values() {
		try {
            Set<byte[]> keys = cache.keys(this.keyPrefix + this.cacheName + "-" + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                List<V> values = new ArrayList<V>(keys.size());
                for (byte[] key : keys) {
                    @SuppressWarnings("unchecked")
					V value = get((K)key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

}
