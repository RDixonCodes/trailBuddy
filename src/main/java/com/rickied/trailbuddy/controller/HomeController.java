package com.rickied.trailbuddy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rickied.trailbuddy.models.Message;
import com.rickied.trailbuddy.models.Trip;
import com.rickied.trailbuddy.models.User;
import com.rickied.trailbuddy.services.MessageService;
import com.rickied.trailbuddy.services.TripService;
import com.rickied.trailbuddy.services.UserService;
import com.rickied.trailbuddy.validator.UserValidator;

@Controller
public class HomeController {
	@Autowired
	private UserService uService;
	
	@Autowired
	private TripService tService;
	
	@Autowired
	private MessageService mService;
	
	@Autowired
	private UserValidator uVal;
	
	@GetMapping("/")
	public String home(User user) {
		return "home.jsp";
	}
	
	@GetMapping("/logreg")
	public String reg(User user) {
		return "logreg.jsp";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		
		this.uVal.validate(user, result);
		
		if(result.hasErrors()) {
			return "logreg.jsp";
		}
		User newUser = this.uService.registration(user);
		System.out.println(user.getFirstName() + " has been registered as a user.");
		session.setAttribute("user_id", newUser.getId());
		return "dashboard.jsp";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes reAtts, Model model, HttpSession session) {
		
		if(!this.uService.authenticateUser(email, password)) {
			reAtts.addFlashAttribute("error", "Invalid Credentials");
			return "redirect:/logreg";
		}
		
		User user = this.uService.findByEmail(email);
		session.setAttribute("user_id", user.getId());
		System.out.println(user.getFirstName() + " has been logged in");
		return "redirect:/trips";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		System.out.println("You have been logged out");
		return "redirect:/";
	}
	
	public Long userSessionId(HttpSession session) {
		if(session.getAttribute("userId") == null)
			return null;
		
		return (Long)session.getAttribute("userId");
	}
	
	private String now() {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(new Date());
	}
	
	@GetMapping("/trips")
	public String trips(@ModelAttribute("trip") Trip trip, Model model, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		User user = this.uService.getOneUser(userId);
		if(userId == null) {
			return "redirect:/logreg";
		}
		
		List<Trip> userTrips = this.tService.allTripsWithState(user.getState());
		List<Trip> otherTrips = this.tService.allTripsNotState(user.getState());
		model.addAttribute("userTrips", userTrips);
		model.addAttribute("otherTrips", otherTrips);
		model.addAttribute("trip", trip);
		model.addAttribute("user", user);
		return "dashboard.jsp";
	}
	
	@PostMapping("/trips/new")
	public String trips(@Valid @ModelAttribute("trip") Trip trip, BindingResult result, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		User user = this.uService.getOneUser(userId);
		if(userId == null) {
			return "redirect:/logreg";
		}
		
		if(result.hasErrors()) {
			List<Trip> userTrips = this.tService.allTripsWithState(user.getState());
			List<Trip> otherTrips = this.tService.allTripsNotState(user.getState());
			model.addAttribute("userTrips", userTrips);
			model.addAttribute("otherTrips", otherTrips);
			model.addAttribute("user", user);
			model.addAttribute("tripDate", now());
			return "dashboard.jsp";
		}
		
		trip.setHost(user);
		this.tService.createTrip(trip);
		return "redirect:/trips";
	}
	
	//join trip
	@GetMapping("/join/{id}")
	public String joinTrip(@PathVariable("id") Long id, HttpSession session) {
		Trip joinedTrip = this.tService.getOneTrip(id);
		User joinedUser = this.uService.getOneUser((long)session.getAttribute("user_id"));
		System.out.println(joinedTrip);
		this.tService.joinedTrip(joinedUser, joinedTrip);
		return "redirect:/trips";
	}
	//cancel trip
	@GetMapping("/trips/cancel/{id}")
	public String cancelTrip(@PathVariable("id") Long id, HttpSession session) {
		Trip joinedTrip = this.tService.getOneTrip(id);
		User joinedUser = this.uService.getOneUser((Long)session.getAttribute("user_id"));
		this.tService.cancelTrip(joinedUser, joinedTrip);
		return "redirect:/trips";
	}
	
	@GetMapping("/trips/delete/{id}")
	public String deleteTrip(@PathVariable("id") Long id) {
		this.tService.deleteTrip(id);
		return "redirect:/trips";
	}
	
	@GetMapping("/trips/edit/{id}")
	public String editTrip(@ModelAttribute("updateTrip") Trip trip, @PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		Trip thisTrip = this.tService.getOneTrip(id);
		if(userId == null) {
			return "redirect:/logreg";
		}
		
		if(!thisTrip.getHost().getId().equals(userId)) {
			return "redirect:/trips";
		}
		
		User user = this.uService.getOneUser(userId);
		model.addAttribute("thisTirp", thisTrip);
		model.addAttribute("user", user);
		return "edit.jsp";
	}
	
	@PostMapping("/events/edit/{id}")
	public String editEvents(@Valid @ModelAttribute("updateTrip") Trip updatedTrip, BindingResult result, @PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		User user = this.uService.getOneUser(userId);
		Trip thisTrip = this.tService.getOneTrip(id);
		if(result.hasErrors()) {
			model.addAttribute("updateTrip", this.tService.getOneTrip(id));
			return "edit.jsp";
		}
		
		model.addAttribute("user", user);
		model.addAttribute("updateTrip", thisTrip);
		updatedTrip.setHost(user);
		this.tService.updateTrip(updatedTrip);
		return "redirect:/trips";
	}
	
	//Show trip
	@GetMapping("/trips/{id}")
	public String showTrip(@ModelAttribute("message") Message message, @PathVariable("id") Long id, HttpSession session, Model model) {
	 Long userId = (Long) session.getAttribute("user_id");
	 User user = this.uService.findById(userId);
	 Trip trip = this.tService.getOneTrip(id);
	 if(session.getAttribute("user_id") == null) {
	 	return "redirect:/logreg";
	 }

	 if(trip == null) {
		 return "redirect:/trips";
	 }
	 
	 model.addAttribute("user", user);
	 model.addAttribute("trip", trip);
	 return "info.jsp";
	 }
	
	@PostMapping("/trips/{id}/messages")
	public String postMessages(@Valid @ModelAttribute("message") Message message, BindingResult result, @PathVariable("id") Long id, HttpSession session) {
		System.out.println(message.getComment());
		Long userId = (Long) session.getAttribute("user_id");
		User thisUser = this.uService.getOneUser(userId);
		Trip trip = this.tService.getOneTrip(id);
		if(result.hasErrors()) {
			return "redirect: info.jsp";
		}
		
		message.setAuthor(thisUser);
		message.setCommentTrip(trip);
		this.mService.addMessage(message);
		return "redirect:/trips/{id}";
	}
	
	
	
}
