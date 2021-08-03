package com.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.domain.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
	  Optional<UserInfo> findByEmail(String email);
	}