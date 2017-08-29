package com.media.smartcore.config;

import com.media.smartcore.authentication.MyDBAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
// @EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	MyDBAuthenticationService myDBAauthenticationService;
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Các User trong Database
		auth.userDetailsService(myDBAauthenticationService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// Các yêu cầu phải login với vai trò EMPLOYEE hoặc MANAGER.
		// Nếu chưa login, nó sẽ redirect tới trang /login.
		http.authorizeRequests().antMatchers("/orderList","/order", "/accountInfo")//
			.access("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_MANAGER')");

		// Trang chỉ dành cho MANAGER
		http.authorizeRequests().antMatchers("/product").access("hasRole('ROLE_MANAGER')");
		// Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()
			.loginProcessingUrl("/j_spring_security_check")
			.loginPage("/login")
			.defaultSuccessUrl("/accountInfo")
			.failureUrl("/login?error=true")
			.usernameParameter("userName")
			.passwordParameter("password")
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
	}
}