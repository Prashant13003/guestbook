package com.user.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.booking.model.Role;

/**
 * 
 * JPA Repository Interface for table Role responsible for DB Operation
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String roleName);
}
