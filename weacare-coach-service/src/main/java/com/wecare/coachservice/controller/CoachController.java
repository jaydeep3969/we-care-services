package com.wecare.coachservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wecare.coachservice.client.CoachBookingFeign;
import com.wecare.coachservice.dto.BookingDTO;
import com.wecare.coachservice.dto.CoachDTO;
import com.wecare.coachservice.dto.LoginDTO;
import com.wecare.coachservice.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coaches")
@Validated
@CrossOrigin
public class CoachController {
	@Autowired
	CoachService coachService;

//	@Autowired
//	RestTemplate restTemplate;

//	@Value("${bookingsUri}")
//	String bookingUri;

	@Autowired
	CoachBookingFeign bookingFeign;
	
	@GetMapping("/all")
	public List<CoachDTO> showAllCoaches() {
		return this.coachService.showAllCoaches();
	}
	
	@GetMapping("/{coachId}")
	public Optional<CoachDTO> getCoachProfile(@PathVariable String coachId) {
		return this.coachService.getCoachProfile(coachId);
	}

	@PostMapping
	public ResponseEntity<String> createCoach(@Valid @RequestBody CoachDTO coachDTO) {
		String result = this.coachService.createCoach(coachDTO);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Boolean> loginCoach(@RequestBody LoginDTO loginDTO) {
		return ResponseEntity.ok(this.coachService.loginCoach(loginDTO));
	}

	@HystrixCommand(fallbackMethod = "showMyAppointmentsFB")
	@GetMapping("/booking/{coachId}")
	public List<BookingDTO> showMyAppointments(@PathVariable String coachId) {
		return bookingFeign.fetchMyAppointments(coachId, LocalDate.now().toString());
	}

	public List<BookingDTO> showMyAppointmentsFB(@PathVariable String coachId) {
		return new ArrayList<BookingDTO>();
	}

	@GetMapping
	public boolean checkCoachExistence(@RequestParam("coachId") String coachId) {
		return coachService.checkUserExistence(coachId);
	}
}
