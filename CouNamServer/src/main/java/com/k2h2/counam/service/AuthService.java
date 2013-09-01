package com.k2h2.counam.service;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Person;
import com.k2h2.counam.constant.AuthType;
import com.k2h2.counam.constant.UserStatus;
import com.k2h2.counam.entity.User;
import com.k2h2.counam.exception.CNException;
import com.k2h2.counam.info.AuthInfo;
import com.k2h2.counam.info.GoogleAuthInfo;
import com.k2h2.counam.mapper.UserMapper;

@Controller
@SessionAttributes({"userId", "userId"})
public class AuthService {
	
	@Autowired
	UserMapper userMapper;
	
//	@Autowired
//	ClientTokenServices google; 
	
	@RequestMapping(value="/auth/google/checkAuth.json")
	@ResponseBody
	public AuthInfo checkGoogleAuth(GoogleAuthInfo authResult, HttpSession session) {
		AuthInfo res = new AuthInfo();
		String userId = (String) session.getAttribute("userId");
		res.setLoggedIn(userId != null ? true: false);
		res.setSignedUp(userId != null ? true: false);
		String googleId = getGoogleId(authResult.access_token);
		if(googleId != null) {
			res.setLoggedInGoogle(true);
			User user = this.userMapper.getUserByAuthId(AuthType.GOOGLE, googleId);
			res.setSignedUp(user != null ? true: false);
		} else {
			res.setLoggedInGoogle(false);
		}
		return res;
	}
	
	private Person getGoogleProfile(String accToken) {
		Person res = null;
		HttpTransport httpTransport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();
		GoogleCredential credential = new GoogleCredential().setAccessToken(accToken);
		//credential.
		Plus plus = new Plus(httpTransport, jsonFactory, credential);
		try {
			res = plus.people().get("me").execute();
		//} catch (GoogleJsonResponseException e) {
		//	e
		} catch (IOException e) {
			throw new CNException("Can't get google profile.", e);
		}
		return res;
	}
	
	private String getGoogleId(String accToken) {
		return getGoogleProfile(accToken).getId();
	}
	
	@RequestMapping(value="/auth/updateGoogleAuthInfo.json")
	@ResponseBody
	public AuthInfo updateGoogleAuthInfo(GoogleAuthInfo googleAuthInfo, HttpSession session) {
		// session.setAttribute("googleAuthInfo", googleAuthInfo);
		session.setAttribute("googleAccToken", googleAuthInfo.access_token);
		session.setAttribute("googleId", getGoogleId(googleAuthInfo.access_token));
		return getAuthInfo(session);
	}
	
	@RequestMapping(value="/auth/getAuthInfo.json")
	@ResponseBody
	public AuthInfo getAuthInfo(HttpSession session) {
		AuthInfo res = new AuthInfo();
		String googleId = (String) session.getAttribute("googleId");
		String userId = autoLogin(googleId);
		res.setLoggedIn(userId != null ? true: false);
		res.setSignedUp(userId != null ? true: false);
		res.setLoggedInGoogle(googleId != null ? true: false);
		if(googleId != null) {
			User user = this.userMapper.getUserByAuthId(AuthType.GOOGLE, googleId);
			res.setSignedUp(user != null ? true: false);
			if(user != null) {
				session.setAttribute("userId", user.getId());
			} 
			res.setSignedUp(user != null ? true: false);
		}
		return res;
	}
	
	private String autoLogin(String googleId) {
		SecurityContext context = SecurityContextHolder.getContext();
		if(context.getAuthentication().isAuthenticated() && !(context.getAuthentication() instanceof AnonymousAuthenticationToken)) {
			return context.getAuthentication().getPrincipal().toString();
		}
		User user = this.userMapper.getUserByAuthId(AuthType.GOOGLE, googleId);
		if(user == null) {
			return null;
		}
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getId(), "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		context.setAuthentication(auth);
		return user.getId();
	}
	
	@RequestMapping(value="/auth/signUpWithGoogleAccount.json")
	@ResponseBody
	public String signUpWithGoogleAccount(String accToken, HttpSession session) {
		String res = UUID.randomUUID().toString();
		Person p = getGoogleProfile(accToken);
		
		if(this.userMapper.getUserByAuthId(AuthType.GOOGLE, p.getId()) != null) {
			throw new CNException("The account is already used by other user.");
		}
		User user = new User();
		user.setId(res);
		user.setAccToken(accToken);
		user.setAgreement(true);
		user.setAuthId(p.getId());
		user.setAuthType(AuthType.GOOGLE);
		user.setName(p.getDisplayName());
		user.setStatus(UserStatus.ACTIVE);
		this.userMapper.createUser(user);
		session.setAttribute("userId", res);
		return res;
	}
	
	@RequestMapping(value="/auth/login.json")
	@ResponseBody
	public int login(String id, String password, HttpSession session) {
		int res = 0;
		String userId = (String) session.getAttribute("userId");
		session.setAttribute("userId", id);
		return res;
	}
	
	@RequestMapping(value="/auth/logout.json")
	@ResponseBody
	public void logout(HttpSession session) {
		SecurityContextHolder.clearContext();
		//session.removeAttribute("userId");
	}
	
	@RequestMapping(value="/auth/deleteAccount.json")
	@ResponseBody
	public void deleteAccount(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		User user = new User();
		user.setId(userId);
		user.setName("탈퇴한 사용자");
		user.setStatus(UserStatus.DELETED);
		user.setAccToken("");
		user.setAuthId("");
		user.setAuthType(AuthType.UNKNOWN);
		this.userMapper.updateUser(user);
		session.removeAttribute("userId");
	}
}
