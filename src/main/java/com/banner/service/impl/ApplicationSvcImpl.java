package com.banner.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banner.dao.ApplicationDao;
import com.banner.dto.ApplicationDto;
import com.banner.entity.Application;
import com.banner.service.ApplicationSvc;

@Service
@Transactional
public class ApplicationSvcImpl implements ApplicationSvc {
	
	@Autowired
	ApplicationDao applicationDao;
	

	@Override
	public List<ApplicationDto> findAll() {
		// TODO Auto-generated method stub
		List<ApplicationDto> applicationDtos = new ArrayList<>();
		List<Application> applications = applicationDao.findAll();
		for (Application application : applications) {
			ApplicationDto applicationDto = new ApplicationDto();
			applicationDto.setId(application.getId().toString());
			applicationDto.setAppId(application.getAppId().toString());
			applicationDto.setAppKey(application.getAppKey().toString());
			applicationDto.setType(application.getType().toString());
			applicationDto.setRowStatus(application.getRowStatus().toString());
			applicationDto.setRowUserId(application.getRowUserId().toString());
			applicationDto.setRowTimeUpdate(application.getRowTimeUpdate());
			applicationDto.setCreateDate(application.getCreateDate());
			applicationDto.setCreateUserId(application.getCreateUserId().toString());
			applicationDto.setAppHost(application.getAppHost().toString());
			applicationDtos.add(applicationDto);
			
		}
		return applicationDtos;
	}

	@Override
	public ApplicationDto findOneById(String id) {
		// TODO Auto-generated method stub
		ApplicationDto applicationDto = new ApplicationDto();
		Application application = applicationDao.findOneById(id);
		if(application != null) {
			
			applicationDto.setId(application.getId());
			applicationDto.setAppId(application.getAppId());
			applicationDto.setAppKey(application.getAppKey());
			applicationDto.setType(application.getType());
			applicationDto.setRowStatus(application.getRowStatus());
			applicationDto.setRowUserId(application.getRowUserId());
			applicationDto.setRowTimeUpdate(application.getRowTimeUpdate());
			applicationDto.setCreateDate(application.getCreateDate());
			applicationDto.setCreateUserId(application.getCreateUserId());
			applicationDto.setAppHost(application.getAppHost());
			
		}
			
		return applicationDto;
	}

	@Override
	public void save(ApplicationDto applicationDto) {
		// TODO Auto-generated method stub
		Application application = new Application();
		
		application.setId(applicationDto.getId());
		application.setAppId(applicationDto.getAppId());
		application.setAppKey(applicationDto.getAppKey());
		application.setType(applicationDto.getType());
		application.setRowStatus(applicationDto.getRowStatus());
		application.setRowUserId(applicationDto.getRowUserId());
		application.setRowTimeUpdate(applicationDto.getRowTimeUpdate());
		application.setCreateDate(applicationDto.getCreateDate());
		application.setCreateUserId(applicationDto.getCreateUserId());
		application.setAppHost(applicationDto.getAppHost());
		applicationDao.save(application);
		
	}

	@Override
	public String deleteApplication(String id) {
		// TODO Auto-generated method stub
		String resultDelete = "";
		try {
			
			Application application = new Application();
			String rowStatus = "0";
			resultDelete = String.valueOf(applicationDao.deleteApplication(rowStatus, id));
			System.out.println("resultDelete : "+resultDelete);
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Delete Application : "+e.getMessage());
		}
		return resultDelete;
	}

	@Override
	public String checkApplicationByAppId(String AppId) {
		// TODO Auto-generated method stub
		String resultAppId = "";
		try {
			Application applications = applicationDao.findOneByAppId(AppId);
			if (applications == null){
				applications = applicationDao.findOneByAppId(AppId);	
			}
			if (applications != null) {
				resultAppId = applications.getAppId();	
			}
		
		}catch(Exception e){
			System.out.println("Error Check Application : "+ e.getMessage());
			e.printStackTrace();
		}
		return resultAppId;
	}

	@Override
	public String checkApplicationByAppKey(String appKey, String appId) {
		// TODO Auto-generated method stub
		String resultAppKey = "";
		try{
			Application applications = applicationDao.findOneByAppKey(appKey, appId);
			if(applications == null){
				applications = applicationDao.findOneByAppKey(appKey, appId);
			}
			
				if(applications != null){
				resultAppKey = applications.getAppKey();
			}
		}catch(Exception e){
				System.out.println("Error Check Application : "+e.getMessage());
				e.printStackTrace();
		}
		return resultAppKey;
	}
	
	@Override
	public String checkApplication(String appId, String appKey){
		String rstApp = "";
		try{
			Application applications = applicationDao.findOneByAppId(appId);
			if (applications != null){
				rstApp = "true";
			}else{
				Application applicationsd = applicationDao.findOneByAppKey(appKey);
				if (applicationsd != null){
					rstApp = "true";
				}else{
					rstApp = "false";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Check Application : "+e.getMessage());
		}
		return rstApp;
	}
	
	
}

