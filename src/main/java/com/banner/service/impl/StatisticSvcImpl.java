package com.banner.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banner.dao.StatisticDao;
import com.banner.dto.StatisticDto;
import com.banner.entity.Statistic;
import com.banner.service.StatisticSvc;

@Service("statisticSvc")
@Transactional
public class StatisticSvcImpl implements StatisticSvc{

	@Autowired
	StatisticDao statisticDao;
	
	@Override
	public void save(StatisticDto statisticDto){
		Statistic statistic = new Statistic();
		
		statistic.setId(statisticDto.getId());//1
		statistic.setParentId(statisticDto.getParentId());//2
		statistic.setType(statisticDto.getType());//3
		statistic.setIp(statisticDto.getIp());//4
		statistic.setUserId(statisticDto.getUserId());//5
		statistic.setRowStatus(statisticDto.getRowStatus());//6
		statistic.setRowUserId(statisticDto.getRowUserId());//7
		statistic.setRowTimeUpdate(statisticDto.getRowTimeUpdate());//8
		statistic.setCreateDate(statisticDto.getCreateDate());//9
		statistic.setCreateUserId(statisticDto.getCreateUserId());//10
		
		statisticDao.save(statistic);
	}
}
