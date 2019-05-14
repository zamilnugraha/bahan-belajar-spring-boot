package com.banner.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="EKR_98_CONFIG")
@IdClass(ConfigPKEntity.class)
public class ConfigEntity {
	
	
	private String id;
	private String name;
	private String description;
	private String value;
	private String type;
	private String rowStatus;
	private String rowUserId;
	private String rowTimeUpdate;
	private String modes;
	
	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="VALUE")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	
	@Column(name="ROW_TIME_UPDATE")
	public String getRowTimeUpdate() {
		return rowTimeUpdate;
	}
	public void setRowTimeUpdate(String rowTimeUpdate) {
		this.rowTimeUpdate = rowTimeUpdate;
	}
	
	@Column(name="MODES")
	public String getModes() {
		return modes;
	}
	public void setModes(String modes) {
		this.modes = modes;
	}
	
	

}
