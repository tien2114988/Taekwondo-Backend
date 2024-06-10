package com.taekwondo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taekwondo.entity.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String>{
	public Optional<User> findByUsername(String username);
}
