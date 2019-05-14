/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banner.entity;

import javax.persistence.Column;
import javax.persistence.Lob;

import java.util.Date;

import javax.persistence.*;

import com.banner.entity.BannerPK;

/**
 *
 * @author eKreasi Chubb Team ( Fairuz Fatin, Bayu Sugara, Maretta, Muhamad
 *         Zamil, Fhad Saleh )
 */

@Entity
@Table(name = "EKR_10_BANNER")
@IdClass(BannerPK.class)
public class Banner {

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
	private Date createDate;
	private String createUserId;
	private String rowStatus;
	private String rowUserId;
	private Date rowTimeUpdate;
	private String layoutId;
	private String url;
	private String showText;
	private Date approvalDate;
	private Date rejectDate;
	private String rejectUserId;
	private Date lastUpdateDate;
	private int sortNo;

	@Id
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "TITLE_EN")
	public String getTitleEn() {
		return titleEn;
	}

	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}

	@Column(name = "DESCRIPTION")
	@Lob
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "DESCRIPTION_EN")
	@Lob
	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	@Column(name = "DATE_START")
	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	@Column(name = "DATE_FINISH")
	public Date getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}

	@Column(name = "APPROVAL_NO")
	public int getApprovalNo() {
		return approvalNo;
	}

	public void setApprovalNo(int approvalNo) {
		this.approvalNo = approvalNo;
	}

	@Column(name = "APPROVAL_USER_ID")
	public String getApprovalUserId() {
		return approvalUserId;
	}

	public void setApprovalUserId(String approvalUserId) {
		this.approvalUserId = approvalUserId;
	}

	@Column(name = "ROW_STATUS")
	public String getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}

	@Column(name = "ROW_USER_ID")
	public String getRowUserId() {
		return rowUserId;
	}

	public void setRowUserId(String rowUserId) {
		this.rowUserId = rowUserId;
	}

	@Column(name = "ROW_TIME_UPDATE")
	public Date getRowTimeUpdate() {
		return rowTimeUpdate;
	}

	public void setRowTimeUpdate(Date rowTimeUpdate) {
		this.rowTimeUpdate = rowTimeUpdate;
	}

	@Column(name = "IMAGE_FILENAME")
	public String getImageFilename() {
		return imageFilename;
	}

	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}

	@Column(name = "LAYOUT_ID")
	public String getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}

	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_USER_ID")
	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

    @Column(name="SHOW_TEXT")
	public String getShowText() {
		return showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}
	
	@Column(name="APPROVAL_DATE")
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	
	@Column(name="REJECT_DATE")
	public Date getRejectDate() {
		return rejectDate;
	}
	public void setRejectDate(Date rejectDate) {
		this.rejectDate = rejectDate;
	}
	
	@Column(name="REJECT_USER_ID")
	public String getRejectUserId() {
		return rejectUserId;
	}
	public void setRejectUserId(String rejectUserId) {
		this.rejectUserId = rejectUserId;
	}
	
	@Column(name="LAST_UPDATE_DATE")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	@Column(name="SORT_NO")
	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

}
