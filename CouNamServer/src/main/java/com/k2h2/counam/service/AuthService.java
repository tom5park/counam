package com.k2h2.counam.service;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.k2h2.counam.entity.User;
import com.k2h2.counam.info.AuthInfo;
import com.k2h2.counam.info.GoogleAuthInfo;
import com.k2h2.counam.mapper.UserMapper;

@Controller
@SessionAttributes({"userId", "userId"})
public class AuthService {
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping(value="/auth/google/checkAuth.json")
	@ResponseBody
	public AuthInfo checkGoogleAuth(GoogleAuthInfo authResult, HttpSession session) {
		AuthInfo res = new AuthInfo();
		String userId = (String) session.getAttribute("userId");
		res.setLoggedIn(userId != null ? true: false);
		res.setSignedUp(userId != null ? true: false);
		String googleId = getGoogleId(authResult.access_token);
		if(googleId != null) {
			res.setGoogleLoggedIn(true);
			User user = this.userMapper.getUserByAuthId(googleId);
			res.setSignedUp(user != null ? true: false);
		} else {
			res.setGoogleLoggedIn(false);
		}
		return res;
	}
	
	private String getGoogleId(String accToken) {
		String res = null;
		HttpTransport httpTransport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();
		GoogleCredential credential = new GoogleCredential().setAccessToken(accToken);
		Plus plus = new Plus(httpTransport, jsonFactory, credential);

		Person profile;
		try {
			profile = plus.people().get("me").execute();
			res = profile.getId();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@RequestMapping(value="/auth/updateGoogleAuthInfo.json")
	@ResponseBody
	public AuthInfo updateGoogleAuthInfo(GoogleAuthInfo googleAuthInfo, HttpSession session) {
		// session.setAttribute("googleAuthInfo", googleAuthInfo);
		session.setAttribute("googleAccToken", googleAuthInfo.access_token);
		session.setAttribute("googleId", getGoogleId(googleAuthInfo.access_token));
		return getAuthInfo(session);
	}
	
	public AuthInfo getAuthInfo(HttpSession session) {
		AuthInfo res = new AuthInfo();
		
		String userId = (String) session.getAttribute("userId");
		String googleId = (String) session.getAttribute("googleId");
		
		res.setLoggedIn(userId != null ? true: false);
		res.setSignedUp(userId != null ? true: false);
		res.setGoogleLoggedIn(googleId != null ? true: false);
		if(googleId != null) {
			User user = this.userMapper.getUserByAuthId(googleId);
			res.setSignedUp(user != null ? true: false);
			if(user != null) {
				session.setAttribute("userId", user.getId());
			} 
			res.setSignedUp(user != null ? true: false);
		}
		return res;
	}
	
	@RequestMapping(value="/auth/login.json")
	@ResponseBody
	public int login(String id, String password, HttpSession session) {
		int res = 0;
		String userId = (String) session.getAttribute("userId");
		System.out.println(String.format("__[T18]__: %s", userId));
		session.setAttribute("userId", id);
		return res;
	}
}
