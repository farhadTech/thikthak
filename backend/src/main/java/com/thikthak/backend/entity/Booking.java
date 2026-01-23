package com.thikthak.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String service;

    private LocalDate preferredDate;
    private String preferredTime;

    @Column(length = 500)
    private String address;

    private String status = "PENDING"; // PENDING, CONFIRMED, COMPLETED

    private LocalDateTime createdAt;
}
