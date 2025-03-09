package com.example.security.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotValid extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5197792886831551233L;

	public UserNotValid(String msg) {

		super(msg);
	}
}
