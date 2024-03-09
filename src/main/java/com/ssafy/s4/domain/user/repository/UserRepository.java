package com.ssafy.s4.domain.user.repository;

import com.ssafy.s4.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
