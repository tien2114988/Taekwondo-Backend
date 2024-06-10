package com.taekwondo.repository;

import com.taekwondo.entity.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvalidTokenRepository extends JpaRepository<InvalidToken, String> {
}
