package com.banner.dao;

public interface RedisDao {	
	public void put(String group, String key, Object value);
	public Object get(String group, String key);	
	public void delete(String group, String key);
	public void setCounter(String group, String key);
	public long getCounter(String group, String key);
	public void deleteAll(String group);	
}
