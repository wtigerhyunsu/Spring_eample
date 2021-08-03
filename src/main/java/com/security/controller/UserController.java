package com.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.security.dto.UserInfoDTO;
import com.security.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	private final UserService userservice;
	
	@PostMapping("/user")
	public String signup(UserInfoDTO infoDto) {
		
		userservice.save(infoDto);
		
		return "redirect:/logon";
	}
	@GetMapping(value="/logout")
	public String logoutPage(HttpServletRequest requst, HttpServletResponse reponse) {
		new SecurityContextLogoutHandler().logout(requst, reponse, SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/login";
	}
}
