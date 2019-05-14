package com.banner.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.banner.entity.Application;
import com.banner.entity.ApplicationPK;

public interface ApplicationDao extends JpaRepository<Application, ApplicationPK>{
	
	public Application findOneById(String id);
	public Application findOneByAppId(String appId);
	public Application findOneByAppKey(String appKey);
	
	/*@Query(value="SELECT*FROM EKR_99_APPLICATION WHERE APP_ID = ?",nativeQuery=true)
	public Application findOneByAppId(String appId);*/
	
	
	@Query(value="SELECT*FROM EKR_99_APPLICATION WHERE APP_KEY = ? AND APP_ID = ?",nativeQuery=true)
	public Application findOneByAppKey(String appKey, String appId);

    
	
    @Modifying(clearAutomatically = true)
	@Query(value="UPDATE EKR_99_APPLICATION SET ROW_STATUS = ? WHERE id = ?",nativeQuery=true)
	public Integer deleteApplication(String rowStatus, String id);

}
