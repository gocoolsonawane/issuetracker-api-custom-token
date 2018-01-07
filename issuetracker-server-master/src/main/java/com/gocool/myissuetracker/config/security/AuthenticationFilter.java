package com.gocool.myissuetracker.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gocool.myissuetracker.common.AppContstant.CustomResponse;
import com.gocool.myissuetracker.common.dto.Response;
import com.google.common.base.Strings;

public class AuthenticationFilter extends GenericFilterBean {

	private final static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

	private AuthenticationManager authenticationManager;
	String AUTHENTICATE_URL = "/api/authenticate";
	public static final String TOKEN_SESSION_KEY = "token";
	public static final String USER_SESSION_KEY = "user";

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Executing**************"+AuthenticationFilter.class.getName());
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String username = httpRequest.getHeader("X-Auth-Username");
		String password = httpRequest.getHeader("X-Auth-Password");
		String token = httpRequest.getHeader("Authentication");

		String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);
		System.out.println(resourcePath);
		logger.info("user ->"+resourcePath);
		try {
			if (postToAuthenticate(httpRequest, resourcePath)) {
				logger.info("Trying to authenticate user {} by X-Auth-Username method", username);
				processUsernamePasswordAuthentication(httpResponse, username, password, httpRequest);
				return;
			} else {
				logger.info("Trying to authenticate user by X-Auth-Token method. Token: {}", token);
				processTokenAuthentication(token, httpRequest);
			}
			logger.debug("AuthenticationFilter is passing request down the filter chain");
			addSessionContextToLogging();
			chain.doFilter(request, response);

		} catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
			SecurityContextHolder.clearContext();
			logger.info("Internal authentication service exception", internalAuthenticationServiceException);
			httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (AuthenticationException authenticationException) {
			SecurityContextHolder.clearContext();
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
		} finally {
			MDC.remove(TOKEN_SESSION_KEY);
			MDC.remove(USER_SESSION_KEY);
		}
	}

	private void addSessionContextToLogging() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String tokenValue = "EMPTY";
		if (authentication != null && !Strings.isNullOrEmpty(authentication.getPrincipal().toString())) {
			MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder("SHA-1");
			tokenValue = encoder.encodePassword(authentication.getPrincipal().toString(), "not_so_random_salt");
		}
		MDC.put(TOKEN_SESSION_KEY, tokenValue);

		String userValue = "EMPTY";
		if (authentication != null && !Strings.isNullOrEmpty(authentication.getPrincipal().toString())) {
			userValue = authentication.getPrincipal().toString();
		}
		MDC.put(USER_SESSION_KEY, userValue);
	}

	private void processTokenAuthentication(String token, HttpServletRequest httpRequest) {
		Authentication resultOfAuthentication = tryToAuthenticateWithToken(token, httpRequest);
		SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);

	}

	private Authentication tryToAuthenticateWithToken(String token, HttpServletRequest httpRequest) {
		PreAuthenticatedAuthenticationToken requestAuthentication = new PreAuthenticatedAuthenticationToken(token,
				null);
		return tryToAuthenticate(requestAuthentication, httpRequest);
	}

	private void processUsernamePasswordAuthentication(HttpServletResponse httpResponse, String username,
			String password, HttpServletRequest httpRequest) throws JsonProcessingException, IOException {
		Authentication resultOfAuthentication = tryToAuthenticateWithUsernameAndPassword(username, password,
				httpRequest);
		SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
		httpResponse.setStatus(HttpServletResponse.SC_OK);
		Response response=new Response();
		response.setCode(CustomResponse.SUCCESS.getCode());
		response.setMessage(CustomResponse.SUCCESS.getMessage());
		response.setData(resultOfAuthentication.getDetails());
		httpResponse.setContentType("application/json");
		httpResponse.getWriter().write(new ObjectMapper().writeValueAsString(response));
	}

	private Authentication tryToAuthenticateWithUsernameAndPassword(String username, String password,
			HttpServletRequest httpRequest) {
		UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(username,
				password);
		return tryToAuthenticate(requestAuthentication, httpRequest);
	}

	private Authentication tryToAuthenticate(Authentication requestAuthentication, HttpServletRequest httpRequest) {
		Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
		if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
			throw new InternalAuthenticationServiceException(
					"Unable to authenticate Domain User for provided credentials");
		}
		logger.debug("User successfully authenticated");
		return responseAuthentication;
	}

	private boolean postToAuthenticate(HttpServletRequest httpRequest, String resourcePath) {
		return AUTHENTICATE_URL.equalsIgnoreCase(resourcePath) && httpRequest.getMethod().equals("POST");
	}

}
