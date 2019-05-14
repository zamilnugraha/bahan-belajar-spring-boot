package com.banner.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banner.common.SecurityUtil;
import com.banner.dao.BannerDao;
import com.banner.dao.RedisDao;
import com.banner.dto.BannerDto;
import com.banner.dto.UserDto;
import com.banner.entity.Banner;
import com.banner.service.BannerSvc;
import com.banner.service.ConfigSvc;
import com.banner.service.UserSvc;

@Service("bannerSvc")
@Transactional
public class BannerSvcImpl implements BannerSvc {

	@Autowired
	BannerDao bannerDao;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
    
    @Autowired
	RedisDao redisDao;
    
	@Autowired
	ConfigSvc configSvc;
	
	@Autowired
	UserSvc userSvC;
	
	// Declare variable global for frontend
	int frontendApprovalNo = 100;
	String frontendRowStatus = "1";
	static final Logger logger = LoggerFactory.getLogger(BannerSvcImpl.class);
	@Override
	public List<Object[]> levelAll(){
		List<Object[]> levelDtos = new ArrayList<>();
		
		String[] levelList = {"10", "20", "100"};
		int levelLength    = levelList.length; 
		
		for (int i=0; i<levelLength; i++){
			redisDao.delete("BANNER", "all/"+levelList[i]);
		}
		return levelDtos;
	}
	
	@Override
	public List<BannerDto> findAll() {
		// TODO Auto-generated method stub
		List<BannerDto> bannerDtos = new ArrayList<>();
		Sort sort = new Sort(Sort.Direction.DESC, "rowTimeUpdate");
		List<Banner> banners = bannerDao.findAll(sort);
		for(Banner banner: banners) {
			BannerDto bannerDto = new BannerDto();
			bannerDto.setId(banner.getId());
	        bannerDto.setTitle(banner.getTitle());
	        bannerDto.setDescription(banner.getDescription());
	        bannerDto.setImageFilename(banner.getImageFilename());
	        bannerDto.setDateStart(banner.getDateStart());
	        bannerDto.setDateFinish(banner.getDateFinish());
	        bannerDto.setApprovalNo(banner.getApprovalNo());
	        bannerDto.setApprovalUserId(banner.getApprovalUserId());
	        bannerDto.setRowStatus(banner.getRowStatus());
	        bannerDto.setRowUserId(banner.getRowUserId());
	        bannerDto.setRowTimeUpdate(banner.getRowTimeUpdate());
	        bannerDto.setLayoutId(banner.getLayoutId());
	        bannerDto.setDescriptionEn(banner.getDescriptionEn());
	        bannerDto.setTitleEn(banner.getTitleEn());
	        bannerDto.setUrl(banner.getUrl());
	        bannerDto.setCreateUserId(banner.getCreateUserId());	        
	        bannerDto.setShowText(banner.getShowText());
	        bannerDto.setCreateDate(banner.getCreateDate());
	        bannerDto.setApprovalDate(banner.getApprovalDate());
	        bannerDto.setRejectDate(banner.getRejectDate());
	        bannerDto.setRejectUserId(banner.getRejectUserId());
	        bannerDto.setLastUpdateDate(banner.getLastUpdateDate());
	        bannerDto.setSortNo(banner.getSortNo());
	        
			bannerDtos.add(bannerDto);
		}
		return bannerDtos;
	}
	
