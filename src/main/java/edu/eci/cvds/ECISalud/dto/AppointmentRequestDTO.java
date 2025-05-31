
package edu.eci.cvds.ECISalud.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AppointmentRequestDTO {
    @NotBlank(message = "Patient name is required")
    private String patientName;
    
    @NotBlank(message = "Patient ID is required")
    private String patientId;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotNull(message = "Date is required")
    private LocalDate date;
    
    @NotBlank(message = "Specialty is required")
    private String specialtyId;
    
    public AppointmentRequestDTO() {
    }
    
    public AppointmentRequestDTO(String patientName, String patientId, String email, LocalDate date, String specialtyId) {
        this.patientName = patientName;
        this.patientId = patientId;
        this.email = email;
        this.date = date;
        this.specialtyId = specialtyId;
    }
    
    public String getPatientName() {
        return patientName;
    }
    
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    
    public String getPatientId() {
        return patientId;
    }
    
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public String getSpecialtyId() {
        return specialtyId;
    }
    
    public void setSpecialtyId(String specialtyId) {
        this.specialtyId = specialtyId;
    }
}