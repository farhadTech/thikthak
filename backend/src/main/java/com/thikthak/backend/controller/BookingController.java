package com.thikthak.backend.controller;

import com.thikthak.backend.entity.Booking;
import com.thikthak.backend.repository.BookingRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*") // allow Netlify frontend
public class BookingController {

    private final BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // 📌 Create booking (FROM CUSTOMER)
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        booking.setStatus("NEW");
        booking.setCreatedAt(LocalDateTime.now());
        return bookingRepository.save(booking);
    }

    // 📌 Admin dashboard – get all bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
