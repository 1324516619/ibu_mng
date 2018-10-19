package com.luolong.common.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.luolong.common.util.SerializeUtil;


public class RedisSessionDAO extends AbstractSessionDAO {

	private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);
	/**
	 * shiro-redis的session对象前缀
	 */
	private final String SHIRO_REDIS_SESSION_PRE = "shiroo_redis_session:";
	
	private RedisManager redisManager;
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		this.saveSession(session);
	}
	
	/**
	 * save session
	 * @param session
	 * @throws UnknownSessionException
	 */
	private void saveSession(Session session) throws UnknownSessionException{
		//System.out.println("执行RedisSessionDAO,saveSession方法,sessionid:"+session.getId());
		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			return;
		}
		try{
			byte[] key = getByteKey(session.getId());
			byte[] value = SerializeUtil.serialize(session);
			Long timeout = session.getTimeout()/1000;
			int expire = timeout.intValue();
			this.redisManager.set(key, value, expire);
		}catch(Exception ex){
			logger.error("执行redis,saveSession错误!", ex);
		}
	}

	@Override
	public void delete(Session session) {
		//System.out.println("执行RedisSessionDAO,delete方法，sessionid:"+session.getId());
		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			return;
		}
		try{
			redisManager.del(this.getByteKey(session.getId()));
		}catch(Exception ex){
			logger.error("执行redis,delete错误!", ex);
		}
	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();
		Set<byte[]> keys = redisManager.keys(this.SHIRO_REDIS_SESSION_PRE + "*");
		if(keys != null && keys.size()>0){
			for(byte[] key:keys){
				Session s = (Session)SerializeUtil.deserialize(redisManager.get(key));
				sessions.add(s);
			}
		}
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);  
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId == null){
			logger.error("session id is null");
			return null;
		}
		byte[] bytes = redisManager.get(this.getByteKey(sessionId));
		if(bytes!=null){
			return (Session)SerializeUtil.deserialize(bytes);
		}
		return null;
	}
	
	/**
	 * 获得byte[]型的key
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(Serializable sessionId){
		String preKey = this.SHIRO_REDIS_SESSION_PRE + sessionId;
		return preKey.getBytes();
	}

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
		this.redisManager.init();
	}
	
}
