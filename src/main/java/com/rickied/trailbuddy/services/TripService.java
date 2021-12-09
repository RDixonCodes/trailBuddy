package com.rickied.trailbuddy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rickied.trailbuddy.models.Trip;
import com.rickied.trailbuddy.models.User;
import com.rickied.trailbuddy.repository.TripRepository;

@Service
public class TripService {
	
	@Autowired
	private TripRepository tRepo;
	
	public List<Trip> getAllTrips(){
		return this.tRepo.findAll();
	}
	
	public Trip getOneTrip(Long id) {
		return this.tRepo.findById(id).orElse(null);
	}
	
	public Trip createTrip(Trip trip) {
		return this.tRepo.save(trip);
	}
	
	public Trip updateTrip(Trip updatedTrip) {
		return this.tRepo.save(updatedTrip);
	}
	
	public void deleteTrip(Long id) {
		this.tRepo.deleteById(id);
	}
	
	public List<Trip> allTripsWithState(String state) {
		return this.tRepo.findByState(state);
	}
	
	public List<Trip> allTripsNotState(String state) {
		return this.tRepo.findByStateIsNot(state);
	}
	
	public void joinedTrip(User user, Trip trip) {
		List<User> joinedUsers = trip.getJoinedUsers();
		joinedUsers.add(user);
		this.tRepo.save(trip);
	}
	
	public void cancelTrip(User user, Trip trip) {
		List<User> joinedUsers = trip.getJoinedUsers();
		joinedUsers.remove(user);
		this.tRepo.save(trip);
	}
}
