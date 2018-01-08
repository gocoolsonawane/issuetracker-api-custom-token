package com.gocool.myissuetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.gocool.myissuetracker.config.security.AuthenticationFilter;
import com.gocool.myissuetracker.config.security.CustomForbiddenResponse;
import com.gocool.myissuetracker.config.security.CustomUnauthorizedResponse;
import com.gocool.myissuetracker.config.security.TokenAuthenticationProvider;
import com.gocool.myissuetracker.config.security.UsernamePasswordAuthenticationProvider;
import com.gocool.myissuetracker.services.TokenService;
import com.gocool.myissuetracker.services.UserService;

@Configuration
@EnableWebSecurity
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.anonymous().disable().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint())
				.accessDeniedHandler(accessDeniedHandler());

		http.addFilterBefore(new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
	}

	private AccessDeniedHandler accessDeniedHandler() {
		return new CustomForbiddenResponse();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(UsernamePasswordAuthenticationProvider())
				.authenticationProvider(tokenAuthenticationProvider());
	}

	public AuthenticationProvider tokenAuthenticationProvider() {
		return new TokenAuthenticationProvider(tokenService());
	}

	public AuthenticationProvider UsernamePasswordAuthenticationProvider() {
		return new UsernamePasswordAuthenticationProvider(tokenService(), getUserService());
	}

	@Bean
	public TokenService tokenService() {
		return new TokenService();
	}

	@Bean
	public UserService getUserService() {
		return new UserService();
	}

	public AuthenticationEntryPoint unauthorizedEntryPoint() {
		return new CustomUnauthorizedResponse();

	}
}
