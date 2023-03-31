package com.wecare.bookingservice.controller;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.wecare.bookingservice.client.BookingCoachClient;
import com.wecare.bookingservice.client.BookingUserClient;
import com.wecare.bookingservice.dto.BookingDTO;
import com.wecare.bookingservice.dto.ErrorMessage;
import com.wecare.bookingservice.exception.ExceptionConstants;
import com.wecare.bookingservice.exception.WecareException;
import com.wecare.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Future;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/booking")
public class BookingController {
	@Autowired
	BookingService bookingService;
//	@Autowired
//	RestTemplate restTemplate;

//	@Value("${users.existence}")
//	String usersExistenceUri;

//	@Value("${coaches.existence}")
//	String coachesExistenceUri;

	@Autowired
	BookingUserClient userClient;

	@Autowired
	BookingCoachClient coachClient;

	@HystrixCommand(fallbackMethod = "bookAppointmentFB", ignoreExceptions = WecareException.class)
	@PostMapping("/{userId}/{coachId}")
	public ResponseEntity<String> bookAppointment(@PathVariable @NotNull(message = "{user.userid.must}") String userId,
												  @PathVariable @NotNull(message = "{coach.coachid.must}") String coachId,
												  @RequestParam("appointmentDate") @NotEmpty(message = "{appointmentDate.must}") String appointmentDate,
												  @RequestParam("slot") @NotEmpty(message = "{slot.must}") String slot)
													throws Exception {
		/*
		Future<Boolean> userExists = checkUserExistence(userId);
		Future<Boolean> coachExists = checkCoachExistence(coachId);

		if(!userExists.get()) {
			throw new WecareException(ExceptionConstants.USER_NOT_FOUND.toString());
		}

		if(!coachExists.get()) {
			throw new WecareException(ExceptionConstants.COACH_NOT_FOUND.toString());
		}
		*/

		if(!checkUserExistence(userId)) {
			throw new WecareException(ExceptionConstants.USER_NOT_FOUND.toString());
		}

		if(!checkCoachExistence(coachId)) {
			throw new WecareException(ExceptionConstants.COACH_NOT_FOUND.toString());
		}

		String result = this.bookingService.bookAppointment(userId, coachId, LocalDate.parse(appointmentDate), slot);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	public ResponseEntity<String> bookAppointmentFB(@PathVariable @NotNull(message = "{user.userid.must}") String userId,
												  @PathVariable @NotNull(message = "{coach.coachid.must}") String coachId,
												  @RequestParam("appointmentDate") @NotEmpty(message = "{appointmentDate.must}") String appointmentDate,
												  @RequestParam("slot") @NotEmpty(message = "{slot.must}") String slot)
			throws WecareException {
		return new ResponseEntity<>("Something Went Wrong, Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{bookingId}")
	public ResponseEntity<BookingDTO> getBooking(@PathVariable Integer bookingId) {
		BookingDTO booking = this.bookingService.getBooking(bookingId);
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}
	
	@PutMapping("/{bookingId}")
	public ResponseEntity<String> rescheduleAppointment(@PathVariable Integer bookingId,
														@RequestParam("appointmentDate") @NotEmpty(message = "{appointmentDate.must}") String appointmentDate,
														@RequestParam("slot") @NotEmpty(message = "{slot.must}") String slot) {
		String result = this.bookingService.rescheduleAppointment(bookingId, LocalDate.parse(appointmentDate), slot);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{bookingId}")
	public ResponseEntity<String> cancelAppointment(@PathVariable Integer bookingId) {
		String result = this.bookingService.cancelAppointment(bookingId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/users/{userId}")
	public List<BookingDTO> getAllAppointmentsForUser(@PathVariable("userId") String userId) {
		List<BookingDTO> result =bookingService.getAllAppointmentsForUser(userId);
		return result;
	}

	@GetMapping("/users")
	public List<BookingDTO> getAllAppointmentsForUserByDate(@RequestParam("userId") String userId,
													  		@RequestParam("appointmentDate") String appointmentDate) {
		List<BookingDTO> result =bookingService.getAllAppointmentsForUserByDate(userId, LocalDate.parse(appointmentDate));
		return result;
	}

	@GetMapping("/coaches/{coachId}")
	public List<BookingDTO> getAllAppointmentsForCoach(@PathVariable("coachId") String coachId) {
		return bookingService.getAllAppointmentsForCoach(coachId);
	}

	@GetMapping("/coaches")
	public List<BookingDTO> getAllAppointmentsForCoachByDate(@RequestParam("coachId") String coachId,
															 @RequestParam("appointmentDate") String appointmentDate) {
		return bookingService.getAllAppointmentsForCoachByDate(coachId, LocalDate.parse(appointmentDate));
	}

	private boolean checkUserExistence(String userId) {
		return userClient.checkUserExistence(userId);
	}

//	private Future<Boolean> checkUserExistence(String userId) {
//		String usersExistenceUrl = "http://UserService" + "/users";
//
//		return new AsyncResult<Boolean>() {
//			@Override
//			public Boolean invoke() {
//				return restTemplate.getForObject(usersExistenceUrl + "?userId=" + userId, Boolean.class);
//			}
//		};
//	}

	private boolean checkCoachExistence(String coachId) {
		return coachClient.checkCoachExistence(coachId);
	}

//	private Future<Boolean> checkCoachExistence(String coachId) {
//		String coachesExistenceUrl = "http://CoachService" + "/coaches";
//
//		return new AsyncResult<Boolean>() {
//			@Override
//			public Boolean invoke() {
//				return restTemplate.getForObject(coachesExistenceUrl + "?coachId=" + coachId, Boolean.class);
//			}
//		};
//	}


}
