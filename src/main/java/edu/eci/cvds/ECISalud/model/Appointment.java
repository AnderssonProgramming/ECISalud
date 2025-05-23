
package edu.eci.cvds.ECISalud.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "appointments")
public class Appointment{
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
}