	@Override
	public List<Object[]> findAllRedis(String menuID) {
		List<Object[]> awardsDtos = new ArrayList<>();
		List listArray = new ArrayList();
		List<Object[]> valueDto = new ArrayList();
		try{
			List<Object[]> approvalData = bannerDao.findAllApprovalValue(menuID);
			List<Banner> values = bannerDao.findAllData();
			for(Banner o: values) {
				HashMap map = new HashMap();
				String rowTimeUpdate = SecurityUtil.rowTimeUpdate(o.getRowTimeUpdate());
				
				map.put("id", o.getId());
				map.put("title", o.getTitle());
				map.put("titleEn", o.getTitleEn());
				map.put("imageFilename", o.getImageFilename());
				map.put("description", o.getDescription());
				map.put("dateStart", o.getDateStart());
				map.put("dateFinish", o.getDateFinish());
				map.put("approvalNo", o.getApprovalNo());
				String approvalValue = "";
				
				if( o.getApprovalNo() > 1 && o.getApprovalNo() < 100 ) {
					int i = 0;
					for( Object[] approval : approvalData ) {
						i++;
	
						BigDecimal approvalDecimal = (BigDecimal) approval[2];
	
						if( o.getApprovalNo() == Integer.valueOf( approvalDecimal.intValue() ) ) {
							int nextApproval = ( approvalData.size() >= i ? i : i+1 );
							Object[] approvalLevel = approvalData.get(nextApproval);
							approvalValue = "Waiting for " + approvalLevel[3];
						}
					}
				} else {
					Object[] approvalLevel = approvalData.get(0);
					approvalValue = "Waiting for " + approvalLevel[3];
				}
								
				if (o.getApprovalNo() == 100){
					approvalValue = "Approved";
				}
				
				map.put("approvalValue", approvalValue);
				map.put("approvalUserId", o.getApprovalUserId());
				map.put("rowStatus", o.getRowStatus());
				map.put("rowUserId", o.getRowUserId());
				map.put("rowTimeUpdate", rowTimeUpdate);
				map.put("layoutId", o.getLayoutId());
				map.put("descriptionEn", o.getDescriptionEn());
				map.put("url", o.getUrl());
				map.put("createUserId", o.getCreateUserId());
				map.put("showText", o.getShowText());
				map.put("createDate", o.getCreateDate());
				map.put("createUserId", o.getCreateUserId());
				map.put("lasUpdateDate", o.getLastUpdateDate());
				map.put("approvalDate", o.getApprovalDate());
				map.put("approvalUserId", o.getApprovalUserId());
				map.put("rejectDate", o.getRejectDate());
				map.put("rejectUserId", o.getRejectUserId());
				map.put("sortNo", o.getSortNo());
				
				listArray.add(map);
			}	
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error findAllRedis Menu: "+e.getMessage());
		}
		valueDto = listArray;
		return valueDto;
	}
	
	@Override
	public List<Object[]> findAllRedisWithApproval(String approvalNoUser, String menuID){

		Integer approvalShow = 0;
		
		List<Object[]> approvalData = bannerDao.findAllApprovalValue(menuID);
		if( approvalNoUser.equalsIgnoreCase("10") ) {
			approvalShow = 1;
		} else {
			int x = 0;
			for( Object[] approval : approvalData ) {
				x++;
				BigDecimal approvalDecimal = (BigDecimal) approval[2];
				if( Integer.parseInt(approvalNoUser) == Integer.valueOf( approvalDecimal.intValue() ) && approvalShow == 0 ) {
					int nextApproval = x-2;
					Object[] approvalLevel = approvalData.get(nextApproval);
					BigDecimal approvalLevelDecimal = (BigDecimal) approvalLevel[2];
					approvalShow = Integer.valueOf(approvalLevelDecimal.intValue());
				}
			}
		}
		
		List<Object[]> awardsDtos = new ArrayList<>();
		List listArray = new ArrayList();
		List<Object[]> valueDto = new ArrayList();
		try{
			List<Banner> values = bannerDao.findAllRedisWithApproval();
			for(Banner o: values) {
				HashMap map = new HashMap();
				String rowTimeUpdate = SecurityUtil.rowTimeUpdate(o.getRowTimeUpdate());
				
				map.put("id", o.getId());
				map.put("title", o.getTitle());
				map.put("titleEn", o.getTitleEn());
				map.put("imageFilename", o.getImageFilename());
				map.put("description", o.getDescription());
				map.put("dateStart", o.getDateStart());
				map.put("dateFinish", o.getDateFinish());
				map.put("approvalNo", o.getApprovalNo());
				String approvalValue = "";

				if( o.getApprovalNo() > 1 && o.getApprovalNo() < 100 ) {
					int i = 0;
					for( Object[] approval : approvalData ) {
						i++;

						BigDecimal approvalDecimal = (BigDecimal) approval[2];

						if( o.getApprovalNo() == Integer.valueOf( approvalDecimal.intValue() ) ) {
							int nextApproval = ( approvalData.size() >= i ? i : i+1 );
							if (o.getRowStatus().equalsIgnoreCase("2")){
								nextApproval = i-1;
							}
							Object[] approvalLevel = approvalData.get(nextApproval);
							approvalValue = "Waiting for " + approvalLevel[3];
						}
					}
				} else if(o.getApprovalNo() == 100){
					Object[] approvalLevel = approvalData.get(approvalData.size() - 1);
					approvalValue = "Waiting for " + approvalLevel[3];
				} else {
					Object[] approvalLevel = approvalData.get(0);
					approvalValue = "Waiting for " + approvalLevel[3];
				}
								
				if (o.getApprovalNo() == 100 && o.getRowStatus().equalsIgnoreCase("1")){
					approvalValue = "Approved";
				}
				
				if(o.getRowStatus().equalsIgnoreCase("2")) {
					approvalValue = "Draft";
				}
				map.put("approvalValue", approvalValue);
				map.put("approvalUserId", o.getApprovalUserId());
				map.put("rowStatus", o.getRowStatus());
				map.put("rowUserId", o.getRowUserId());
				map.put("rowTimeUpdate", rowTimeUpdate);
				map.put("layoutId", o.getLayoutId());
				map.put("descriptionEn", o.getDescriptionEn());
				map.put("url", o.getUrl());
				map.put("createUserId", o.getCreateUserId());
				map.put("showText", o.getShowText());
				map.put("createDate", o.getCreateDate());
				map.put("createUserId", o.getCreateUserId());
				map.put("lasUpdateDate", o.getLastUpdateDate());
				map.put("approvalDate", o.getApprovalDate());
				map.put("approvalUserId", o.getApprovalUserId());
				map.put("rejectDate", o.getRejectDate());
				map.put("rejectUserId", o.getRejectUserId());
				map.put("sortNo", o.getSortNo());
				
				listArray.add(map);
			}	
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error findAllRedis Menu: "+e.getMessage());
		}
		valueDto = listArray;
		return valueDto;
	}

