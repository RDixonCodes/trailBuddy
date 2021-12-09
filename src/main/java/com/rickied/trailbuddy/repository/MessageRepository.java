package com.rickied.trailbuddy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rickied.trailbuddy.models.Message;

@Repository
public interface MessageRepository extends CrudRepository <Message, Long> {
	List<Message> findAll();
}
