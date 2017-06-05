package com.cn.ub.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 缓存实现类
 * @author lichuan
 *
 */
public class CommRedisCache implements Cache {
	
	//缓存默认对象默认名称
	private static final String YSCDEFAULTKEY = "yscDefaultKey";
	
	private String keyPrefix = "commcache-";

	
	
	private RedisTemplate<String, Object> redisTemplate;
	
	private String name;
	
	private byte[] getByteKey(Object key){
		if(key instanceof String){
			String preKey = this.keyPrefix + (String)key;
    		return preKey.getBytes();
    	}else{
    		return SerializeUtils.serialize(key);
    	}
	}
	

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public Object getNativeCache() {
		// TODO Auto-generated method stub
		return this.redisTemplate;
	}

	/**
	 * 获取缓存中的对象
	 * 
	 * @param key
	 *            键值
	 * @return ValueWrapper 缓存对象
	 */
	@Override
	public ValueWrapper get(Object key) {
		// TODO Auto-generated method stub
		String newKey = YSCDEFAULTKEY;
		if (null != key) {
			newKey = key.toString();
		}

		final String keyf = newKey;

		ValueWrapper retObj = null;

		try {
			Object object = null;
			object = redisTemplate.execute(new RedisCallback<Object>() {
				public Object doInRedis(RedisConnection connection)
						throws DataAccessException {
					byte[] key = getByteKey(keyf);
					byte[] value = connection.get(key);
					if (value == null) {
						return null;
					}
					return toObject(value);

				}
			});

			if (null != object) {
				retObj = new SimpleValueWrapper(object);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retObj;
	}

	/**
	 * 将对象压入缓存
	 * 
	 * @param key
	 *            键值
	 * @param value
	 *            将压入的对象
	 */
	@Override
	public void put(Object key, Object value) {
		// TODO Auto-generated method stub
		String newKey = YSCDEFAULTKEY;
		if (null != key) {
			newKey = key.toString();
		}

		final String keyf = newKey;

		final Object valuef = value;
		final long liveTimeSeconds = 86400;	//单位（秒）

		try {
			redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					byte[] keyb = getByteKey(keyf);
					byte[] valueb = toByteArray(valuef);
					connection.set(keyb, valueb);
					if (liveTimeSeconds > 0) {
						connection.expire(keyb, liveTimeSeconds);
					}
					return 1L;
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 描述 : <Object转byte[]>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param obj
	 * @return
	 */
	private byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ObjectOutputStream oos = null;
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}

	/**
	 * 描述 : <byte[]转Object>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param bytes
	 * @return
	 */
	private Object toObject(byte[] bytes) {
		Object obj = null;
		ObjectInputStream ois = null;
		ByteArrayInputStream bis = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	/**
	 * 删除缓存中的对象
	 * 
	 * @param key
	 *            键值
	 */
	@Override
	public void evict(Object key) {
		// TODO Auto-generated method stub
		String newKey = YSCDEFAULTKEY;
		if (null != key) {
			newKey = key.toString();
		}

		final String keyf = newKey;

		try {
			redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection)
						throws DataAccessException {
					return connection.del(getByteKey(keyf));
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 清空缓存
	 * 
	 * @param key
	 *            键值
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		try {
			redisTemplate.execute(new RedisCallback<String>() {
				public String doInRedis(RedisConnection connection)
						throws DataAccessException {
					connection.flushDb();
					return "ok";
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public <T> T get(Object arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
