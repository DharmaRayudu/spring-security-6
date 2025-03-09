package com.example.security.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@Component
public class CustomeExceptions implements AuthenticationEntryPoint {
	
//	@ExceptionHandler(UserNotValid.class)
//	public ResponseEntity<String> handleUnauthorizedExceptions(UserNotValid ex) {
//
//		return ResponseEntity.ok(ex.getMessage());
//	}


	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());

		String message = authException.getMessage();
		String jsonResponse = String.format("{\"error\": \"Unauthorized\", \"message\": \"%s\"}", message);

		response.getWriter().write(jsonResponse);
	}
}
