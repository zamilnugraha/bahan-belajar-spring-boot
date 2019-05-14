package com.banner.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.banner.dao.RedisDao;

@Repository
public class RedisDaoImpl implements RedisDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void put(String group, String key, Object value) {		
		redisTemplate.opsForHash().put(group, key, value);		
	}

	@Override
	public Object get(String group, String key) {
		return redisTemplate.opsForHash().get(group, key);		
	}
	
	@Override
	public void delete(String group, String key) {
		redisTemplate.opsForHash().delete(group, key);		
	}
	
	@Override
	public void setCounter(String group, String key) {		
		redisTemplate.opsForHash().put(group, key + "_counter", 1l);		
	}

	@Override
	public long getCounter(String group, String key) {		
		return (Long) redisTemplate.opsForHash().get(group, key + "_counter");		
	}

	@Override
	public void deleteAll(String group) {
		redisTemplate.delete(group);
	}
}
