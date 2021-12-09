package com.rickied.trailbuddy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rickied.trailbuddy.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
		List<User> findAll();
		User findByEmail(String email);
		boolean existsByEmail(String email);
}
