package com.wecare.userservice.clients;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wecare.userservice.dto.BookingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient("BookingService")
public interface UserBookingFeign {

    @GetMapping("/booking/users")
    List<BookingDTO> fetchMyAppointments(@RequestParam("userId") String userId, @RequestParam("appointmentDate") String appointmentDate);
}
