package com.banner.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
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
	
	//additional
//	private String idToken;
//	private String token;
//	private String createUserId;
//	private List<TokenDto> tokenDtos = new ArrayList<>();
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSecretQuestion() {
		return secretQuestion;
	}
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	public String getSecretAnswer() {
		return secretAnswer;
	}
	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
//	public String getIdToken() {
//		return idToken;
//	}
//	public void setIdToken(String idToken) {
//		this.idToken = idToken;
//	}
//	public String getToken() {
//		return token;
//	}
//	public void setToken(String token) {
//		this.token = token;
//	}
//	public String getCreateUserId() {
//		return createUserId;
//	}
//	public void setCreateUserId(String createUserId) {
//		this.createUserId = createUserId;
//	}
//	public List<TokenDto> getTokenDtos() {
//		return tokenDtos;
//	}
//	public void setTokenDtos(List<TokenDto> tokenDtos) {
//		this.tokenDtos = tokenDtos;
//	}
}
