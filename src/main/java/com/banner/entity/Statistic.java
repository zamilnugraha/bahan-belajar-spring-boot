package com.banner.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
*
* @author Chubb Team ( Fairuz Fatin, Bayu Sugara, Muhamad Zamil, Fhad Saleh )
*/
@Entity
@Table(name="EKR_80_STATISTIC")
@IdClass(StatisticPK.class)
public class Statistic {

	private String id;//1
	private String parentId;//2
	private String type;//3
	private String ip;//4
	private String userId;//5
	private String rowStatus;//6
	private String rowUserId;//7
	private Date rowTimeUpdate;//8
	private Date createDate;//9
	private String createUserId;//10
	
	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="PARENT_ID")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="IP")
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name="USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	
	@Column(name="ROW_TIME_UPDATE")
	public Date getRowTimeUpdate() {
		return rowTimeUpdate;
	}
	public void setRowTimeUpdate(Date rowTimeUpdate) {
		this.rowTimeUpdate = rowTimeUpdate;
	}
	
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
}
