package com.gocool.myissuetracker.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.gocool.myissuetracker.common.dto.UserDto;

@SuppressWarnings("serial")
public class AuthenticationWithToken extends PreAuthenticatedAuthenticationToken {

	public AuthenticationWithToken(Object aPrincipal, Object aCredentials) {
		super(aPrincipal, aCredentials);
	}

	public AuthenticationWithToken(Object aPrincipal, Object aCredentials,
			Collection<? extends GrantedAuthority> anAuthorities) {
		super(aPrincipal, aCredentials, anAuthorities);
	}

	public void setUserData(UserDto userData) {
		setDetails(userData);
	}

	public UserDto etUserData() {
		return (UserDto) getDetails();
	}

}
