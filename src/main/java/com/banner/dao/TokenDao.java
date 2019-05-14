package com.banner.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.banner.dto.TokenDto;
import com.banner.entity.Token;
import com.banner.entity.TokenPK;


public interface TokenDao extends JpaRepository<Token, TokenPK>{
	
//	@Query(value="SELECT * FROM EKR_99_TOKEN WHERE TOKEN = ?", nativeQuery=true)
	@Query("select t from Token t where t.token = ?1 and t.rowStatus = 1")
	public Token findTokenByToken(String token);
	
	@Query
	(value="SELECT a.*, b.ID "+
			"FROM EKR_99_TOKEN a, EKR_98_USER "+
			"ON a.ID = b.ID "+
			"WHERE a.USER_ID=b.ID", nativeQuery=true)
	public Token findTokenByUserId(String userId);

	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE EKR_99_TOKEN SET ROW_STATUS = ?1 where ID = ?2",nativeQuery=true)
	public Integer deleteToken(String rowStatus, String id);
	
	//PERUBAHAN TANGGAL 09042018
	@Query("select t from Token t where t.token = ?1")
	public TokenDto searchTokenByToken(String token);
	
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE EKR_99_TOKEN SET ROW_TIME_UPDATE = ?1 where TOKEN = ?2",nativeQuery=true)
	public Integer updateToken(Date createDate, String token);
	
	@Query
	(value="SELECT * FROM EKR_98_USER_GROUP_MENU "
			+ "WHERE MENU_ID = (SELECT ID FROM EKR_98_MENU WHERE URL = ?1) "
			+ "AND PARENT_ID = (SELECT GROUP_ID FROM EKR_98_USER WHERE ID = (SELECT USER_ID FROM EKR_99_TOKEN WHERE TOKEN = ?2 AND ROW_STATUS = 1))", nativeQuery=true)
	public List<Object[]> findPrivilegeByToken(String menuId, String token);
	
	@Query
	(value="SELECT * FROM EKR_98_USER_GROUP_MENU "
			+ "WHERE MENU_ID = (SELECT ID FROM EKR_98_MENU WHERE URL = ?1) "
			+ "AND PARENT_ID = (SELECT GROUP_ID FROM EKR_98_USER WHERE ID = (SELECT USER_ID FROM EKR_99_TOKEN WHERE TOKEN = ?2 AND ROW_STATUS = 1))", nativeQuery=true)
	public List<Object[]> mapFindPrivilegeByToken(String menuId, String token);
	
}
