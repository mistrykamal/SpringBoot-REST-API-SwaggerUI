package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // tells application that this is the Web Security Configuration class
@Order(1)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private AuthenticationEntryPoint authenticationEntryPoint;

	@Value("${SpringBoot-REST-API.http.auth-token-header-name}")
	private String principalRequestHeader;

	@Value("${SpringBoot-REST-API.http.auth-token}")
	private String principalRequestValue;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		APIKeyAuthFilter filter = new APIKeyAuthFilter(principalRequestHeader);
		filter.setAuthenticationManager((AuthenticationManager) new AuthenticationManager() {

			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String principal = (String) authentication.getPrincipal();
				if (!principalRequestValue.equals(principal)) {
					throw new BadCredentialsException("The API key was not found or not the expected value.");
				}
				authentication.setAuthenticated(true);
				return authentication;
			}
		});
		httpSecurity.antMatcher("https://api.nytimes.com/svc/topstories/v2/arts.json/**").csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().addFilter(filter)
				.authorizeRequests().anyRequest().authenticated();
	}

	/**
	 * Overriding the configure method of WebSecurityConfigureAdapter
	 * 
	 * Authorizing security using HttpSecurity
	 * 
	 * 1. get hold of HttpSecurity 2. set configuration on it
	 */
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable(); //When not using Authentication, You Have to disable the http csrf, because its ON by default 
//		http.authorizeRequests()
//			.antMatchers("/test/hospitals/load").hasAnyRole("USER","ADMIN")
//			.antMatchers("/test/hospitals/{id}").hasAnyRole("USER","ADMIN")
//			.antMatchers("/test/hospitals").hasAnyRole("USER","ADMIN")
//			.antMatchers("/test").permitAll()
//			.and().formLogin();

	// When using Postman to see the response
//		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic()
//			.authenticationEntryPoint(authenticationEntryPoint);
//	}

	/**
	 * Overriding the configure method of WebSecurityConfigurerAdapter
	 * 
	 * using AuthenticationManagerBuilder
	 * 
	 * 1. get hold of Authentication Manager Builder 2. set configuration on it
	 * 
	 */
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("kamal")
//			.password("mistry")
//			.roles("ADMIN")
//			.and()
//			.withUser("minesh")
//			.password("mistry123")
//			.roles("USER");
	}

	/**
	 * Even its doing NOTHING it is required for username and password input to work
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
