package com.user.booking.service;

import com.user.booking.model.User;

/**
 * 
 * Interface for User Service
 *
 */
public interface UserService {
	void save(User user);

	User findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
