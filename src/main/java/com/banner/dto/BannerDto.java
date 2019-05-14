/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banner.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author eKreasi Chubb Team ( Fairuz Fatin, Bayu Sugara, Maretta, Muhamad Zamil, Fhad Saleh )
 */
public class BannerDto implements Serializable {
	
	private static final long serialVersionUID = 1L;	
    
	private String id;
    private String title;
    private String titleEn;
    private String imageFilename;
    private String description;
    private String descriptionEn;
    private Date dateStart;
    private Date dateFinish;
    private int approvalNo;
    private String approvalUserId;
    private String rowStatus;
    private String rowUserId;
    private Date rowTimeUpdate;
    private String imageAlt;
    private String imageAltEn;
    private String layoutId;
    private String url;
    private Date publishDate;
    private Date createDate;
    private String createUserId;
    private String publishUserId;
    private String showText;
    private Date approvalDate;
	private Date rejectDate;
	private String rejectUserId;
	private Date lastUpdateDate;
	private int sortNo;
    
	public String getShowText() {
		return showText;
	}
	public void setShowText(String showText) {
		this.showText = showText;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageFilename() {
		return imageFilename;
	}
	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateFinish() {
		return dateFinish;
	}
	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}
	public int getApprovalNo() {
		return approvalNo;
	}
	public void setApprovalNo(int approvalNo) {
		this.approvalNo = approvalNo;
	}
	public String getApprovalUserId() {
		return approvalUserId;
	}
	public void setApprovalUserId(String approvalUserId) {
		this.approvalUserId = approvalUserId;
	}
	public String getRowStatus() {
		return rowStatus;
	}
	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}
	public String getRowUserId() {
		return rowUserId;
	}
	public void setRowUserId(String rowUserId) {
		this.rowUserId = rowUserId;
	}
	public Date getRowTimeUpdate() {
		return rowTimeUpdate;
	}
	public void setRowTimeUpdate(Date rowTimeUpdate) {
		this.rowTimeUpdate = rowTimeUpdate;
	}
	public String getImageAlt() {
		return imageAlt;
	}
	public void setImageAlt(String imageAlt) {
		this.imageAlt = imageAlt;
	}
	public String getLayoutId() {
		return layoutId;
	}
	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}
	public String getImageAltEn() {
		return imageAltEn;
	}
	public void setImageAltEn(String imageAltEn) {
		this.imageAltEn = imageAltEn;
	}
	public String getDescriptionEn() {
		return descriptionEn;
	}
	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}
	public String getTitleEn() {
		return titleEn;
	}
	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}
	

//	public String getShortLink() {
//		return shortLink;
//	}
//	public void setShortLink(String shortLink) {
//		this.shortLink = shortLink;
//	}
//	public String getLongLink() {
//		return longLink;
//	}
//	public void setLongLink(String longLink) {
//		this.longLink = longLink;
//	}
//	public Date getDropDate() {
//		return dropDate;
//	}
//	public void setDropDate(Date dropDate) {
//		this.dropDate = dropDate;
//	}
//	public String getDropUserId() {
//		return dropUserId;
//	}
//	public void setDropUserId(String dropUserId) {
//		this.dropUserId = dropUserId;
//	}
//	public String getDropReason() {
//		return dropReason;
//	}
//	public void setDropReason(String dropReason) {
//		this.dropReason = dropReason;
//	}
//	public String getShowCount() {
//		return showCount;
//	}
//	public void setShowCount(String showCount) {
//		this.showCount = showCount;
//	}
//	public int getShowTotal() {
//		return showTotal;
//	}
//	public void setShowTotal(int showTotal) {
//		this.showTotal = showTotal;
//	}
//	public String getClickCount() {
//		return clickCount;
//	}
//	public void setClickCount(String clickCount) {
//		this.clickCount = clickCount;
//	}
//	public int getClickTotal() {
//		return clickTotal;
//	}
//	public void setClickTotal(int clickTotal) {
//		this.clickTotal = clickTotal;
//	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getPublishUserId() {
		return publishUserId;
	}
	public void setPublishUserId(String publishUserId) {
		this.publishUserId = publishUserId;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	public Date getRejectDate() {
		return rejectDate;
	}
	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
	}
	public String getRejectUserId() {
		return rejectUserId;
	}
	public void setRejectUserId(String rejectUserId) {
		this.rejectUserId = rejectUserId;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public int getSortNo() {
		return sortNo;
	}
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	 public static Comparator<BannerDto> publishStartDateComparator = new Comparator<BannerDto>() {

		    @Override
			public int compare(BannerDto s1, BannerDto s2) {
		    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		    	String date1 = df.format(s1.getDateStart());
		    	String date2 = df.format(s2.getDateFinish());
			   return date2.compareTo(date1);
		    }};
}
