package com.banner.dto;

import java.io.Serializable;
import java.util.Date;

public class ApplicationDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String appId;
	private String appKey;
	private String type;
	private String rowStatus;
	private String rowUserId;
	private Date rowTimeUpdate;
	private Date createDate;
	private String createUserId;
	private String appHost;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getAppHost() {
		return appHost;
	}
	public void setAppHost(String appHost) {
		this.appHost = appHost;
	}
	
	

}
