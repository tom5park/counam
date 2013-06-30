package com.k2h2.counam.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

	public class AM implements AuthenticationManager {

	public AM() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println(String.format("____[B58]__: %s", authentication));
		System.out.println(String.format("__[E29]__: %s", authentication.getAuthorities()));
		System.out.println(String.format("__[B58]__: %s", authentication.getClass()));
		System.out.println(String.format("__[B58]__: %s", authentication.getCredentials()));
		System.out.println(String.format("__[B58]__: %s", authentication.getDetails()));
		System.out.println(String.format("__[B58]__: %s", authentication.getName()));
		System.out.println(String.format("__--[B58]__: %s", authentication.getPrincipal()));
		return null;
	}

}
