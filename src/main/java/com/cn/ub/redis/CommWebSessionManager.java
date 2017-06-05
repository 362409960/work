package com.cn.ub.redis;

import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

public class CommWebSessionManager extends DefaultWebSessionManager {

	/* 全局session超时时间（单位毫秒） */
	private long globalSessionTimeout = 30 * 60 * 1000;

	/**
	 * 获取全局session超时时间（单位毫秒）
	 */
	@Override
	public long getGlobalSessionTimeout() {
		// TODO Auto-generated method stub
		return this.globalSessionTimeout;
	}

	/**
	 * 设置sessionDAO,将session超时时间注入
	 */
	@Override
	public void setSessionDAO(SessionDAO sessionDAO) {
		RedisSessionDAO redisSessionDAO = null;
		try {
			if (sessionDAO instanceof RedisSessionDAO) {
				redisSessionDAO = (RedisSessionDAO) sessionDAO;
				int secord = (int) (this.globalSessionTimeout / 1000);
				redisSessionDAO.getRedisManager().setLiveTimeSeconds(secord);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// TODO Auto-generated method stub
		super.setSessionDAO(sessionDAO);
	}

	/**
	 * 设置session超时
	 */
	public void setGlobalSessionTimeout(long globalSessionTimeout) {
		this.globalSessionTimeout = globalSessionTimeout * 1000;
	}
}
