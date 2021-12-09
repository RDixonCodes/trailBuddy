package com.rickied.trailbuddy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickied.trailbuddy.models.Message;
import com.rickied.trailbuddy.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository mRepo;
	
	public List<Message> getAllMessages() {
		return this.mRepo.findAll();
	}
	
	public Message addMessage(Message message) {
		return this.mRepo.save(message);
	}
}
