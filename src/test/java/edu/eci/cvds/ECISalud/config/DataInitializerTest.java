package edu.eci.cvds.ECISalud.config;

import edu.eci.cvds.ECISalud.model.Specialty;
import edu.eci.cvds.ECISalud.repository.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;






class DataInitializerTest {

    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private DataInitializer dataInitializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateSpecialtiesWhenDatabaseIsEmpty() throws Exception {
        // Arrange
        when(specialtyRepository.count()).thenReturn(0L);

        // Act
        dataInitializer.run(new String[]{});

        // Assert
        ArgumentCaptor<Specialty> specialtyCaptor = ArgumentCaptor.forClass(Specialty.class);
        verify(specialtyRepository, times(4)).save(specialtyCaptor.capture());
        
        List<Specialty> capturedSpecialties = specialtyCaptor.getAllValues();
        assertEquals(4, capturedSpecialties.size());
        
        // Verify each specialty properties
        verifySpecialty(capturedSpecialties, "Medicina General", 
            "Consulta de medicina general para diagnóstico y tratamiento de enfermedades comunes.",
            "Dr. Carlos Rodríguez", 
            "Consultorio 101, Edificio Principal", 
            "/images/medicina-general.jpg");
            
        verifySpecialty(capturedSpecialties, "Psicología", 
            "Servicio de atención psicológica para evaluación, diagnóstico y tratamiento de problemas de salud mental.",
            "Dra. Ana Martínez", 
            "Consultorio 205, Edificio Anexo", 
            "/images/psicologia.jpg");
            
        verifySpecialty(capturedSpecialties, "Ortopedia", 
            "Especialidad médica dedicada al diagnóstico, tratamiento, rehabilitación y prevención de lesiones del sistema musculoesquelético.",
            "Dr. Javier Pérez", 
            "Consultorio 310, Edificio Principal", 
            "/images/ortopedia.jpg");
            
        verifySpecialty(capturedSpecialties, "Odontología", 
            "Especialidad médica dedicada al diagnóstico, tratamiento y prevención de enfermedades del aparato estomatognático.",
            "Dra. Laura Gómez", 
            "Consultorio 102, Edificio Principal", 
            "/images/odontologia.jpg");
    }

    @Test
    void shouldNotCreateSpecialtiesWhenDatabaseHasData() throws Exception {
        // Arrange
        when(specialtyRepository.count()).thenReturn(4L);

        // Act
        dataInitializer.run(new String[]{});

        // Assert
        verify(specialtyRepository, never()).save(any(Specialty.class));
    }
    
    private void verifySpecialty(List<Specialty> specialties, String name, String description, 
                                String doctor, String location, String imageUrl) {
        Specialty specialty = specialties.stream()
                .filter(s -> name.equals(s.getName()))
                .findFirst()
                .orElseThrow(() -> new AssertionError(name + " specialty not found"));
                
        assertEquals(description, specialty.getDescription());
        assertEquals(doctor, specialty.getDoctor());
        assertEquals(location, specialty.getLocation());
        assertEquals(imageUrl, specialty.getImageUrl());
    }
}