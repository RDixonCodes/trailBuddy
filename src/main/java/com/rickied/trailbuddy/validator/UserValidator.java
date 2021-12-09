package com.rickied.trailbuddy.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.rickied.trailbuddy.models.User;
import com.rickied.trailbuddy.repository.UserRepository;

@Component
public class UserValidator {
	
	@Autowired
	private UserRepository tRepo;
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		User user = (User) target;
	
		if(!user.getPassword().equals(user.getPasswordConfirmation())) {
			errors.rejectValue("password", "Match", "PASSWORD DOES NOT MATCH!!");
		}
		
		if(this.tRepo.existsByEmail(user.getEmail())) {
			errors.rejectValue("email", "Unique", "Email not available!");
		}
	
	}
}
