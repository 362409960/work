package com.cn.ub.redis;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

public class RedisSessionDAO extends AbstractSessionDAO{
	
	private RedisManager redisManager;

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	
	private String keyBefore = "shiro_redis_session:";

	@Override
	public void update(Session session) throws UnknownSessionException {
		try {
//			System.out.println("================更新session");			
			this.saveSession(session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * save session
	 * 
	 * @param session
	 * @throws Exception
	 */
	private void saveSession(Session session) throws Exception {
		if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {  
			
	        return; //如果会话过期/停止 没必要再更新了  
	    }
		if (session == null || session.getId() == null) {
			
			return;
		}

		byte[] key = getByteKey(session.getId());
		byte[] value = SerializeUtils.serialize(session);
		
		session.setTimeout(redisManager.getLiveTimeSeconds() * 1000);
		this.redisManager.set(key, value, redisManager.getLiveTimeSeconds());
	}

	@Override
	public void delete(Session session) {
		if (session == null || session.getId() == null) {
			
			return;
		}
		try {
//			System.out.println("================删除session");
			redisManager.del(this.getByteKey(session.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();


		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = null;
		try {
//			System.out.println("================创建session");
			sessionId = this.generateSessionId(session);
			this.assignSessionId(session, sessionId);
			this.saveSession(session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session.getId();
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
		
			return null;
		}

		Session s = null;
		try {
//			System.out.println("================读session");
			s = (Session) SerializeUtils.deserialize(redisManager.get(this.getByteKey(sessionId)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 获得byte[]型的key
	 * 
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(Serializable sessionId) {
		String preKey = keyBefore + sessionId;
		return preKey.getBytes();
	}

	

	

	/**
	 * Sets the Redis sessions key prefix.
	 * 
	 * @param keyPrefix
	 *            The prefix
	 */
	public void setkeyBefore(String keyBefore) {
		this.keyBefore = keyBefore;
	}


}
