package com.banner.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banner.entity.ConfigEntity;
import com.banner.entity.ConfigPKEntity;

public interface ConfigDao extends JpaRepository<ConfigEntity, ConfigPKEntity>{

	public ConfigEntity findOneByName(String name);
	public List<ConfigEntity> findByName(String name);


}
