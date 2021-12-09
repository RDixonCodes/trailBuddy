package com.rickied.trailbuddy.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickied.trailbuddy.models.User;
import com.rickied.trailbuddy.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository uRepo;
	
	public User registration(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return this.uRepo.save(user);
	}
	
	public User findByEmail(String email) {
		return this.uRepo.findByEmail(email);
	}
	
	public User findById(Long id) {
		Optional<User> user = this.uRepo.findById(id);
		if(user.isPresent()) {
			return user.get();
		}else {
			return null;
		}
	}
	
	public boolean authenticateUser(String email, String password) {
		User user = this.uRepo.findByEmail(email);
		if(user == null) {
			return false;
		} else {
			if(BCrypt.checkpw(password,  user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public List<User> getAllUsers(){
		return this.uRepo.findAll();
	}
	
	public User getOneUser(Long id) {
		return this.uRepo.findById(id).orElse(null);
	}
	
}
