
package edu.eci.cvds.ECISalud.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "appointments")
public class Appointment {
    @Id
    private String id;
    private String patientName;
    private String patientId;
    private String email;
    private LocalDate date;
    private String specialty;
    private String doctor;
    private String location;
    private AppointmentStatus status;

    public enum AppointmentStatus {
        PENDING,
        CONFIRMED,
        CANCELLED
    }
    
    public Appointment() {
    }
    
    public Appointment(String id, String patientName, String patientId, String email, LocalDate date, 
                      String specialty, String doctor, String location, AppointmentStatus status) {
        this.id = id;
        this.patientName = patientName;
        this.patientId = patientId;
        this.email = email;
        this.date = date;
        this.specialty = specialty;
        this.doctor = doctor;
        this.location = location;
        this.status = status;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
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
    
    public String getSpecialty() {
        return specialty;
    }
    
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    
    public String getDoctor() {
        return doctor;
    }
    
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public AppointmentStatus getStatus() {
        return status;
    }
    
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}