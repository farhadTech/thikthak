package com.thikthak.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/bookings")
public class AdminBookingController {

    @GetMapping
    public String getAllBookings() {
        return "All bookings (ADMIN ONLY)";
    }

    @PutMapping("/{id}/status")
    public String updateBookingStatus(@PathVariable Long id) {
        return "Booking " + id + " status updated";
    }
}
