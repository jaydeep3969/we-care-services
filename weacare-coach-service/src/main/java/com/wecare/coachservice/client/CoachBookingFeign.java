package com.wecare.coachservice.client;

import com.wecare.coachservice.dto.BookingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("BookingService")
public interface CoachBookingFeign {
    @GetMapping("/booking/coaches")
    List<BookingDTO> fetchMyAppointments(@RequestParam("coachId") String coachId, @RequestParam("appointmentDate") String appointmentDate);
}
