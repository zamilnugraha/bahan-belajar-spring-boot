package com.banner.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banner.entity.Statistic;
import com.banner.entity.StatisticPK;

/**
 *
 * @author Chubb Team ( Fairuz Fatin, Bayu Sugara, Muhamad Zamil, Fhad Saleh )
 */
public interface StatisticDao extends JpaRepository<Statistic, StatisticPK>{

	public Statistic findOneById(String id);
}
