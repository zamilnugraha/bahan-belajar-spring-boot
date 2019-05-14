/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banner.service;

import java.util.HashMap;
import java.util.List;

import com.banner.dto.BannerDto;


/**
 *
 * @author eKreasi Chubb Team ( Fairuz Fatin, Bayu Sugara, Maretta, Muhamad
 *         Zamil, Fhad Saleh )
 */
public interface BannerSvc {

	public List<Object[]> levelAll();

	public List<BannerDto> findAll();

	public List<Object[]> findAllRedis(String menuID);

	public BannerDto findOneByTitle(String title);

	public void save(BannerDto bannerDto);

	public String deleteBanner(String id);

	public BannerDto findOneById(String id);

	public BannerDto findOneEditById(String id);

	public void update(BannerDto bannerDto, String id);

	public String updateStatus(String id, String approvalValue, String idUser);

	public String getApprovalValue(String parentID, String menuID);

	public List<HashMap> frontendBannerIndo();

	public List<HashMap> frontendBannerEn();

	public HashMap<String, Object[]> frontendBannerId();

	public HashMap<String, Object[]> frontendBannerEng();

	public List<HashMap> frontendPreviewBannerId();

	public List<HashMap> frontendPreviewBannerEng();

	public HashMap<String, Object[]> frontendBannerId(String id);

	public HashMap<String, Object[]> frontendBannerEng(String id);

	public List<Object[]> findAllRedisWithApproval(String approvalNoUser, String menuID);

	public List<Object[]> findAllApproval(String menuID);

	public String getApprovalReject(String idPrivacyPolicy, String menuID);

	public String rejectBanner(String id, String approvalUserId);

	public String rejectRemarkBanner(String id, String approvalNo, String menuID, String userId);

	public List<Object[]> pending(Integer approvalNoUser);
	
	public List<HashMap> frontendBannerIndoWithPublishDate();

	public List<HashMap> frontendBannerEnWithPublishDate();
}
