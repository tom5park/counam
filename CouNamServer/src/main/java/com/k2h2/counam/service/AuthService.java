package com.k2h2.counam.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@SessionAttributes({"currentUserID", "currentUserAccessToken"})
public class AuthService {
	
	@RequestMapping(value="/auth/google/checkAuth.json")
	@ResponseBody
	public String checkGoogleAuth(String idToken) {
		return "";
	}
}
