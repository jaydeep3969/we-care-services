package com.wecare.userservice.controller;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wecare.userservice.clients.UserBookingFeign;
import com.wecare.userservice.dto.BookingDTO;
import com.wecare.userservice.dto.LoginDTO;
import com.wecare.userservice.dto.UserDTO;
import com.wecare.userservice.exception.WecareException;
import com.wecare.userservice.service.UserService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
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
@RequestMapping("/users")
@Validated
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

//    @Autowired
//    RestTemplate restTemplate;

//    @Value("${bookingUri}")
//    String bookingUri;

    @Autowired
    UserBookingFeign bookingFeign;

    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) throws WecareException {
        String result = userService.createUser(userDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public UserDTO getUserProfile(@PathVariable String userId) throws WecareException {
        return this.userService.getUserProfile(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(this.userService.loginUser(loginDTO));
    }

    @HystrixCommand(fallbackMethod = "showMyAppointmentsFB")
    @GetMapping("/booking/{userId}")
    public List<BookingDTO> showMyAppointments(@PathVariable String userId) {
        return bookingFeign.fetchMyAppointments(userId, LocalDate.now().toString());
    }

    public List<BookingDTO> showMyAppointmentsFB(@PathVariable String userId) {
        System.out.println("====In Fallback to show my appointments====");
        return new ArrayList<BookingDTO>();
    }

    @GetMapping
    public boolean checkUserExistence(@RequestParam("userId") String userId) {
        return userService.checkUserExistence(userId);
    }
}
