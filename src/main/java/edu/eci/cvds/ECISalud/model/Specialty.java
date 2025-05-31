
package edu.eci.cvds.ECISalud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "specialties")
public class Specialty {
    
    @Id
    private String id;
    private String name;
    private String description;
    private String doctor;
    private String location;
    private String imageUrl;
    
    public Specialty() {
    }
    
    public Specialty(String id, String name, String description, String doctor, String location, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.doctor = doctor;
        this.location = location;
        this.imageUrl = imageUrl;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}