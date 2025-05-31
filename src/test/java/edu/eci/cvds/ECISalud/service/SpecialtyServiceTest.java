package edu.eci.cvds.ECISalud.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import edu.eci.cvds.ECISalud.model.Specialty;
import edu.eci.cvds.ECISalud.repository.SpecialtyRepository;

@SpringBootTest
class SpecialtyServiceTest {

    @Mock
    private SpecialtyRepository specialtyRepository;
    
    @InjectMocks
    private SpecialtyService specialtyService;
    
    private Specialty testSpecialty;
    
    @BeforeEach
    void setUp() {
        // Setup test data
        testSpecialty = new Specialty();
        testSpecialty.setId("specialty-id");
        testSpecialty.setName("Medicina General");
        testSpecialty.setDescription("Servicio de medicina general");
        testSpecialty.setDoctor("Dr. Smith");
        testSpecialty.setLocation("Room 101");
        testSpecialty.setImageUrl("/images/medicina-general.jpg");
    }
    
    @Test
    void testGetAllSpecialties() {
        // Setup
        List<Specialty> specialties = new ArrayList<>();
        specialties.add(testSpecialty);
        when(specialtyRepository.findAll()).thenReturn(specialties);
        
        // Execute
        List<Specialty> result = specialtyService.getAllSpecialties();
        
        // Verify
        assertEquals(1, result.size());
        assertEquals("specialty-id", result.get(0).getId());
        verify(specialtyRepository, times(1)).findAll();
    }
    
    @Test
    void testGetSpecialtyById() {
        // Setup
        when(specialtyRepository.findById("specialty-id")).thenReturn(Optional.of(testSpecialty));
        
        // Execute
        Optional<Specialty> result = specialtyService.getSpecialtyById("specialty-id");
        
        // Verify
        assertTrue(result.isPresent());
        assertEquals("specialty-id", result.get().getId());
        assertEquals("Medicina General", result.get().getName());
        verify(specialtyRepository, times(1)).findById("specialty-id");
    }
    
    @Test
    void testGetSpecialtyById_NotFound() {
        // Setup
        when(specialtyRepository.findById("nonexistent-id")).thenReturn(Optional.empty());
        
        // Execute
        Optional<Specialty> result = specialtyService.getSpecialtyById("nonexistent-id");
        
        // Verify
        assertFalse(result.isPresent());
        verify(specialtyRepository, times(1)).findById("nonexistent-id");
    }
}
