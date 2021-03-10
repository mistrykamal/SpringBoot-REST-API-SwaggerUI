package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration		
@EnableWebSecurity  // tells application that this is the Web Security Configuration class
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	/**
	 *   Overriding the configure method of WebSecurityConfigureAdapter
	 *   
	 *   Authorizing security using HttpSecurity
	 *   
	 *   1. get hold of HttpSecurity 
	 *   2. set configuration on it
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable(); //When not using Authentication, You Have to disable the http csrf, because its ON by default 
//		http.authorizeRequests()
//			.antMatchers("/test/hospitals/load").hasAnyRole("USER","ADMIN")
//			.antMatchers("/test/hospitals/{id}").hasAnyRole("USER","ADMIN")
//			.antMatchers("/test/hospitals").hasAnyRole("USER","ADMIN")
//			.antMatchers("/test").permitAll()
//			.and().formLogin();
	}
	
	/** 
	 * 	Overriding the configure method of WebSecurityConfigurerAdapter 
	 * 
	 *  using AuthenticationManagerBuilder
	 * 	
	 *  1. get hold of Authentication Manager Builder
	 *  2. set configuration on it
	 *  
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("kamal")
			.password("mistry")
			.roles("ADMIN")
			.and()
			.withUser("minesh")
			.password("mistry123")
			.roles("USER");
	}
	
	/**  
	 *   Even its doing NOTHING
	 *   it is required for username and password input to work
	 */
	@Bean 
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
