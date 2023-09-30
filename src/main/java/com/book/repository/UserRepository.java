package com.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public Optional<User> findByUsernameIgnoreCase(String username);
	
}
