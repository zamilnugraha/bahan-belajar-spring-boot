package com.banner.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banner.entity.Reject;
import com.banner.entity.RejectPK;

public interface RejectDao extends JpaRepository<Reject, RejectPK>{
	
	public Reject findOneById(String id);
	
	public Reject findOneByContentId(String contentId);

	public List<Reject> findAllByContentIdOrderByRowTimeUpdateDesc(String contenId);
	
	
}