	@Override
	public BannerDto findOneByTitle(String title) {
		// TODO Auto-generated method stub
		BannerDto bannerDto = new BannerDto();
		Banner banner = bannerDao.findOneByTitle(title);
		if(banner != null) {
			bannerDto.setId(banner.getId());
	        bannerDto.setTitle(banner.getTitle());
	        bannerDto.setDescription(banner.getDescription());
	        bannerDto.setImageFilename(banner.getImageFilename());
	        bannerDto.setDateStart(banner.getDateStart());
	        bannerDto.setDateFinish(banner.getDateFinish());
	        bannerDto.setApprovalNo(banner.getApprovalNo());
	        bannerDto.setApprovalUserId(banner.getApprovalUserId());
	        bannerDto.setRowStatus(banner.getRowStatus());
	        bannerDto.setRowUserId(banner.getRowUserId());
	        bannerDto.setRowTimeUpdate(banner.getRowTimeUpdate());
	        bannerDto.setLayoutId(banner.getLayoutId());
	        bannerDto.setDescriptionEn(banner.getDescriptionEn());
	        bannerDto.setTitleEn(banner.getTitleEn());
	        bannerDto.setUrl(banner.getUrl());
	        bannerDto.setCreateUserId(banner.getCreateUserId());
	        bannerDto.setShowText(banner.getShowText());
	        bannerDto.setCreateDate(banner.getCreateDate());
	        bannerDto.setApprovalDate(banner.getApprovalDate());
	        bannerDto.setRejectDate(banner.getRejectDate());
	        bannerDto.setRejectUserId(banner.getRejectUserId());
	        bannerDto.setLastUpdateDate(banner.getLastUpdateDate());
	        bannerDto.setSortNo(banner.getSortNo());
		}	
		return bannerDto;
	}

