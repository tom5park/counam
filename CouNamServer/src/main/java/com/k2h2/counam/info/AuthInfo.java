package com.k2h2.counam.info;

public class AuthInfo {
	/**
	 * 쿠폰남발에 가입되어 있는 상태인가?
	 */
	private boolean isSignedUp = false;
	
	/**
	 * 쿠폰남발에 로그인되어 있는 상태인가?
	 */
	private boolean isLoggedIn = false;
	
	/**
	 * 구글에 로그인되어 있는 상태인가?
	 */
	private boolean isGoogleLoggedIn = false;
	
	/**
	 * 페이스북에 로그인되어 있는 상태인가? 
	 */
	private boolean isFacebookLoggedIn = false;
	
	/**
	 * 카카오톡에 로그인되어 있는 상태인가? 
	 */
	private boolean isKakaoLoggedIn = false;

	public boolean isSignedUp() {
		return isSignedUp;
	}

	public void setSignedUp(boolean isSignedUp) {
		this.isSignedUp = isSignedUp;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public boolean isGoogleLoggedIn() {
		return isGoogleLoggedIn;
	}

	public void setGoogleLoggedIn(boolean isGoogleLoggedIn) {
		this.isGoogleLoggedIn = isGoogleLoggedIn;
	}

	public boolean isFacebookLoggedIn() {
		return isFacebookLoggedIn;
	}

	public void setFacebookLoggedIn(boolean isFacebookLoggedIn) {
		this.isFacebookLoggedIn = isFacebookLoggedIn;
	}

	public boolean isKakaoLoggedIn() {
		return isKakaoLoggedIn;
	}

	public void setKakaoLoggedIn(boolean isKakaoLoggedIn) {
		this.isKakaoLoggedIn = isKakaoLoggedIn;
	}
}
