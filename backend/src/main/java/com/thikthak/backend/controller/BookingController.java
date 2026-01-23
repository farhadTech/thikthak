package com.thikthak.backend.controller;

import com.thikthak.backend.entity.Booking;
import com.thikthak.backend.repository.BookingRepository;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
        booking.setStatus("PENDING");
        booking.setCreatedAt(LocalDateTime.now());
        return bookingRepository.save(booking);
    }

    // 📌 Admin dashboard – get all bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // ADMIN UPDATE STATUS
    @PutMapping("/{id}/status")
    public Booking updateStatus(@PathVariable Long id) {
        Booking b = bookingRepository.findById(id).orElseThrow();
        b.setStatus(b.getStatus().equals("PENDING") ? "COMPLETED" : "PENDING");
        return bookingRepository.save(b);
    }

    @PostMapping("/whatsapp")
    public String sendWhatsapp(@RequestBody Booking booking) {
        // Save booking first
        booking.setStatus("NEW");
        booking.setCreatedAt(LocalDateTime.now());
        Booking savedBooking = bookingRepository.save(booking);

        // Generate WhatsApp message link
        String phoneNumber = "8801744513221"; // your number
        String message = "📢 New Booking\n" +
                "Name: " + savedBooking.getName() + "\n" +
                "Phone: " + savedBooking.getPhone() + "\n" +
                "Service: " + savedBooking.getService();

        String whatsappLink = "https://wa.me/" + phoneNumber + "?text=" +
                URLEncoder.encode(message, StandardCharsets.UTF_8);

        return whatsappLink;
    }
}
