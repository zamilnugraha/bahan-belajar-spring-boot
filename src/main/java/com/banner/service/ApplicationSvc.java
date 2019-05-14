package com.banner.service;

import java.util.List;

import com.banner.dto.ApplicationDto;
import com.banner.entity.Application;

public interface ApplicationSvc {
	
	public List<ApplicationDto> findAll();
	public ApplicationDto findOneById(String id);
	public void save(ApplicationDto applicationDto);
	public String deleteApplication(String id);
	public String checkApplicationByAppId(String appId);
	public String checkApplicationByAppKey(String appKey, String appId);
	
	public String checkApplication(String appId, String appKey);

}