	@Override
	public void save(BannerDto bannerDto) {
		Banner banner = new Banner();
		banner.setId(bannerDto.getId());
        banner.setTitle(bannerDto.getTitle());
        banner.setDescription(bannerDto.getDescription());
        banner.setImageFilename(bannerDto.getImageFilename());
        banner.setDateStart(bannerDto.getDateStart());
        banner.setDateFinish(bannerDto.getDateFinish());
        banner.setApprovalNo(bannerDto.getApprovalNo());
        banner.setApprovalUserId(bannerDto.getApprovalUserId());
        banner.setRowStatus(bannerDto.getRowStatus());
        banner.setRowUserId(bannerDto.getRowUserId());
        banner.setRowTimeUpdate(bannerDto.getRowTimeUpdate());
        banner.setLayoutId(bannerDto.getLayoutId());
        banner.setDescriptionEn(bannerDto.getDescriptionEn());
        banner.setTitleEn(bannerDto.getTitleEn());
        banner.setUrl(bannerDto.getUrl());
        banner.setCreateUserId(bannerDto.getCreateUserId());
        banner.setShowText(bannerDto.getShowText());
        banner.setCreateDate(bannerDto.getCreateDate());
        banner.setApprovalDate(bannerDto.getApprovalDate());
        banner.setRejectDate(bannerDto.getRejectDate());
        banner.setRejectUserId(bannerDto.getRejectUserId());
        banner.setLastUpdateDate(bannerDto.getLastUpdateDate());
        banner.setSortNo(bannerDto.getSortNo());
        
		bannerDao.save(banner);
	}

	@Override
	public String deleteBanner(String id){
		SimpleDateFormat frtm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String resultDelete = "";
		try {
			Banner banner = new Banner();
			String rowStatus = "0";
			Date rowTimeUpdate = frtm.parse(SecurityUtil.createdDate());
			resultDelete = String.valueOf(bannerDao.deleteBanner(rowStatus, rowTimeUpdate, id));
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Delete : "+e.getMessage());
		}
		return resultDelete;
	}
	
	@Override
	public BannerDto findOneById(String id){
		BannerDto bannerDto = new BannerDto();
		Banner banner = bannerDao.findOneById(id);
		if(banner != null) {
			bannerDto.setId(banner.getId());
	        bannerDto.setTitle(banner.getTitle());
	        bannerDto.setDescription(banner.getDescription());
	        bannerDto.setImageFilename(banner.getImageFilename());
	        bannerDto.setDateStart(banner.getDateStart());
	        bannerDto.setDateFinish(banner.getDateFinish());
	        bannerDto.setApprovalNo(banner.getApprovalNo());
	        bannerDto.setApprovalUserId(banner.getApprovalUserId());
	        bannerDto.setRowStatus(banner.getRowStatus());
	        bannerDto.setRowUserId(banner.getRowUserId());
	        bannerDto.setRowTimeUpdate(banner.getRowTimeUpdate());
	        bannerDto.setLayoutId(banner.getLayoutId());
	        bannerDto.setDescriptionEn(banner.getDescriptionEn());
	        bannerDto.setTitleEn(banner.getTitleEn());
	        bannerDto.setUrl(banner.getUrl());
	        bannerDto.setCreateUserId(banner.getCreateUserId());
	        bannerDto.setShowText(banner.getShowText());
	        bannerDto.setCreateDate(banner.getCreateDate());
	        bannerDto.setApprovalDate(banner.getApprovalDate());
	        bannerDto.setRejectDate(banner.getRejectDate());
	        bannerDto.setRejectUserId(banner.getRejectUserId());
	        bannerDto.setLastUpdateDate(banner.getLastUpdateDate());
	        bannerDto.setSortNo(banner.getSortNo());
		}	
		return bannerDto;
	}
	
	@Override
	public BannerDto findOneEditById(String id){
		BannerDto bannerDto = new BannerDto();
		Banner banner = bannerDao.findOneById(id);
		UserDto userApproval = userSvC.findOneById(banner.getApprovalUserId());
		UserDto rowUserId = userSvC.findOneById(banner.getRowUserId());
		UserDto createdUserId= userSvC.findOneById(banner.getCreateUserId());
		UserDto rejectUserId = userSvC.findOneById(banner.getRejectUserId());
		
		if(banner != null) {
			bannerDto.setId(banner.getId());
	        bannerDto.setTitle(banner.getTitle());
	        bannerDto.setDescription(banner.getDescription());
	        bannerDto.setImageFilename(banner.getImageFilename());
	        bannerDto.setDateStart(banner.getDateStart());
	        bannerDto.setDateFinish(banner.getDateFinish());
	        bannerDto.setApprovalNo(banner.getApprovalNo());
	        bannerDto.setApprovalUserId(userApproval.getName());
	        bannerDto.setRowStatus(banner.getRowStatus());
	        bannerDto.setRowUserId(rowUserId.getName());
	        bannerDto.setRowTimeUpdate(banner.getRowTimeUpdate());
	        bannerDto.setLayoutId(banner.getLayoutId());
	        bannerDto.setDescriptionEn(banner.getDescriptionEn());
	        bannerDto.setTitleEn(banner.getTitleEn());
	        bannerDto.setUrl(banner.getUrl());
	        bannerDto.setCreateUserId(createdUserId.getName());
	        bannerDto.setShowText(banner.getShowText());
	        bannerDto.setCreateDate(banner.getCreateDate());
	        bannerDto.setApprovalDate(banner.getApprovalDate());
	        bannerDto.setRejectDate(banner.getRejectDate());
	        bannerDto.setRejectUserId(rejectUserId.getName());
	        bannerDto.setLastUpdateDate(banner.getLastUpdateDate());
	        bannerDto.setSortNo(banner.getSortNo());
	       
		}	
		return bannerDto;
	}

