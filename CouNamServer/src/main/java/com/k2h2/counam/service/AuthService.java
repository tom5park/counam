package com.k2h2.counam.service;

import java.io.IOException;

import javax.servlet.http.HttpSession;

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
import com.k2h2.counam.auth.GoogleAuthResult;

@Controller
@SessionAttributes({"userId", "userId"})
public class AuthService {
	@RequestMapping(value="/auth/google/checkAuth.json")
	@ResponseBody
	public String checkGoogleAuth(GoogleAuthResult authResult) {
		HttpTransport httpTransport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();
		GoogleCredential credential = new GoogleCredential().setAccessToken(authResult.access_token);
		/*
	    Plus plus = Plus.builder(new NetHttpTransport(), new JacksonFactory())
	        .setApplicationName("Google-PlusSample/1.0")
	        .setHttpRequestInitializer(credential)
	        .build();
		

		Credential credential = new Credential.Builder(accessMethod)
		    .setJsonFactory(jsonFactory)
		    .setTransport(httpTransport)
		    .setTokenServerEncodedUrl(tokenServerEncodedUrl)
		    .setClientAuthentication(clientAuthentication)
		    .setRequestInitializer(requestInitializer)
		    .build();
		*/
		Plus plus = new Plus(httpTransport, jsonFactory, credential);

		Person profile;
		try {
			profile = plus.people().get("me").execute();
			System.out.println("ID: " + profile.getId());
			System.out.println("Name: " + profile.getDisplayName());
			System.out.println("Image URL: " + profile.getImage().getUrl());
			System.out.println("Profile URL: " + profile.getUrl());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		GoogleCredential c = new GoogleCredential.Builder()
	      .setJsonFactory(JSON_FACTORY).setTransport(TRANSPORT)
	      .setClientSecrets(CLIENT_ID, CLIENT_SECRET).build(); 
		System.out.println(String.format("__[X78]__: %s", authResult.access_token));
		System.out.println(String.format("__[X78]__: %s", authResult._aa));
		*/
		return "";
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
