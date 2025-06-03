package com.example.doctorapp.controller;

import com.example.doctorapp.model.Appointment;
import com.example.doctorapp.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public Appointment book(@RequestBody Appointment appointment) {
        return appointmentService.bookAppointment(appointment);
    }

    @GetMapping("/doctor/{id}")
    public List<Appointment> getByDoctor(@PathVariable Long id) {
        return appointmentService.getAppointmentsByDoctor(id);
    }

    @GetMapping("/patient/{id}")
    public List<Appointment> getByPatient(@PathVariable Long id) {
        return appointmentService.getAppointmentsByPatient(id);
    }
}

