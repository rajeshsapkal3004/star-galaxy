package com.cartwheel.galaxy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cartwheel.galaxy.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String username);
	User findById(int id);
	User findByEmailIgnoreCase(String emailId);
}
