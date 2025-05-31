package edu.eci.cvds.ECISalud.controller;

import edu.eci.cvds.ECISalud.dto.AppointmentRequestDTO;
import edu.eci.cvds.ECISalud.model.Appointment;
import edu.eci.cvds.ECISalud.model.Appointment.AppointmentStatus;
import edu.eci.cvds.ECISalud.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    private AppointmentController appointmentController;

    private Appointment mockAppointment;
    private List<Appointment> mockAppointments;
    private AppointmentRequestDTO mockRequestDTO;
      @BeforeEach
    void setUp() {
        // Initialize controller with mocked service
        appointmentController = new AppointmentController(appointmentService);
        
        // Setup mock appointment
        mockAppointment = new Appointment();
        mockAppointment.setId("1");
        mockAppointment.setEmail("patient@example.com");
        mockAppointment.setDate(LocalDate.now().plusDays(1));
        mockAppointment.setStatus(AppointmentStatus.CONFIRMED);

        // Setup mock appointments list
        mockAppointments = Arrays.asList(mockAppointment);
        
        // Setup mock request DTO
        mockRequestDTO = new AppointmentRequestDTO();
        mockRequestDTO.setPatientName("Test Patient");
        mockRequestDTO.setPatientId("12345");
        mockRequestDTO.setEmail("patient@example.com");
        mockRequestDTO.setDate(LocalDate.now().plusDays(1));
        mockRequestDTO.setSpecialtyId("specialty-1");
    }

    @Test
    void getAllAppointments_ShouldReturnAllAppointments() {
        // Arrange
        when(appointmentService.getAllAppointments()).thenReturn(mockAppointments);

        // Act
        ResponseEntity<List<Appointment>> response = appointmentController.getAllAppointments();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAppointments, response.getBody());
        verify(appointmentService).getAllAppointments();
    }

    @Test
    void getAppointmentsByEmail_ShouldReturnAppointmentsForEmail() {
        // Arrange
        String email = "patient@example.com";
        when(appointmentService.getAppointmentsByEmail(email)).thenReturn(mockAppointments);

        // Act
        ResponseEntity<List<Appointment>> response = appointmentController.getAppointmentsByEmail(email);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAppointments, response.getBody());
        verify(appointmentService).getAppointmentsByEmail(email);
    }

    @Test
    void getAppointmentsByEmailAndStatus_ShouldReturnFilteredAppointments() {
        // Arrange
        String email = "patient@example.com";
        AppointmentStatus status = AppointmentStatus.CONFIRMED;
        when(appointmentService.getAppointmentsByEmailAndStatus(email, status)).thenReturn(mockAppointments);

        // Act
        ResponseEntity<List<Appointment>> response = appointmentController.getAppointmentsByEmailAndStatus(email, status);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAppointments, response.getBody());
        verify(appointmentService).getAppointmentsByEmailAndStatus(email, status);
    }

    @Test
    void createAppointment_ShouldReturnCreatedAppointment() {
        // Arrange
        when(appointmentService.createAppointment(any(AppointmentRequestDTO.class))).thenReturn(mockAppointment);

        // Act
        ResponseEntity<Appointment> response = appointmentController.createAppointment(mockRequestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockAppointment, response.getBody());
        verify(appointmentService).createAppointment(mockRequestDTO);
    }

    @Test
    void cancelAppointment_ShouldReturnCancelledAppointment() {
        // Arrange
        String appointmentId = "1";
        Appointment cancelledAppointment = mockAppointment;
        cancelledAppointment.setStatus(AppointmentStatus.CANCELLED);
        
        when(appointmentService.cancelAppointment(appointmentId)).thenReturn(cancelledAppointment);

        // Act
        ResponseEntity<Appointment> response = appointmentController.cancelAppointment(appointmentId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cancelledAppointment, response.getBody());
        verify(appointmentService).cancelAppointment(appointmentId);
    }
}