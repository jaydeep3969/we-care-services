package com.wecare.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("CoachService")
public interface BookingCoachClient {
    @GetMapping("/coaches")
    boolean checkCoachExistence(@RequestParam("coachId") String coachId);
}
