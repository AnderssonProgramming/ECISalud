
package edu.eci.cvds.ECISalud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.ECISalud.dto.AppointmentRequestDTO;
import edu.eci.cvds.ECISalud.model.Appointment;
import edu.eci.cvds.ECISalud.model.Appointment.AppointmentStatus;
import edu.eci.cvds.ECISalud.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
@Tag(name = "Appointment Controller", description = "API for managing medical appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    
    @GetMapping
    @Operation(summary = "Get all appointments", description = "Returns a list of all appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
    
    @GetMapping("/email/{email}")
    @Operation(summary = "Get appointments by email", description = "Returns appointments for a specific email")
    public ResponseEntity<List<Appointment>> getAppointmentsByEmail(@PathVariable String email) {
        List<Appointment> appointments = appointmentService.getAppointmentsByEmail(email);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
    
    @GetMapping("/filter")
    @Operation(summary = "Filter appointments by email and status", description = "Returns appointments for a specific email filtered by status")
    public ResponseEntity<List<Appointment>> getAppointmentsByEmailAndStatus(
            @RequestParam String email, 
            @RequestParam AppointmentStatus status) {
        List<Appointment> appointments = appointmentService.getAppointmentsByEmailAndStatus(email, status);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
    
    @PostMapping
    @Operation(summary = "Create a new appointment", description = "Creates a new appointment and returns it")
    public ResponseEntity<Appointment> createAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentDTO) {
        Appointment createdAppointment = appointmentService.createAppointment(appointmentDTO);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}/cancel")
    @Operation(summary = "Cancel an appointment", description = "Changes the status of an appointment to CANCELLED")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable String id) {
        Appointment cancelledAppointment = appointmentService.cancelAppointment(id);
        return new ResponseEntity<>(cancelledAppointment, HttpStatus.OK);
    }
}