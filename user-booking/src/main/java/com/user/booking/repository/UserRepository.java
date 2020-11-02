package com.user.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.booking.model.User;

/**
 * 
 * JPA Repository Interface for table User responsible for DB Operation
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
