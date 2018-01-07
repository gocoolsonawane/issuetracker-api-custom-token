package com.gocool.myissuetracker.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.gocool.myissuetracker.services.TokenService;

public class TokenAuthenticationProvider implements AuthenticationProvider {

	private TokenService tokenService;

	public TokenAuthenticationProvider(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String token = (String) authentication.getPrincipal();
		if (token == null || token.isEmpty()) {
			throw new BadCredentialsException("Invalid token");
		}
		if (!tokenService.contains(token)) {
			throw new BadCredentialsException("Invalid token or token expired");
		}
		authentication = tokenService.retrieve(token);
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 return authentication.equals(PreAuthenticatedAuthenticationToken.class);
	}

}
