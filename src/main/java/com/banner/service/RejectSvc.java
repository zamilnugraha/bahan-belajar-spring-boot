package com.banner.service;

import java.util.List;

import com.banner.dto.RejectDto;

public interface RejectSvc {
	
	public List<RejectDto> findAll();
	
	public List<RejectDto> findAllByContentId(String contentId);
	
	public void save(RejectDto rejectDto);
	
	public RejectDto findOneById(String id);
	
}