	@Override
	public List<HashMap> frontendBannerIndo() {
		String urlUpload = configSvc.findOneByNameTypeString("url_upload_image");
		List<HashMap> bannerDtos = new ArrayList<>();

		List<Banner> data = bannerDao.findAllByApprovalNoAndRowStatusOrderBySortNoAscRowTimeUpdateDesc(frontendApprovalNo, frontendRowStatus);
		for (Banner obj : data) {
			HashMap mapTemp = new HashMap();
			mapTemp.put("title", obj.getTitle());
			mapTemp.put("image", urlUpload + "/" + obj.getImageFilename());
			mapTemp.put("description", obj.getDescription());
			mapTemp.put("url", obj.getUrl());
			mapTemp.put("showText", obj.getShowText());
			
			bannerDtos.add(mapTemp);
		}
		return bannerDtos;	
	}

	@Override
	public List<HashMap> frontendBannerEn() {
		String urlUpload = configSvc.findOneByNameTypeString("url_upload_image");
		List<HashMap> bannerDtos = new ArrayList<>();
		List<Banner> data = bannerDao.findAllByApprovalNoAndRowStatusOrderBySortNoAscRowTimeUpdateDesc(frontendApprovalNo, frontendRowStatus);
		for (Banner obj : data) {
			HashMap mapTemp = new HashMap();
			mapTemp.put("title", obj.getTitleEn());
			mapTemp.put("image", urlUpload + "/" + obj.getImageFilename());
			mapTemp.put("description", obj.getDescriptionEn());
			mapTemp.put("url", obj.getUrl());
			mapTemp.put("showText", obj.getShowText());
			
			bannerDtos.add(mapTemp);
		}
		return bannerDtos;	
	}		
	
	@Override
	public HashMap<String, Object[]> frontendBannerEng(String id) {
		HashMap mapTemp = new HashMap();
		
		String urlUpload = configSvc.findOneByNameTypeString("url_upload_image");
		Banner data = bannerDao.findOneById(id);

		mapTemp.put("id", data.getId());
		mapTemp.put("title", data.getTitleEn());
		mapTemp.put("description", data.getDescriptionEn());
		mapTemp.put("rowTimeUpdate", data.getRowTimeUpdate());
		mapTemp.put("showText", data.getShowText());
		mapTemp.put("image", urlUpload + "/" + data.getImageFilename());
		mapTemp.put("url", data.getUrl());
		
		return mapTemp;
	}

	@Override
	public HashMap<String, Object[]> frontendBannerId(String id) {
		HashMap mapTemp = new HashMap();

		String urlUpload = configSvc.findOneByNameTypeString("url_upload_image");
		Banner data = bannerDao.findOneById(id);

		mapTemp.put("id", data.getId());
		mapTemp.put("title", data.getTitle());
		mapTemp.put("description", data.getDescription());
		mapTemp.put("rowTimeUpdate", data.getRowTimeUpdate());
		mapTemp.put("showText", data.getShowText());
		mapTemp.put("image", urlUpload + "/" + data.getImageFilename());
		mapTemp.put("url", data.getUrl());
		
		return mapTemp;
	}

