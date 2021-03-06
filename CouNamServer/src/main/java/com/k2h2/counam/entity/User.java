package com.k2h2.counam.entity;

import com.k2h2.counam.constant.AuthType;
import com.k2h2.counam.constant.UserStatus;


public class User {
	
	String id;
	
	String name;
	
	UserStatus status;
	
	AuthType authType;
	
	String accToken;
	
	String authId;
	
	boolean agreement;
	
	String eMail;
	
	String mobile;
	
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
	
	public UserStatus getStatus() {
		return status;
	}
	
	public void setStatus(UserStatus status) {
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

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
