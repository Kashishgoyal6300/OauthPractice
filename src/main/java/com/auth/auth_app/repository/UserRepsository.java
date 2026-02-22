package com.auth.auth_app.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.auth_app.entity.User;

public interface UserRepsository extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
}
