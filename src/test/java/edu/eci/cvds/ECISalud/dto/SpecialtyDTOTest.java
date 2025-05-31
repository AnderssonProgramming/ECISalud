package edu.eci.cvds.ECISalud.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpecialtyDTOTest {

    @Test
    public void testNoArgsConstructor() {
        SpecialtyDTO specialty = new SpecialtyDTO();
        assertNotNull(specialty);
        assertNull(specialty.getId());
        assertNull(specialty.getName());
        assertNull(specialty.getDescription());
        assertNull(specialty.getDoctor());
        assertNull(specialty.getLocation());
        assertNull(specialty.getImageUrl());
    }
    
    @Test
    public void testAllArgsConstructor() {
        SpecialtyDTO specialty = new SpecialtyDTO();
        specialty.setId("1");
        specialty.setName("Cardiology");
        specialty.setDescription("Heart related treatments");
        specialty.setDoctor("Dr. Smith");
        specialty.setLocation("Building A, Floor 3");
        specialty.setImageUrl("cardio.jpg");
        
        assertEquals("1", specialty.getId());
        assertEquals("Cardiology", specialty.getName());
        assertEquals("Heart related treatments", specialty.getDescription());
        assertEquals("Dr. Smith", specialty.getDoctor());
        assertEquals("Building A, Floor 3", specialty.getLocation());
        assertEquals("cardio.jpg", specialty.getImageUrl());
    }

    @Test
    public void testGettersAndSetters() {
        SpecialtyDTO specialty = new SpecialtyDTO();
        
        specialty.setId("2");
        specialty.setName("Neurology");
        specialty.setDescription("Brain treatments");
        specialty.setDoctor("Dr. Johnson");
        specialty.setLocation("Building B, Floor 2");
        specialty.setImageUrl("neuro.jpg");
        
        assertEquals("2", specialty.getId());
        assertEquals("Neurology", specialty.getName());
        assertEquals("Brain treatments", specialty.getDescription());
        assertEquals("Dr. Johnson", specialty.getDoctor());
        assertEquals("Building B, Floor 2", specialty.getLocation());
        assertEquals("neuro.jpg", specialty.getImageUrl());
    }
    
    @Test
    public void testEqualsAndHashCode() {
        SpecialtyDTO specialty1 = new SpecialtyDTO();
        specialty1.setId("3");
        specialty1.setName("Pediatrics");
        specialty1.setDescription("Children care");
        specialty1.setDoctor("Dr. Brown");
        specialty1.setLocation("Building C, Floor 1");
        specialty1.setImageUrl("peds.jpg");
        
        SpecialtyDTO specialty2 = new SpecialtyDTO();
        specialty2.setId("3");
        specialty2.setName("Pediatrics");
        specialty2.setDescription("Children care");
        specialty2.setDoctor("Dr. Brown");
        specialty2.setLocation("Building C, Floor 1");
        specialty2.setImageUrl("peds.jpg");
        
        SpecialtyDTO specialty3 = new SpecialtyDTO();
        specialty3.setId("4");
        specialty3.setName("Dentistry");
        specialty3.setDescription("Dental care");
        specialty3.setDoctor("Dr. White");
        specialty3.setLocation("Building D, Floor 2");
        specialty3.setImageUrl("dental.jpg");
        
        assertEquals(specialty1, specialty2);
        assertEquals(specialty1.hashCode(), specialty2.hashCode());
        assertNotEquals(specialty1, specialty3);
        assertNotEquals(specialty1.hashCode(), specialty3.hashCode());
    }

    @Test
    public void testToString() {
        SpecialtyDTO specialty = new SpecialtyDTO();
        specialty.setId("5");
        specialty.setName("Dermatology");
        specialty.setDescription("Skin treatments");
        specialty.setDoctor("Dr. Green");
        specialty.setLocation("Building E, Floor 4");
        specialty.setImageUrl("derm.jpg");
        
        String toStringResult = specialty.toString();
        
        assertTrue(toStringResult.contains("id='5'"));
        assertTrue(toStringResult.contains("name='" + specialty.getName() + "'"));
        assertTrue(toStringResult.contains("description='" + specialty.getDescription() + "'"));
        assertTrue(toStringResult.contains("doctor='" + specialty.getDoctor() + "'"));
        assertTrue(toStringResult.contains("location='" + specialty.getLocation() + "'"));
        assertTrue(toStringResult.contains("imageUrl='" + specialty.getImageUrl() + "'"));
    }
}