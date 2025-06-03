package com.example.doctorapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime appointmentDate;
    private String status; // CONFIRMED, CANCELLED, COMPLETED

    @ManyToOne
    private User patient;

    @ManyToOne
    private Doctor doctor;
}

