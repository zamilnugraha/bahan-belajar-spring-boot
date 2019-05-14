package com.banner.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="EKR_98_USER")
@IdClass(UserPK.class)
public class User {
	
	private String id;
	private String name;
	private String email;
	private String password;
	private String groupId;
	private String status;
	private String secretQuestion;
	private String secretAnswer;
	private String rowStatus;
	private String rowUserId;
	private Date rowTimeUpdate;
	private String type;
	private Date lastLoginDate;
	
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

	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="GROUP_ID")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="SECRET_QUESTION")
	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	@Column(name="SECRET_ANSWER")
	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
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
	
	@Column(name="TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="LAST_LOGIN_DATE")
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
}
