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

    private final AppointmentRepository appointmentRepository;
    private final SpecialtyRepository specialtyRepository;
    
    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, SpecialtyRepository specialtyRepository) {
        this.appointmentRepository = appointmentRepository;
        this.specialtyRepository = specialtyRepository;
    }
    
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
        // Validate that appointment date is not in the past
        LocalDate currentDate = LocalDate.now();
        if (appointmentRequest.getDate().isBefore(currentDate)) {
            Appointment rejectedAppointment = new Appointment();
            rejectedAppointment.setPatientName(appointmentRequest.getPatientName());
            rejectedAppointment.setPatientId(appointmentRequest.getPatientId());
            rejectedAppointment.setEmail(appointmentRequest.getEmail());
            rejectedAppointment.setDate(appointmentRequest.getDate());
            rejectedAppointment.setSpecialty(appointmentRequest.getSpecialtyId());
            rejectedAppointment.setStatus(AppointmentStatus.CANCELLED);
            return appointmentRepository.save(rejectedAppointment);
        }
        
        // Get specialty details
        Optional<Specialty> specialtyOpt = specialtyRepository.findById(appointmentRequest.getSpecialtyId());
        Specialty specialty = specialtyOpt.orElse(null);
        
        Appointment appointment = new Appointment();
        appointment.setPatientName(appointmentRequest.getPatientName());
        appointment.setPatientId(appointmentRequest.getPatientId());
        appointment.setEmail(appointmentRequest.getEmail());
        appointment.setDate(appointmentRequest.getDate());
        appointment.setSpecialty(appointmentRequest.getSpecialtyId());
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        
        if (specialty != null) {
            appointment.setDoctor(specialty.getDoctor());
            appointment.setLocation(specialty.getLocation());
        }
        
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