package com.rickied.trailbuddy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rickied.trailbuddy.models.Trip;

@Repository
public interface TripRepository extends CrudRepository<Trip, Long>{
	List<Trip> findAll();
	List<Trip> findByState(String state);
	List<Trip> findByStateIsNot(String state);
}
