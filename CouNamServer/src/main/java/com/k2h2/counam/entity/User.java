package com.k2h2.counam.entity;

import com.k2h2.counam.constant.AuthType;


public class User {
	
	String id;
	
	String name;
	
	String status;
	
	AuthType authType;
	
	String accToken;
	
	String authId;
	
	boolean agreement;
	
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public AuthType getAuthType() {
		return authType;
	}
	
	public void setAuthType(AuthType authType) {
		this.authType = authType;
	}
	
	public String getAccToken() {
		return accToken;
	}
	
	public void setAccToken(String accToken) {
		this.accToken = accToken;
	}
	
	public String getAuthId() {
		return this.authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	
	public boolean isAgreement() {
		return agreement;
	}
	
	public void setAgreement(boolean agreement) {
		this.agreement = agreement;
	}
}
