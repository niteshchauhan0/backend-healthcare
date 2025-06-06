package com.example.doctorapp.service;

import com.example.doctorapp.model.Doctor;
import com.example.doctorapp.model.User;
import com.example.doctorapp.repository.DoctorRepository;
import com.example.doctorapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public Doctor saveDoctor(Doctor doctor) {
        Long userId = doctor.getUser().getId();  // Get just the ID from request
        Optional<User> userOpt = userRepository.findById(userId);  // Load full user from DB

        userOpt.ifPresent(doctor::setUser);  // Replace doctor.user with the full object

        return doctorRepository.save(doctor);  // Now saving doctor with full user attached
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
