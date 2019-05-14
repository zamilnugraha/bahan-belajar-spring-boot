package com.banner.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="EKR_99_APPLICATION")
@IdClass(ApplicationPK.class)
public class Application {
	
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
	
	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="APP_ID")
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	@Column(name="APP_KEY")
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="ROW_STATUS")
	public String getRowStatus() {
		return rowStatus;
	}
	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}
	
	@Column(name="ROW_USER_ID")
	public String getRowUserId() {
		return rowUserId;
	}
	public void setRowUserId(String rowUserId) {
		this.rowUserId = rowUserId;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="ROW_TIME_UPDATE")
	public Date getRowTimeUpdate() {
		return rowTimeUpdate;
	}
	public void setRowTimeUpdate(Date rowTimeUpdate) {
		this.rowTimeUpdate = rowTimeUpdate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name="CREATE_USER_ID")
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
	@Column(name="APP_HOST")
	public String getAppHost() {
		return appHost;
	}
	public void setAppHost(String appHost) {
		this.appHost = appHost;
	}
	

}
