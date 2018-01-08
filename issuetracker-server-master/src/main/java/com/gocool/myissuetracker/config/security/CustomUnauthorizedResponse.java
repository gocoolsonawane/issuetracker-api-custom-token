package com.gocool.myissuetracker.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gocool.myissuetracker.common.AppContstant.CustomResponse;
import com.gocool.myissuetracker.common.dto.Response;

/**
 * 
 * @author gokulsonawane
 *
 */
public class CustomUnauthorizedResponse implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse httpResponse,
			AuthenticationException authException) throws IOException, ServletException {
		System.out.println("*********CustomUnauthorizedResponse*******");
		
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		Response response=new Response();
		response.setCode(CustomResponse.UNAUTHORIZED.getCode());
		response.setMessage(CustomResponse.UNAUTHORIZED.getMessage());
		httpResponse.setContentType("application/json");
		httpResponse.getWriter().write(new ObjectMapper().writeValueAsString(response));
		
	}

}
