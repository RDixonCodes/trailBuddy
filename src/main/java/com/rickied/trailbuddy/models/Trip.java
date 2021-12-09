package com.rickied.trailbuddy.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="trips")
public class Trip {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	@Future(message="Must be in the future, must not be blank.")
	@DateTimeFormat(pattern="yyy-MM-dd")
	private Date tripDate;
	
	@NotEmpty
	private String city;
	
	private String state;
	
	@OneToMany(mappedBy="commentTrip", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Message> messages;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="host_id")
	private User host;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="joinTrips",
				joinColumns=@JoinColumn(name="trip_id"),
				inverseJoinColumns=@JoinColumn(name="user_id")
				)
	
	private List<User> joinedUsers;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyy-MM-DD HH:mm:ss")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-DD HH:mm:ss")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	public String getTripDateFormatted() {
		SimpleDateFormat df = new SimpleDateFormat("dd,MM,YYYY");
		return df.format(this.tripDate);
	}
	
	public Trip() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getTripDate() {
		return tripDate;
	}
	
	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public User getHost() {
		return host;
	}
	public void setHost(User host) {
		this.host = host;
	}
	public List<User> getJoinedUsers() {
		return joinedUsers;
	}
	public void setJoinedUsers(List<User> joinedUsers) {
		this.joinedUsers = joinedUsers;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