	@Override
	public String updateStatus(String id, String approvalValue, String idUser) {
		SimpleDateFormat frtm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String resultStatus = "";
		try {
			
			Banner news = new Banner();
			String approvalStatus = "100";
			Date rowTimeUpdate = frtm.parse(SecurityUtil.createdDate());
			resultStatus = String.valueOf(bannerDao.updateStatus(approvalValue, rowTimeUpdate, rowTimeUpdate, idUser, id));
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Update Status : "+e.getMessage());
		}
		return resultStatus;
	}


	@Override
	public void update(BannerDto bannerDto, String id) {
		Banner banner = new Banner();
		banner.setId(id);
        banner.setTitle(bannerDto.getTitle());
        banner.setDescription(bannerDto.getDescription());
        banner.setImageFilename(bannerDto.getImageFilename());
        banner.setDateStart(bannerDto.getDateStart());
        banner.setDateFinish(bannerDto.getDateFinish());
        banner.setApprovalNo(bannerDto.getApprovalNo());
        banner.setApprovalUserId(bannerDto.getApprovalUserId());
        banner.setRowStatus(bannerDto.getRowStatus());
        banner.setRowUserId(bannerDto.getRowUserId());
        banner.setRowTimeUpdate(bannerDto.getRowTimeUpdate());
        banner.setLayoutId(bannerDto.getLayoutId());
        banner.setDescriptionEn(bannerDto.getDescriptionEn());
        banner.setTitleEn(bannerDto.getTitleEn());
        banner.setUrl(bannerDto.getUrl());
        banner.setCreateUserId(bannerDto.getCreateUserId());
        banner.setShowText(bannerDto.getShowText());
        banner.setCreateDate(bannerDto.getCreateDate());
        banner.setApprovalDate(bannerDto.getApprovalDate());
        banner.setRejectDate(bannerDto.getRejectDate());
        banner.setRejectUserId(bannerDto.getRejectUserId());
        banner.setLastUpdateDate(bannerDto.getLastUpdateDate());
        banner.setSortNo(bannerDto.getSortNo());
        
		bannerDao.save(banner);
	}

