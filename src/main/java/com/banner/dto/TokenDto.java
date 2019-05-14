package com.banner.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TokenDto implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String id;
	private String token;
	private String rowStatus;
	private String rowUserId;
	private Date rowTimeUpdate;
	private Date createDate;
	private String createUserId;
	private String userId;
	private String groupId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
