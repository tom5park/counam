package com.k2h2.counam.info;

public class AuthInfo {
	/**
	 * 쿠폰남발에 가입되어 있는 상태인가?
	 */
	private boolean signedUp = false;
	
	/**
	 * 쿠폰남발에 로그인되어 있는 상태인가?
	 */
	private boolean loggedIn = false;
	
	/**
	 * 구글에 로그인되어 있는 상태인가?
	 */
	private boolean loggedInGoogle = false;
	
	/**
	 * 페이스북에 로그인되어 있는 상태인가? 
	 */
	private boolean loggedInFacebook = false;
	
	/**
	 * 카카오톡에 로그인되어 있는 상태인가? 
	 */
	private boolean loggedInKakao = false;

	public boolean isSignedUp() {
		return signedUp;
	}

	public void setSignedUp(boolean signedUp) {
		this.signedUp = signedUp;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean isLoggedInGoogle() {
		return loggedInGoogle;
	}

	public void setLoggedInGoogle(boolean loggedInGoogle) {
		this.loggedInGoogle = loggedInGoogle;
	}

	public boolean isLoggedInFacebook() {
		return loggedInFacebook;
	}

	public void setLoggedInFacebook(boolean loggedInFacebook) {
		this.loggedInFacebook = loggedInFacebook;
	}

	public boolean isLoggedInKakao() {
		return loggedInKakao;
	}

	public void setLoggedInKakao(boolean loggedInKakao) {
		this.loggedInKakao = loggedInKakao;
	}

	
}
