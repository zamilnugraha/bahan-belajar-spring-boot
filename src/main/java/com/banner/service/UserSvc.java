package com.banner.service;

import java.util.Date;
import java.util.List;

import com.banner.dto.TokenDto;
import com.banner.dto.UserDto;

public interface UserSvc {
	public List<UserDto> findAll();
	public UserDto findOne(String name);
	public UserDto findOneById(String id);
	public void save(UserDto userDto);
	public String deleteUser(String id);
	public String deleteToken(String token);
	
	//change password
	public String changePassword(String oldPassword, String id);
	
	//new user
	public UserDto findUserByEmail(String email, String password);
	public UserDto loginWithLdap(String email);
	public void update(UserDto userDto, String id);
	
	//token insert
//	public TokenDto findTokenById(String id, String email, String password);
	public String updateStatus(String id);
	public List<Object[]> findAllRedis();
	public List<Object[]> findAllRedisLDAP();
	
	public void updateLastLogin(String email, Date lastLoginDate);
}
