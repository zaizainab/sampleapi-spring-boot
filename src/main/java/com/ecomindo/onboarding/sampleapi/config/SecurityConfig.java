package com.ecomindo.onboarding.sampleapi.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenFilter jwtTokenFilter;

	public SecurityConfig(/*Logger logger,*/ JwtTokenFilter jwtTokenFilter) {
		super();
//		this.logger = logger;
		this.jwtTokenFilter = jwtTokenFilter;
	}

	@Override
    @Order(1)
     public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs",
								   "/configuration/ui",
								   "/swagger-resources/**",
								   "/configuration/security",
								   "/swagger-ui.html",
								   "/webjars/**");
     }

	@Override
	@Order(2)
	protected void configure(HttpSecurity http) throws Exception {
		// set session to stateless
		http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

		// Set unauthorized requests exception handler
		http = http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
			//logger.error("Unauthorized request - {} " + ex.getMessage());
            System.out.println("Unauthorized request - " + ex.getMessage());
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		}).and();

		http = http.authorizeRequests().anyRequest().authenticated().and();
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
