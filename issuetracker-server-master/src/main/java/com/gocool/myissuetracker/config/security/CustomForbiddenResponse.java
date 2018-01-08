package com.gocool.myissuetracker.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gocool.myissuetracker.common.AppContstant.CustomResponse;
import com.gocool.myissuetracker.common.dto.Response;

/**
 * 
 * @author gokulsonawane
 *
 */
public class CustomForbiddenResponse implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse httpResponse,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		Response response=new Response();
		response.setCode(CustomResponse.FORBIDDEN.getCode());
		response.setMessage(CustomResponse.FORBIDDEN.getMessage());
		httpResponse.setContentType("application/json");
		httpResponse.getWriter().write(new ObjectMapper().writeValueAsString(response));
		
	}

}
