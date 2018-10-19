package com.luolong.common.security;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManager {
	
	private String host ;
	private String password;
	private int port = 6379;
	private int expire = 0;
	
	private int maxTotal;
	private int maxIdle;
	private long maxWaitMillis;
	
	private  JedisPool jedisPool = null;

	public RedisManager(){
		 
	}
	
	public RedisManager(String host,int port,int expire, String password
			, int maxTotal, int maxIdle, long maxWaitMillis){
		this.host = host;
		this.port = port;
		this.expire = expire;
		this.password = password;
		this.maxTotal = maxTotal;
		this.maxIdle = maxIdle;
		this.maxWaitMillis = maxWaitMillis;
	}
	/**
	 * 初始化方法
	 */
	public  void init(){
		if(jedisPool == null){
			jedisPool = new JedisPool(getJedisPoolConfig(), host, port, expire, password);
		}
	}
	
	private  JedisPool getJedisPool(){
		if(null == jedisPool){
			jedisPool = new JedisPool(getJedisPoolConfig(), host, port, expire, password);
		}
		return jedisPool;
	}
	
	private  JedisPoolConfig getJedisPoolConfig(){
		JedisPoolConfig config = new JedisPoolConfig();
        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
		config.setMaxTotal(maxTotal);
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(maxIdle);
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWaitMillis(maxWaitMillis);
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(true);
        return config;
	}
	
	/**
	 * get value from redis
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key){
		byte[] value = null;
		Jedis jedis = getJedisPool().getResource();
		try{
			value = jedis.get(key);
		}catch(Exception e){
			//无缓存
		}
		finally{
			
			getJedisPool().returnResourceObject(jedis);
		}
		return value;
	}
	
	/**
	 * get value from redis  (String)
	 * @param key
	 * @return
	 */
	public String get(String key){
		String value = null;
		Jedis jedis = getJedisPool().getResource();
		try{
			value = jedis.get(key);
		}catch(Exception e){
			//无缓存
		}
		finally{
			getJedisPool().returnResourceObject(jedis);
		}
		return value;
	}
	/**
	 * set 
	 * @param key
	 * @param value
	 * @return
	 */
	public byte[] set(byte[] key,byte[] value){
		Jedis jedis = getJedisPool().getResource();
		try{
			jedis.set(key,value);
			if(this.expire != 0){
				jedis.expire(key, this.expire);
		 	}
		}finally{
			getJedisPool().returnResourceObject(jedis);
		}
		return value;
	}
 
	/**
	 * set (String)
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key,String value){
		Jedis jedis = getJedisPool().getResource();
		try{
			jedis.set(key,value);
			if(this.expire != 0){
				jedis.expire(key, this.expire);
		 	}
		}finally{
			getJedisPool().returnResourceObject(jedis);
		}
		return value;
	}
	/**
	 * set 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public byte[] set(byte[] key,byte[] value,int expire){
		Jedis jedis = getJedisPool().getResource();
		try{
			jedis.set(key,value);
			if(expire != 0){
				jedis.expire(key, expire);
		 	}
		}finally{
			getJedisPool().returnResourceObject(jedis);
		}
		return value;
	}
	
	/**
	 * set (String)
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public String set(String key,String value,int expire){
		Jedis jedis = getJedisPool().getResource();
		try{
			jedis.set(key,value);
			if(expire != 0){
				jedis.expire(key, expire);
		 	}
		}finally{
			getJedisPool().returnResourceObject(jedis);
		}
		return value;
	}
	/**
	 * del
	 * @param key
	 */
	public void del(byte[] key){
		Jedis jedis = getJedisPool().getResource();
		try{
			jedis.del(key);
		}finally{
			getJedisPool().returnResourceObject(jedis);
		}
	}
	
	/**
	 * del
	 * @param key(string)
	 */
	public void del(String key){
		Jedis jedis = getJedisPool().getResource();
		try{
			jedis.del(key);
		}finally{
			getJedisPool().returnResourceObject(jedis);
		}
	}
	
	/**
	 * flush
	 */
	public void flushDB(){
		Jedis jedis = getJedisPool().getResource();
		try{
			jedis.flushDB();
		}finally{
			getJedisPool().returnResourceObject(jedis);
		}
	}
	
	/**
	 * size
	 */
	public Long dbSize(){
		Long dbSize = 0L;
		Jedis jedis = getJedisPool().getResource();
		try{
			dbSize = jedis.dbSize();
		}finally{
			getJedisPool().returnResourceObject(jedis);
		}
		return dbSize;
	}

	/**
	 * keys
	 * @param regex
	 * @return
	 */
	public Set<byte[]> keys(String pattern){
		Set<byte[]> keys = null;
		Jedis jedis = getJedisPool().getResource();
		try{
			keys = jedis.keys(pattern.getBytes());
		}finally{
			getJedisPool().returnResourceObject(jedis);
		}
		return keys;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getExpire() {
		return expire;
	}
	public void setExpire(int expire) {
		this.expire = expire;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}
	public int getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}
	public long getMaxWaitMillis() {
		return maxWaitMillis;
	}
	public void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}
}
