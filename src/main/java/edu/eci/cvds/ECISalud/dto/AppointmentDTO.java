
package edu.eci.cvds.ECISalud.dto;

import java.time.LocalDate;

import edu.eci.cvds.ECISalud.model.Appointment.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private String id;
    private String patientName;
    private String patientId;
    private String email;
    private LocalDate date;
    private String specialty;
    private String doctor;
    private String location;
    private AppointmentStatus status;
}