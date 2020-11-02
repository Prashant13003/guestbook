package com.user.booking.service;

/**
 * 
 * Service Interface for Spring Security
 *
 */
public interface SecurityService {
	String findLoggedInUsername();

	void autoLogin(String username, String password);
}
