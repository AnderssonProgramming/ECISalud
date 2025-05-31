package edu.eci.cvds.ECISalud.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import edu.eci.cvds.ECISalud.dto.AppointmentRequestDTO;
import edu.eci.cvds.ECISalud.model.Appointment;
import edu.eci.cvds.ECISalud.model.Appointment.AppointmentStatus;
import edu.eci.cvds.ECISalud.model.Specialty;
import edu.eci.cvds.ECISalud.repository.AppointmentRepository;
import edu.eci.cvds.ECISalud.repository.SpecialtyRepository;

@SpringBootTest
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;
    
    @Mock
    private SpecialtyRepository specialtyRepository;
    
    @InjectMocks
    private AppointmentService appointmentService;
    
    private Appointment testAppointment;
    private AppointmentRequestDTO testRequest;
    private Specialty testSpecialty;
    
    @BeforeEach
    void setUp() {
        // Setup test data
        testAppointment = new Appointment();
        testAppointment.setId("test-id");
        testAppointment.setPatientName("John Doe");
        testAppointment.setPatientId("12345");
        testAppointment.setEmail("john@example.com");
        testAppointment.setDate(LocalDate.now().plusDays(1));
        testAppointment.setSpecialty("specialty-id");
        testAppointment.setDoctor("Dr. Smith");
        testAppointment.setLocation("Room 101");
        testAppointment.setStatus(AppointmentStatus.CONFIRMED);
        
        testRequest = new AppointmentRequestDTO();
        testRequest.setPatientName("John Doe");
        testRequest.setPatientId("12345");
        testRequest.setEmail("john@example.com");
        testRequest.setDate(LocalDate.now().plusDays(1));
        testRequest.setSpecialtyId("specialty-id");
        
        testSpecialty = new Specialty();
        testSpecialty.setId("specialty-id");
        testSpecialty.setName("Medicina General");
        testSpecialty.setDoctor("Dr. Smith");
        testSpecialty.setLocation("Room 101");
    }
    
    @Test
    void testGetAllAppointments() {
        // Setup
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(testAppointment);
        when(appointmentRepository.findAll()).thenReturn(appointments);
        
        // Execute
        List<Appointment> result = appointmentService.getAllAppointments();
        
        // Verify
        assertEquals(1, result.size());
        assertEquals("test-id", result.get(0).getId());
        verify(appointmentRepository, times(1)).findAll();
    }
    
    @Test
    void testGetAppointmentById() {
        // Setup
        when(appointmentRepository.findById("test-id")).thenReturn(Optional.of(testAppointment));
        
        // Execute
        Optional<Appointment> result = appointmentService.getAppointmentById("test-id");
        
        // Verify
        assertTrue(result.isPresent());
        assertEquals("test-id", result.get().getId());
        verify(appointmentRepository, times(1)).findById("test-id");
    }
    
    @Test
    void testGetAppointmentsByEmail() {
        // Setup
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(testAppointment);
        when(appointmentRepository.findByEmail("john@example.com")).thenReturn(appointments);
        
        // Execute
        List<Appointment> result = appointmentService.getAppointmentsByEmail("john@example.com");
        
        // Verify
        assertEquals(1, result.size());
        assertEquals("john@example.com", result.get(0).getEmail());
        verify(appointmentRepository, times(1)).findByEmail("john@example.com");
    }
    
    @Test
    void testGetAppointmentsByEmailAndStatus() {
        // Setup
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(testAppointment);
        when(appointmentRepository.findByEmailAndStatus("john@example.com", AppointmentStatus.CONFIRMED)).thenReturn(appointments);
        
        // Execute
        List<Appointment> result = appointmentService.getAppointmentsByEmailAndStatus("john@example.com", AppointmentStatus.CONFIRMED);
        
        // Verify
        assertEquals(1, result.size());
        assertEquals(AppointmentStatus.CONFIRMED, result.get(0).getStatus());
        verify(appointmentRepository, times(1)).findByEmailAndStatus("john@example.com", AppointmentStatus.CONFIRMED);
    }
    
    @Test
    void testCreateAppointment_ValidDate() {
        // Setup
        when(specialtyRepository.findById("specialty-id")).thenReturn(Optional.of(testSpecialty));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(testAppointment);
        
        // Execute
        Appointment result = appointmentService.createAppointment(testRequest);
        
        // Verify
        assertNotNull(result);
        assertEquals(AppointmentStatus.CONFIRMED, result.getStatus());
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }
    
    @Test
    void testCreateAppointment_PastDate() {
        // Setup
        testRequest.setDate(LocalDate.now().minusDays(1)); // Past date
        
        // Execute
        Appointment result = appointmentService.createAppointment(testRequest);
        
        // Verify
        assertNotNull(result);
        assertEquals(AppointmentStatus.CANCELLED, result.getStatus());
    }
    
    @Test
    void testCancelAppointment() {
        // Setup
        when(appointmentRepository.findById("test-id")).thenReturn(Optional.of(testAppointment));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(testAppointment);
        
        // Execute
        Appointment result = appointmentService.cancelAppointment("test-id");
        
        // Verify
        assertNotNull(result);
        assertEquals(AppointmentStatus.CANCELLED, result.getStatus());
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }
}
