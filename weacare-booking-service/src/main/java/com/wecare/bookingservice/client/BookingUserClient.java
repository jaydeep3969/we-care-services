package com.wecare.bookingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("UserService")
public interface BookingUserClient {
    @GetMapping("/users")
    boolean checkUserExistence(@RequestParam("userId") String userId);
}
