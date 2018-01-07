package com.gocool.myissuetracker.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

import com.gocool.myissuetracker.common.dto.UserDto;
import com.gocool.myissuetracker.common.model.User;
import com.gocool.myissuetracker.services.TokenService;
import com.gocool.myissuetracker.services.UserService;

public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	private TokenService tokenService;
	private UserService userService;

	public UsernamePasswordAuthenticationProvider(TokenService tokenService, UserService userService) {
		this.tokenService = tokenService;
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			throw new BadCredentialsException("Invalid Domain User Credentials");
		}

		User user = null;
		AuthenticationWithToken resultOfAuthentication = null;
		try {
			user = userService.authenticate(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null) {
			resultOfAuthentication = new AuthenticationWithToken(username, null,
					AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole()));
			String newToken = tokenService.generateNewToken();
			UserDto userData=transformUserToUserDto(user);
			userData.setToken(newToken);
			resultOfAuthentication.setUserData(userData);
			tokenService.store(newToken, resultOfAuthentication);
		} else {
			throw new BadCredentialsException("Invalid Domain User Credentials");
		}
		return resultOfAuthentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private UserDto transformUserToUserDto(User loginUser) {
		UserDto userDto = new UserDto();
		userDto.setId(loginUser.getId());
		userDto.setName(loginUser.getName());
		userDto.setEmail(loginUser.getEmail());
		userDto.setMobile(loginUser.getMobile());
		userDto.setRole(loginUser.getRole());
		userDto.setToken(loginUser.getToken());
		return userDto;
	}

}
