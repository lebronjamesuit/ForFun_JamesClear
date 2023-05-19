package com.startCoreProduct.JamesClear.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;


@Configuration
public class SpringSecurityBasic {

	@Bean
	public SecurityFilterChain customeChain(HttpSecurity httpSecure) throws Exception {
		
		// 1) Authenticated all requests
		httpSecure.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
	 
		// 2) If a request is not authenticted then show a login form
		httpSecure.httpBasic(Customizer.withDefaults());
		
		// 3) Disable CSRF 
		httpSecure.csrf().disable();
		
		
	return httpSecure.build();
		
	}
		
	
}
