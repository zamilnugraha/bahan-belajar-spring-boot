package com.banner.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banner.dao.ConfigDao;
import com.banner.dto.ConfigDto;
import com.banner.entity.ConfigEntity;
import com.banner.service.ConfigSvc;

@Service
@Transactional
public class ConfigSvcImpl implements ConfigSvc{
	
	@Autowired
	ConfigDao configDao;
	
	@Override
	public List<ConfigDto> findbyName(String name) {
		List<ConfigDto> listMaster = new ArrayList<>();
		try {
			List<ConfigEntity> listEntity = configDao.findByName(name);
			for(ConfigEntity entity: listEntity) {
				ConfigDto dto = new ConfigDto();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setDescription(entity.getDescription());
				dto.setValue(entity.getValue());
				dto.setType(entity.getType());
				dto.setRowStatus(entity.getRowStatus());
				dto.setRowUserId(entity.getRowUserId());
				dto.setRowTimeUpdate(entity.getRowTimeUpdate());
				dto.setModes(entity.getModes());
				listMaster.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listMaster;
	}

	@Override
	public String findOneByNameTypeString(String name) {
		String output = "";
		try {
			ConfigEntity entity = configDao.findOneByName(name);

			if( entity != null ) {
				output = entity.getValue();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return output;
	}

}
