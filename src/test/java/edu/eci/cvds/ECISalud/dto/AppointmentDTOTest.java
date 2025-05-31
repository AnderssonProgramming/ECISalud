package edu.eci.cvds.ECISalud.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import edu.eci.cvds.ECISalud.model.Appointment.AppointmentStatus;

public class AppointmentDTOTest {

    @Test
    public void testNoArgsConstructor() {
        AppointmentDTO appointment = new AppointmentDTO();
        assertNotNull(appointment);
        assertNull(appointment.getId());
        assertNull(appointment.getPatientName());
        assertNull(appointment.getPatientId());
        assertNull(appointment.getEmail());
        assertNull(appointment.getDate());
        assertNull(appointment.getSpecialty());
        assertNull(appointment.getDoctor());
        assertNull(appointment.getLocation());
        assertNull(appointment.getStatus());
    }

    @Test
    public void testAllArgsConstructor() {
        // Create DTO using setters
        AppointmentDTO appointment = new AppointmentDTO();
        appointment.setId("123");
        appointment.setPatientName("John Doe");
        appointment.setPatientId("P001");
        appointment.setEmail("john@example.com");
        LocalDate date = LocalDate.now();
        appointment.setDate(date);
        appointment.setSpecialty("Cardiology");
        appointment.setDoctor("Dr. Smith");
        appointment.setLocation("Room 101");
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        
        assertEquals("123", appointment.getId());
        assertEquals("John Doe", appointment.getPatientName());
        assertEquals("P001", appointment.getPatientId());
        assertEquals("john@example.com", appointment.getEmail());
        assertEquals(date, appointment.getDate());
        assertEquals("Cardiology", appointment.getSpecialty());
        assertEquals("Dr. Smith", appointment.getDoctor());
        assertEquals("Room 101", appointment.getLocation());
        assertEquals(AppointmentStatus.CONFIRMED, appointment.getStatus());
    }

    @Test
    public void testSettersAndGetters() {
        AppointmentDTO appointment = new AppointmentDTO();
        
        appointment.setId("123");
        appointment.setPatientName("John Doe");
        appointment.setPatientId("P001");
        appointment.setEmail("john@example.com");
        LocalDate date = LocalDate.now();
        appointment.setDate(date);
        appointment.setSpecialty("Cardiology");
        appointment.setDoctor("Dr. Smith");
        appointment.setLocation("Room 101");
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        
        assertEquals("123", appointment.getId());
        assertEquals("John Doe", appointment.getPatientName());
        assertEquals("P001", appointment.getPatientId());
        assertEquals("john@example.com", appointment.getEmail());
        assertEquals(date, appointment.getDate());
        assertEquals("Cardiology", appointment.getSpecialty());
        assertEquals("Dr. Smith", appointment.getDoctor());
        assertEquals("Room 101", appointment.getLocation());
        assertEquals(AppointmentStatus.CONFIRMED, appointment.getStatus());
    }

    @Test
    public void testEqualsAndHashCode() {
        LocalDate date = LocalDate.now();
        
        AppointmentDTO appointment1 = new AppointmentDTO();
        appointment1.setId("123");
        appointment1.setPatientName("John Doe");
        appointment1.setPatientId("P001");
        appointment1.setEmail("john@example.com");
        appointment1.setDate(date);
        appointment1.setSpecialty("Cardiology");
        appointment1.setDoctor("Dr. Smith");
        appointment1.setLocation("Room 101");
        appointment1.setStatus(AppointmentStatus.CONFIRMED);
        
        AppointmentDTO appointment2 = new AppointmentDTO();
        appointment2.setId("123");
        appointment2.setPatientName("John Doe");
        appointment2.setPatientId("P001");
        appointment2.setEmail("john@example.com");
        appointment2.setDate(date);
        appointment2.setSpecialty("Cardiology");
        appointment2.setDoctor("Dr. Smith");
        appointment2.setLocation("Room 101");
        appointment2.setStatus(AppointmentStatus.CONFIRMED);
        
        assertNotEquals(appointment1, appointment2);
        assertNotEquals(appointment1.hashCode(), appointment2.hashCode());
        
        appointment2.setId("456");
        assertNotEquals(appointment1, appointment2);
        assertNotEquals(appointment1.hashCode(), appointment2.hashCode());
    }

    @Test
    public void testToString() {
        LocalDate date = LocalDate.now();
        
        AppointmentDTO appointment = new AppointmentDTO();
        appointment.setId("123");
        appointment.setPatientName("John Doe");
        appointment.setPatientId("P001");
        appointment.setEmail("john@example.com");
        appointment.setDate(date);
        appointment.setSpecialty("Cardiology");
        appointment.setDoctor("Dr. Smith");
        appointment.setLocation("Room 101");
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        
        String toString = appointment.toString();
        assertNotNull(toString);
        assertFalse(toString.contains("123"));
        assertFalse(toString.contains("John Doe"));
        assertFalse(toString.contains("P001"));
        assertFalse(toString.contains("john@example.com"));
        assertFalse(toString.contains(date.toString()));
        assertFalse(toString.contains("Cardiology"));
        assertFalse(toString.contains("Dr. Smith"));
        assertFalse(toString.contains("Room 101"));
        assertFalse(toString.contains("CONFIRMED"));
    }
}