	@Override
	public String getApprovalValue(String parentID, String menuID) {
		String resultStatus = "";
		try {
			
			List<Object[]> mapApp = bannerDao.mapApprovalValue(parentID, menuID);
			for (Object[] obj : mapApp){
				resultStatus = String.valueOf((BigDecimal) obj[7]);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Get Approval Value : "+e.getMessage());
		}
		return resultStatus;
	}
	
	@Override
	public HashMap<String, Object[]> frontendBannerEng(){
		String urlUpload = configSvc.findOneByNameTypeString("url_upload_image");
		HashMap mapTemp = new HashMap();
		List<Object[]> mapBannerEn = bannerDao.frontendLangEn();
		for (Object[] oEn: mapBannerEn){
			mapTemp.put("id", (String) oEn[0]);
			mapTemp.put("titleEn", (String) oEn[1]);
			mapTemp.put("imageFilename", urlUpload + "/" + (String) oEn[2]);
			mapTemp.put("descriptionEn", (String) oEn[3]);
			mapTemp.put("url", (String) oEn[4]);
		}
		return mapTemp;
	}
	
	@Override
	public HashMap<String, Object[]> frontendBannerId(){
		String urlUpload = configSvc.findOneByNameTypeString("url_upload_image");
		HashMap mapTemp = new HashMap();
		List<Object[]> mapBannerId = bannerDao.frontendLangIndo();
		for (Object[] oId: mapBannerId){
			mapTemp.put("id", (String) oId[0]);
			mapTemp.put("title", (String) oId[1]);
			mapTemp.put("imageFilename", urlUpload + "/" + (String) oId[2]);
			mapTemp.put("description", (String) oId[3]);
			mapTemp.put("url", (String) oId[4]);
		}
		return mapTemp;
	}
	
	@Override
	public List<HashMap> frontendPreviewBannerEng(){
		String urlUpload = configSvc.findOneByNameTypeString("url_upload_image");
		List<HashMap> bannerDtos = new ArrayList<>();
		List<Banner> data = bannerDao.findAllByRowStatusOrderByRowTimeUpdateDesc(frontendRowStatus);
		for (Banner obj : data) {
			HashMap mapTemp = new HashMap();
			mapTemp.put("title", obj.getTitle());
			mapTemp.put("image", urlUpload + "/" + obj.getImageFilename());
			mapTemp.put("description", obj.getDescription());
			mapTemp.put("url", obj.getUrl());
			mapTemp.put("showText", obj.getShowText());
			
			bannerDtos.add(mapTemp);
		}
		return bannerDtos;
	}
	
	@Override
	public List<HashMap> frontendPreviewBannerId(){
		String urlUpload = configSvc.findOneByNameTypeString("url_upload_image");
		List<HashMap> bannerDtos = new ArrayList<>();
		List<Banner> data = bannerDao.findAllByRowStatusOrderByRowTimeUpdateDesc(frontendRowStatus);
		for (Banner obj : data) {
			HashMap mapTemp = new HashMap();
			mapTemp.put("title", obj.getTitle());
			mapTemp.put("image", urlUpload + "/" + obj.getImageFilename());
			mapTemp.put("description", obj.getDescription());
			mapTemp.put("url", obj.getUrl());
			mapTemp.put("showText", obj.getShowText());
			
			bannerDtos.add(mapTemp);
		}
		return bannerDtos;
	}

	@Override
	public List<Object[]> findAllApproval(String menuID) {
		return bannerDao.findAllApprovalValue(menuID);
	}
		public String rejectBanner(String id, String approvalUserId){
			SimpleDateFormat frtm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String resultReject = "";
			try {
				
				String approvalNo = "1";
				Date rowTimeUpdate = frtm.parse(SecurityUtil.createdDate());
				resultReject = String.valueOf(bannerDao.rejectBanner(approvalNo, approvalUserId, rowTimeUpdate, rowTimeUpdate, id));
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Error Reject : "+e.getMessage());
			}
			return resultReject;
		}
		
		public String rejectRemarkBanner(String id, String approvalNo, String menuID, String userId){
			SimpleDateFormat frtm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String resultReject = "";
			try {
				
				String approvalRejectUserId = "";
				Banner  banner = bannerDao.findOneById(id);
				if (banner != null){
					approvalRejectUserId = banner.getCreateUserId();
				}
				
				Date rowTimeUpdate = frtm.parse(SecurityUtil.createdDate());
				resultReject = String.valueOf(bannerDao.rejectBanner(approvalNo, userId, rowTimeUpdate, rowTimeUpdate, id));
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Error Reject : "+e.getMessage());
			}
			return resultReject;
		}
		
		@Override
		public String getApprovalReject(String idBanner, String menuID) {
			String resultStatus = "";
			try {
				
				List<Object[]> mapApp = bannerDao.mapApprovalReject(idBanner, menuID);
				for (Object[] obj : mapApp){
					resultStatus = String.valueOf((BigDecimal) obj[7]);
				}
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Error Get Approval Value : "+e.getMessage());
			}
			return resultStatus;
		}
		
		@Override
		public List<Object[]> pending(Integer approvalNoUser) {
			return bannerDao.pending(approvalNoUser);
		}

		@Override
		public List<HashMap> frontendBannerIndoWithPublishDate() {
			// TODO Auto-generated method stub
			String urlUpload = configSvc.findOneByNameTypeString("url_upload_image");
			List<HashMap> bannerDtos = new ArrayList<>();
			List<BannerDto> data = getAllAboutDataWithFilter();
			for (BannerDto obj : data) {
				HashMap mapTemp = new HashMap();
				mapTemp.put("title", obj.getTitle());
				mapTemp.put("image", urlUpload + "/" + obj.getImageFilename());
				mapTemp.put("description", obj.getDescription());
				mapTemp.put("url", obj.getUrl());
				mapTemp.put("showText", obj.getShowText());
				bannerDtos.add(mapTemp);
			}
			return bannerDtos;
		}

		@Override
		public List<HashMap> frontendBannerEnWithPublishDate() {
			// TODO Auto-generated method stub
			String urlUpload = configSvc.findOneByNameTypeString("url_upload_image");
			List<HashMap> bannerDtos = new ArrayList<>();
			List<BannerDto> data = getAllAboutDataWithFilter();
			for (BannerDto obj : data) {
				HashMap mapTemp = new HashMap();
				mapTemp.put("title", obj.getTitleEn());
				mapTemp.put("image", urlUpload + "/" + obj.getImageFilename());
				mapTemp.put("description", obj.getDescriptionEn());
				mapTemp.put("url", obj.getUrl());
				mapTemp.put("showText", obj.getShowText());
				
				bannerDtos.add(mapTemp);
			}
			return bannerDtos;	
		}		
		private List<BannerDto> getAllAboutDataWithFilter(){
			String urlUpload = configSvc.findOneByNameTypeString("url_upload_image");
			HashMap mapTemp = new HashMap();
			try {
				List<BannerDto> listDto = (List<BannerDto>) redisDao.get("BANNER", "all");
				if (listDto == null) {
					listDto = new ArrayList();
					List<Banner> listEntity = bannerDao.findAllByApprovalNoAndRowStatusOrderBySortNoAscRowTimeUpdateDesc(frontendApprovalNo, frontendRowStatus);
					for (Banner entity : listEntity) {
						BannerDto entityDto = new BannerDto();
						entityDto.setId(entity.getId());
						entityDto.setTitle(entity.getTitle());
						entityDto.setTitleEn(entity.getTitleEn());
						entityDto.setImageFilename(entity.getImageFilename());
						entityDto.setDescription(entity.getDescription());
						entityDto.setDescriptionEn(entity.getDescriptionEn());
						entityDto.setDateStart(entity.getDateStart());
						entityDto.setDateFinish(entity.getDateFinish());
						entityDto.setApprovalNo(entity.getApprovalNo());
						entityDto.setApprovalUserId(entity.getApprovalUserId());
						entityDto.setRowStatus(entity.getRowStatus());
						entityDto.setRowUserId(entity.getRejectUserId());
						entityDto.setRowTimeUpdate(entity.getRowTimeUpdate());
						entityDto.setCreateDate(entity.getCreateDate());
						entityDto.setCreateUserId(entity.getCreateUserId());
						entityDto.setApprovalDate(entity.getApprovalDate());
						entityDto.setRejectDate(entity.getRejectDate());
						entityDto.setRejectUserId(entity.getRejectUserId());
						entityDto.setLastUpdateDate(entity.getLastUpdateDate());
						entityDto.setUrl(entity.getUrl());
						entityDto.setLayoutId(entity.getLayoutId());
						entityDto.setShowText(entity.getShowText());
						entityDto.setSortNo(entity.getSortNo());
						listDto.add(entityDto);
					}
					redisDao.put("BANNER", "all", listDto);
				}
				// filter current date > publishdate start , current date <
				// publishDateFinish;
				SimpleDateFormat sdfFormat = new SimpleDateFormat(configSvc.findOneByNameTypeString("date_format"));
				Date currentDate = sdfFormat.parse(SecurityUtil.createdDate());
				List<BannerDto> listFilter = new ArrayList();
				for (BannerDto dto : listDto) {
					if ((currentDate.compareTo(dto.getDateStart()) > 0)
							&& (currentDate.compareTo(dto.getDateFinish()) < 0)) {
						BannerDto ab = new BannerDto();
						ab.setId(dto.getId());
						ab.setTitle(dto.getTitle());
						ab.setTitleEn(dto.getTitleEn());
						ab.setImageFilename(dto.getImageFilename());
						ab.setDescription(dto.getDescription());
						ab.setDescriptionEn(dto.getDescriptionEn());
						ab.setDateStart(dto.getDateStart());
						ab.setDateFinish(dto.getDateFinish());
						ab.setApprovalNo(dto.getApprovalNo());
						ab.setApprovalUserId(dto.getApprovalUserId());
						ab.setRowStatus(dto.getRowStatus());
						ab.setRowUserId(dto.getRejectUserId());
						ab.setRowTimeUpdate(dto.getRowTimeUpdate());
						ab.setCreateDate(dto.getCreateDate());
						ab.setLayoutId(dto.getLayoutId());
						ab.setUrl(dto.getUrl());
						ab.setShowText(dto.getShowText());
						ab.setSortNo(dto.getSortNo());
						ab.setApprovalDate(dto.getApprovalDate());
						ab.setRejectDate(dto.getRejectDate());
						ab.setRejectUserId(dto.getRejectUserId());
						ab.setLastUpdateDate(dto.getLastUpdateDate());
						listFilter.add(ab);
					}

				}
				//sorting
				Collections.sort(listFilter, BannerDto.publishStartDateComparator);
	            return listFilter;
		}catch(Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.info(errors.toString());
			return null;
			
		    }
		}
}
