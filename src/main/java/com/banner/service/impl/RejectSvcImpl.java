package com.banner.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.banner.dao.RejectDao;
import com.banner.dto.BannerDto;
import com.banner.dto.RejectDto;
import com.banner.dto.UserDto;
import com.banner.entity.Reject;
import com.banner.service.BannerSvc;
import com.banner.service.RejectSvc;
import com.banner.service.UserSvc;

@Service
@Transactional
public class RejectSvcImpl implements RejectSvc {
	
	@Autowired
	RejectDao rejectDao;
	
	@Autowired
	UserSvc userSvc;
	
	@Autowired
	BannerSvc bannerSvc;
	
	@Override
	public List<RejectDto> findAll() {
		// TODO Auto-generated method stub
		List<RejectDto> rejectDtos = new ArrayList<>();
		Sort sort = new Sort(Sort.Direction.DESC, "rowTimeUpdate");
		List<Reject> listReject = rejectDao.findAll(sort);
		for(Reject reject : listReject) {
			RejectDto rejectDto = new RejectDto();
			
			rejectDto.setId(reject.getId());
			rejectDto.setContentId(reject.getContentId());
			rejectDto.setRejectNote(reject.getRejectNote());
			rejectDto.setCreateDate(reject.getCreateDate());
			rejectDto.setCreateUserId(reject.getCreateUserId());
			rejectDto.setRowStatus(reject.getRowStatus());
			rejectDto.setRowTimeUpdate(reject.getRowTimeUpdate());
			
			rejectDtos.add(rejectDto);
		}
		
		
		return rejectDtos;
	}

	@Override
	public void save(RejectDto rejectDto) {
		// TODO Auto-generated method stub
		Reject reject = new Reject();
		reject.setId(rejectDto.getId());
		reject.setContentId(rejectDto.getContentId());
		reject.setRejectNote(rejectDto.getRejectNote());
		reject.setCreateDate(rejectDto.getCreateDate());
		reject.setCreateUserId(rejectDto.getCreateUserId());
		reject.setRowStatus(rejectDto.getRowStatus());
		reject.setRowTimeUpdate(rejectDto.getRowTimeUpdate());
		
		rejectDao.save(reject);
	}

	@Override
	public RejectDto findOneById(String id) {
		// TODO Auto-generated method stub
		RejectDto rejectDto = new RejectDto();
		Reject reject = rejectDao.findOneById(id);
		
		if(reject != null) {
			rejectDto.setId(reject.getId());
			rejectDto.setContentId(reject.getContentId());
			rejectDto.setRejectNote(reject.getRejectNote());
			rejectDto.setCreateDate(reject.getCreateDate());
			rejectDto.setCreateUserId(reject.getCreateUserId());
			rejectDto.setRowStatus(reject.getRowStatus());
			rejectDto.setRowTimeUpdate(reject.getRowTimeUpdate());
		}
		return rejectDto;
	}

	@Override
	public List<RejectDto> findAllByContentId(String contentId) {
		// TODO Auto-generated method stub
		List<RejectDto> rejectDtos = new ArrayList<>();
		List<Reject> rejects = rejectDao.findAllByContentIdOrderByRowTimeUpdateDesc(contentId);
		for(Reject reject : rejects) {
			RejectDto rejectDto = new RejectDto();
			BannerDto content = bannerSvc.findOneById(reject.getContentId());
			UserDto createUser = userSvc.findOneById(reject.getCreateUserId());
			
			rejectDto.setId(reject.getId());
			rejectDto.setContentId(content.getTitleEn());
			rejectDto.setRejectNote(reject.getRejectNote());
			rejectDto.setCreateDate(reject.getCreateDate());
			rejectDto.setCreateUserId(createUser.getName());
			rejectDto.setRowStatus(reject.getRowStatus());
			rejectDto.setRowTimeUpdate(reject.getRowTimeUpdate());
			
			rejectDtos.add(rejectDto);
		}
	
		return rejectDtos;
	}
	
}
