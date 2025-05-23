package edu.eci.cvds.ECISalud.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.ECISalud.dto.AppointmentRequestDTO;
import edu.eci.cvds.ECISalud.model.Appointment;
import edu.eci.cvds.ECISalud.model.Appointment.AppointmentStatus;
import edu.eci.cvds.ECISalud.model.Specialty;
import edu.eci.cvds.ECISalud.repository.AppointmentRepository;
import edu.eci.cvds.ECISalud.repository.SpecialtyRepository;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private SpecialtyRepository specialtyRepository;
    
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    
    public Optional<Appointment> getAppointmentById(String id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAppointmentsByEmail(String email) {
        return appointmentRepository.findByEmail(email);
    }

    public List<Appointment> getAppointmentsByEmailAndStatus(String email, AppointmentStatus status) {
        return appointmentRepository.findByEmailAndStatus(email, status);
    }

    public Appointment createAppointment(AppointmentRequestDTO appointmentRequest) {
        Appointment appointment = new Appointment();
        appointment.setPatientName(appointmentRequest.getPatientName());
        appointment.setPatientId(appointmentRequest.getPatientId());
        appointment.setEmail(appointmentRequest.getEmail());
        appointment.setDate(appointmentRequest.getDate());
        appointment.setSpecialty(appointmentRequest.getSpecialtyId());
        appointment.setStatus(AppointmentStatus.PENDING);
        
        return appointmentRepository.save(appointment);
    }

    public Appointment cancelAppointment(String id) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            appointment.setStatus(AppointmentStatus.CANCELLED);
            return appointmentRepository.save(appointment);
        }
        return null; // or throw an exception
    }
}