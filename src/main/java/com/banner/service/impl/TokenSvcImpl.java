package com.banner.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banner.common.SecurityUtil;
import com.banner.dao.RedisDao;
import com.banner.dao.TokenDao;
import com.banner.dto.TokenDto;
import com.banner.entity.Token;
import com.banner.service.TokenSvc;

@Service
@Transactional
public class TokenSvcImpl implements TokenSvc {

	@Autowired
	TokenDao tokenDao;
	
	@Autowired
	RedisDao redisDao;
	
	SimpleDateFormat frtm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@Override
	public void save(TokenDto tokenDto) {
		Token tokenLayout = new Token();
		tokenLayout.setId(tokenDto.getId());
		tokenLayout.setToken(tokenDto.getToken());
		tokenLayout.setRowStatus(tokenDto.getRowStatus());
		tokenLayout.setRowUserId(tokenDto.getRowUserId());
		tokenLayout.setRowTimeUpdate(tokenDto.getRowTimeUpdate());
		tokenLayout.setCreateDate(tokenDto.getCreateDate());
		tokenLayout.setCreateUserId(tokenDto.getCreateUserId());
		tokenLayout.setUserId(tokenDto.getUserId());
		tokenLayout.setGroupId(tokenDto.getGroupId());
		tokenDao.save(tokenLayout);		
	}
	
	@Override
	public TokenDto findTokenByToken(String token) {
		TokenDto tokenDto = new TokenDto();
		try {
			Token tokens = tokenDao.findTokenByToken(token);
			if (tokens != null){
				tokenDto.setId(tokens.getId());
				tokenDto.setCreateDate(tokens.getCreateDate());
				tokenDto.setCreateUserId(tokens.getCreateUserId());
				tokenDto.setRowStatus(tokens.getRowStatus());
				tokenDto.setRowUserId(tokens.getRowUserId());
				tokenDto.setRowTimeUpdate(tokens.getRowTimeUpdate());
				tokenDto.setToken(tokens.getToken());
				tokenDto.setUserId(tokens.getUserId());
				tokenDto.setGroupId(tokens.getGroupId());
			}else{
				tokenDto = null;
			}
			return tokenDto;
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error findByToken: "+ex.getMessage());
		}
		return tokenDto;
	}


	@Override
	public String updateCreatedDateFromToken(String token){
		String updateTime = "";
		try {
			Token newToken = new Token();
			Date date = new Date();
			long t  = date.getTime();
			java.sql.Date createDate = new java.sql.Date(t);
			updateTime = String.valueOf(tokenDao.updateToken(createDate, token));
			System.out.println("result Update: "+updateTime);
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Update Token: "+e.getMessage());
		}
		return updateTime;
	}
	
	@Override
	public String checkToken(String token){
		String resultToken = "";
		try {
			
			Token tokens = tokenDao.findTokenByToken(token); //(TokenDto) redisDao.get("TOKEN", "byToken/{token}");
			if (tokens == null){
				tokens = tokenDao.findTokenByToken(token);
			}
			if (tokens != null){
				Date rowsTimeUpdate = tokens.getRowTimeUpdate();
				long tc = rowsTimeUpdate.getTime();
				
				Date date = new Date();
				long t  = date.getTime();
				java.sql.Date sqlDate = new java.sql.Date(t);
				
				long diff = date.getTime() - rowsTimeUpdate.getTime();
				long diffSeconds = diff / 1000;         
				long diffMinutes = diff / (60 * 1000);         
				long diffHours = diff / (60 * 60 * 1000);
				
				if (diffHours > -4 && diffHours < 4){
					Calendar cal = Calendar.getInstance();
					cal.setTime(sqlDate);
					cal.add(cal.HOUR, 3);
					Date afterAddDate = frtm.parse(SecurityUtil.rowTimeUpdate(cal.getTime()));
					Integer updateToken = tokenDao.updateToken(afterAddDate, token);
					
					redisDao.delete("TOKEN_LOGIN", "byToken/"+token);
					TokenDto tokenDto = new TokenDto();
					tokenDto.setId(tokens.getId());
					tokenDto.setToken(token);
					tokenDto.setRowStatus("1");
					tokenDto.setRowUserId(tokens.getUserId());
					tokenDto.setRowTimeUpdate(frtm.parse(SecurityUtil.rowTimeUpdate(cal.getTime())));
					tokenDto.setCreateDate(frtm.parse(SecurityUtil.rowTimeUpdate(cal.getTime())));
					tokenDto.setCreateUserId(tokens.getCreateUserId());
					tokenDto.setUserId(tokens.getUserId());
					tokenDto.setGroupId(tokens.getGroupId());
					redisDao.put("TOKEN_LOGIN", "byToken/"+token, tokenDto);
				}
				
				resultToken = String.valueOf(diffHours);
			}
			
		}catch(Exception e){
			System.out.println("Error Check Token: "+e.getMessage());
			e.printStackTrace();
		}
		return resultToken;
	}
	
	
	@Override
	public String checkPrivilege(String token, String menuID){
		String resultToken = "";
		try {
						
			List<Object[]> mapPrivilege = (List<Object[]>) tokenDao.findPrivilegeByToken(menuID, token);
			if (mapPrivilege.size()<1){
				resultToken = "false";
			}else{
				resultToken = "true";
			}			
			
		}catch(Exception e){
			System.out.println("Error Check Privilege: "+e.getMessage());
			e.printStackTrace();
		}
		return resultToken;
	}
	
	@Override
	public HashMap<String, Object[]> checkedPrivilege(String token, String menuID){
		
		HashMap mapTemp = new HashMap();
		List<Object[]> mapPrivilege = tokenDao.mapFindPrivilegeByToken(menuID, token);
		if (mapPrivilege.size()>0){
			
			for (Object[] o : mapPrivilege){
				mapTemp.put("status", "100");
				mapTemp.put("result", "true");
				mapTemp.put("created", o[3]);
				mapTemp.put("read", o[4]);
				mapTemp.put("deleted", o[6]);
				mapTemp.put("updated", o[5]);
				//2018-08-08 test security reject
				mapTemp.put("approvalno", o[7]);
			}
			
		}else{
			mapTemp.put("status", "0");
			mapTemp.put("result", "false");
			mapTemp.put("created", "0");
			mapTemp.put("read", "0");
			mapTemp.put("deleted", "0");
			mapTemp.put("updated", "0");
		}
		
		return mapTemp;
	}
}
