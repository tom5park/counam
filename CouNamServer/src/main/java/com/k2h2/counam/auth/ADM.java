package com.k2h2.counam.auth;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

@Service("")
public class ADM implements AccessDecisionManager {

	public ADM() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		// TODO Auto-generated method stub
//		System.out.println(String.format("__[B58]__: %s", authentication));
		System.out.println(String.format("__[Z24]__: %s", object));
		
		FilterInvocation fi = (FilterInvocation) object;
		
		
		Set<String> authSet = new HashSet<String>();
		Iterator<? extends GrantedAuthority> it = authentication.getAuthorities().iterator();
		while(it.hasNext()) {
			authSet.add(it.next().getAuthority());
		}
		Iterator<ConfigAttribute> it2 = configAttributes.iterator();
		while(it2.hasNext()) {
			if(authSet.contains(it2.next().getAttribute())) {
				return;
			}
		}
		System.out.println(String.format("__[T17]__: %s", authSet));
		System.out.println(String.format("__[F79]__: %s", configAttributes));
		System.out.println(String.format("__[P62]__: %s", fi.getRequestUrl()));
		throw new InsufficientAuthenticationException("asdf");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		//System.out.println(String.format("__[R79]__: %s", attribute));
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		//System.out.println(String.format("__[D33]__: %s", clazz.getName()));
		return true;
	}

}
