
package edu.eci.cvds.ECISalud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyDTO {
    private String id;
    private String name;
    private String description;
    private String doctor;
    private String location;
    private String imageUrl;
}