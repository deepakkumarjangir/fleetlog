/**
 * FleetLog
 * Apr 26, 2019 12:23:12 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.configurations.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.deepakdaneva.fleetlog.configurations.security.jwt.JwtAuthTokenFilter;
import com.deepakdaneva.fleetlog.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) // We want to protect methods with roles on method level also
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	public static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private AuthEntryPoint jwtAuthEntryPoint;

	@Bean
	public JwtAuthTokenFilter jwtAuthTokenFilter() {
		return new JwtAuthTokenFilter();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/*
	 * AuthecationMangerBuilder Configuration of the application
	 */
	@Override
	public void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/*
	 * WebSecurity Configuration of the application
	 */
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity.ignoring().antMatchers("/resources/**");
	}

	/*
	 * HTTPSecurity Configuration of the application
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable(); // For REST Services we don't need CSRF and CORS Protection we can
													// use if needed.

		httpSecurity.addFilterBefore(jwtAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		httpSecurity.headers().cacheControl();

		httpSecurity.authorizeRequests()
					//.anyRequest().permitAll()
					.antMatchers("/").permitAll()
					.antMatchers("/api/auth/login").permitAll()
					.antMatchers("/api/**").authenticated()
				.and()
					.exceptionHandling()
					.authenticationEntryPoint(jwtAuthEntryPoint)
				.and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
