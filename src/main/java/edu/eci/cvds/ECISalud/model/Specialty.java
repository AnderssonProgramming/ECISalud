
package edu.eci.cvds.ECISalud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "specialties")
public class Specialty {
    
    @Id
    private String id;
    private String name;
    private String description;
    private String doctor;
    private String location;
    private String imageUrl;
}