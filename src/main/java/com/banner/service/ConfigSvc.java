package com.banner.service;

import java.util.List;

import com.banner.dto.ConfigDto;

public interface ConfigSvc {
	
	List<ConfigDto> findbyName(String name);

	String findOneByNameTypeString(String string);

}
