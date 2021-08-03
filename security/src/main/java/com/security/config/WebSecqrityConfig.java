package com.security.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.service.UserService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@EnableWebSecurity
@Configurable
public class WebSecqrityConfig extends WebSecurityConfigurerAdapter{
	
	private final UserService userservice;
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**","/js/**","/img/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			  .antMatchers("/login","signup","/user").permitAll()
			  .antMatchers("/").hasRole("USER")
			  .antMatchers("/admin").hasRole("ADMIN")
			  .anyRequest().authenticated()
		.and()
			.formLogin()
			  .loginPage("/login")
			  .defaultSuccessUrl("/")
		.and()
			.logout()
			  .logoutSuccessUrl("/login")
			  .invalidateHttpSession(true);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//userservice 에서 유저정보를 가져온다
		//가져와서 passwordEncoder
		auth.userDetailsService(userservice).passwordEncoder(new BCryptPasswordEncoder());
	}
}
