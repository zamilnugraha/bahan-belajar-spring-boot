package com.banner.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.banner.dto.TokenDto;


public interface TokenSvc {
	
	public void save(TokenDto tokenDto);
	public TokenDto findTokenByToken (String token);
	public String updateCreatedDateFromToken( String token);	
	public String checkToken(String token);	
	
	public String checkPrivilege(String token, String menuID);	
	public HashMap<String, Object[]> checkedPrivilege(String token, String menuID);
	
}
