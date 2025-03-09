package com.example.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.example.security.exception.UserNotValid;

@RestController
public class WelcomeController {
	
	@GetMapping("")
	public String welcome() {
		try {
			return "Welcome to Spring Security";
		}catch (Unauthorized exception) {
			throw new UserNotValid("Please login with correct Your");
		}
		
	}

	@GetMapping("/token")
	public CsrfToken getToken(HttpServletRequest request){
		return (CsrfToken) request.getAttribute("_csrf");
	}

}
