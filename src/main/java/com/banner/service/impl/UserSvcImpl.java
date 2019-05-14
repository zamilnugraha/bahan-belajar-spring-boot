package com.banner.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banner.common.SecurityUtil;
import com.banner.dao.TokenDao;
import com.banner.dao.UserDao;
import com.banner.dto.UserDto;
import com.banner.entity.Token;
import com.banner.entity.User;
import com.banner.service.UserSvc;

@Service
@Transactional
public class UserSvcImpl implements UserSvc {

	@Autowired
	UserDao userDao;
	
	@Autowired
	TokenDao tokenDao;
	
	SimpleDateFormat frtm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String frontendRowStatus = "1";
		
	@Override
	public List<UserDto> findAll() {
		List<UserDto> userDtos = new ArrayList<>();
		List<User> users = userDao.findAll();
		for(User user : users) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setGroupId(user.getGroupId());
			userDto.setStatus(user.getStatus());
			userDto.setSecretQuestion(user.getSecretQuestion());
			userDto.setSecretAnswer(user.getSecretAnswer());
			userDto.setStatus(user.getStatus());
			userDto.setRowStatus(user.getRowStatus());
			userDto.setRowUserId(user.getRowUserId());
			userDto.setRowTimeUpdate(user.getRowTimeUpdate());	
			userDto.setType(user.getType());
			userDtos.add(userDto);
		}
		return userDtos;
	}
	
	@Override
	public List<Object[]> findAllRedis() {
		List<Object[]> userDtos = new ArrayList<>();
		List<User> users = userDao.findAllByRowStatusAndTypeOrderByNameAsc("1", "NO LDAP");
		List listArray = new ArrayList();
		for(User o: users) {
			HashMap map = new HashMap();
			String rowTimeUpdate = SecurityUtil.rowTimeUpdate(o.getRowTimeUpdate());
			map.put("id", o.getId());
			map.put("name", o.getName());
			map.put("email", o.getEmail());
			map.put("password", o.getPassword());
			map.put("groupId", o.getGroupId());
			map.put("status", o.getStatus());
			map.put("secretQuestion", o.getSecretQuestion());
			map.put("secretAnswer", o.getSecretAnswer());
			map.put("rowStatus", o.getRowStatus());
			map.put("rowUserId", o.getRowUserId());
			map.put("rowTimeUpdate", rowTimeUpdate);
			map.put("type", o.getType());
			listArray.add(map);
		}
		userDtos = listArray;
		return userDtos;
	}

	@Override
	public UserDto findOneById(String id) {
		UserDto userDto = new UserDto();
		User user = userDao.findUserByID(id);
		SecurityUtil util = new SecurityUtil();
		if(user != null) {
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setGroupId(user.getGroupId());
			userDto.setStatus(user.getStatus());
			userDto.setSecretQuestion(user.getSecretQuestion());
			userDto.setSecretAnswer(user.getSecretAnswer());
			userDto.setStatus(user.getStatus());
			userDto.setRowStatus(user.getRowStatus());
			userDto.setRowUserId(user.getRowUserId());
			userDto.setRowTimeUpdate(user.getRowTimeUpdate());		
			userDto.setType(user.getType());
		}	
		return userDto;
	}
	
	@Override
	public UserDto findOne(String name) {
		UserDto userDto = new UserDto();
		User user = userDao.findOneByName(name);
		if(user != null) {
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setGroupId(user.getGroupId());
			userDto.setStatus(user.getStatus());
			userDto.setSecretQuestion(user.getSecretQuestion());
			userDto.setSecretAnswer(user.getSecretAnswer());
			userDto.setStatus(user.getStatus());
			userDto.setRowStatus(user.getRowStatus());
			userDto.setRowUserId(user.getRowUserId());
			userDto.setRowTimeUpdate(user.getRowTimeUpdate());	
			userDto.setType(user.getType());	
		}	
		return userDto;
	}

	@Override
	public void save(UserDto userDto) {
		User userLayout = new User();
		userLayout.setId(userDto.getId());
		userLayout.setName(userDto.getName());
		userLayout.setEmail(userDto.getEmail());
		userLayout.setPassword(userDto.getPassword());
		userLayout.setGroupId(userDto.getGroupId());
		userLayout.setStatus(userDto.getStatus());
		userLayout.setSecretQuestion(userDto.getSecretQuestion());
		userLayout.setSecretAnswer(userDto.getSecretAnswer());
		userLayout.setStatus(userDto.getStatus());
		userLayout.setRowStatus(userDto.getRowStatus());
		userLayout.setRowUserId(userDto.getRowUserId());
		userLayout.setRowTimeUpdate(userDto.getRowTimeUpdate());	
		userLayout.setType(userDto.getType());
		userLayout.setLastLoginDate(userDto.getLastLoginDate());
		userDao.save(userLayout);		
	}
	
	@Override
	public void update(UserDto userDto, String idUser) {
		User userLayout = new User();
		userLayout.setId(idUser);
		userLayout.setName(userDto.getName());
		userLayout.setEmail(userDto.getEmail());
		userLayout.setPassword(userDto.getPassword());
		userLayout.setGroupId(userDto.getGroupId());
		userLayout.setStatus(userDto.getStatus());
		userLayout.setSecretQuestion(userDto.getSecretQuestion());
		userLayout.setSecretAnswer(userDto.getSecretAnswer());
		userLayout.setStatus(userDto.getStatus());
		userLayout.setRowStatus(userDto.getRowStatus());
		userLayout.setRowUserId(userDto.getRowUserId());
		userLayout.setRowTimeUpdate(userDto.getRowTimeUpdate());
		userLayout.setType(userDto.getType());
		userDao.save(userLayout);		
	}
	
	@Override
	public UserDto findUserByEmail(String email, String password) {
		UserDto userDto = new UserDto();
		User user = userDao.findUserByEmail(email, password);
		if(user != null) {
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setGroupId(user.getGroupId());
			userDto.setStatus(user.getStatus());
			userDto.setSecretQuestion(user.getSecretQuestion());
			userDto.setSecretAnswer(user.getSecretAnswer());
			userDto.setStatus(user.getStatus());
			userDto.setRowStatus(user.getRowStatus());
			userDto.setRowUserId(user.getRowUserId());
			userDto.setRowTimeUpdate(user.getRowTimeUpdate());	
			userDto.setType(user.getType());
		}
		return userDto;
	}
	
	@Override
	public UserDto loginWithLdap(String email) {
		UserDto userDto = new UserDto();
		try {
		User user = userDao.findOneByEmailIgnoreCase(email);
		if(user != null) {
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setGroupId(user.getGroupId());
			userDto.setStatus(user.getStatus());
			userDto.setSecretQuestion(user.getSecretQuestion());
			userDto.setSecretAnswer(user.getSecretAnswer());
			userDto.setStatus(user.getStatus());
			userDto.setRowStatus(user.getRowStatus());
			userDto.setRowUserId(user.getRowUserId());
			userDto.setRowTimeUpdate(user.getRowTimeUpdate());	
			userDto.setType(user.getType());
			userDto.setLastLoginDate(user.getLastLoginDate());
		}
		} catch (Exception e) {
			System.out.println("Error Login LDAP: " + e.getMessage());
			e.printStackTrace();
		}
		return userDto;
	}

	@Override
	public String deleteUser(String id) {
		// TODO Auto-generated method stub
		
		String resultDelete = "";
		try {
			
			User user = new User();
			String rowStatus = "0";
			Date rowTimeUpdate = frtm.parse(SecurityUtil.rowTimeUpdate(new Date()));
			resultDelete = String.valueOf(userDao.deleteUser(rowStatus, rowTimeUpdate, id));
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Delete User : "+e.getMessage());
		}
		return resultDelete;
	}
	
	@Override
	public String deleteToken(String token) {
		// TODO Auto-generated method stub
		String resultDelete = "";
		try {
			Token tokens = new Token();
			resultDelete = String.valueOf(userDao.deleteToken(token));
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Delete Token : "+e.getMessage());
		}
		return resultDelete;
	}


	@Override
	public String updateStatus(String id) {
		String resultStatus = "";
		try {
			User user = new User();
			String approvalStatus = "100";
			resultStatus = String.valueOf(userDao.updateStatus(approvalStatus, id));
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Delete News : "+e.getMessage());
		}
		return resultStatus;
	}

	@Override
	public String changePassword(String oldPassword, String id) {
		String resultPassword = "";
		try {
			Date rowTimeUpdate = frtm.parse(SecurityUtil.createdDate());
			resultPassword = String.valueOf(userDao.changePassword(oldPassword, rowTimeUpdate, id));
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Change Password : " + e.getMessage());
		}
		return resultPassword;
	}

	@Override
	public List<Object[]> findAllRedisLDAP() {
		List<Object[]> userDtos = new ArrayList<>();
		List<User> users = userDao.findAllByRowStatusAndTypeOrderByNameAsc("1", "LDAP");
		List listArray = new ArrayList();
		for(User o: users) {
			HashMap map = new HashMap();
			String rowTimeUpdate = SecurityUtil.rowTimeUpdate(o.getRowTimeUpdate());
			map.put("id", o.getId());
			map.put("name", o.getName());
			map.put("email", o.getEmail());
			map.put("password", o.getPassword());
			map.put("groupId", o.getGroupId());
			map.put("status", o.getStatus());
			map.put("secretQuestion", o.getSecretQuestion());
			map.put("secretAnswer", o.getSecretAnswer());
			map.put("rowStatus", o.getRowStatus());
			map.put("rowUserId", o.getRowUserId());
			map.put("rowTimeUpdate", rowTimeUpdate);
			map.put("type", o.getType());
			listArray.add(map);
		}
		userDtos = listArray;
		return userDtos;
	}

	@Override
	public void updateLastLogin(String email, Date lastLoginDate) {
		User userEntity = userDao.findOneByEmail(email);
		
		if( userEntity != null ) {
			userEntity.setLastLoginDate(lastLoginDate);
			userDao.save(userEntity);
		}
		
	}

}
