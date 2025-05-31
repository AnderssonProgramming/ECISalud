package edu.eci.cvds.ECISalud.controller;

import edu.eci.cvds.ECISalud.model.Specialty;
import edu.eci.cvds.ECISalud.service.SpecialtyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;






@WebMvcTest(SpecialtyController.class)
public class SpecialtyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecialtyService specialtyService;

    @Test
    public void testGetAllSpecialties() throws Exception {
        // Setup mock specialty objects
        Specialty cardiology = new Specialty();
        cardiology.setId("1");
        cardiology.setName("Cardiology");

        Specialty neurology = new Specialty();
        neurology.setId("2");
        neurology.setName("Neurology");

        List<Specialty> specialties = Arrays.asList(cardiology, neurology);
        
        when(specialtyService.getAllSpecialties()).thenReturn(specialties);

        // Execute and verify
        mockMvc.perform(get("/api/specialties")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].name", is("Cardiology")))
                .andExpect(jsonPath("$[1].id", is("2")))
                .andExpect(jsonPath("$[1].name", is("Neurology")));
        
        verify(specialtyService, times(1)).getAllSpecialties();
    }

    @Test
    public void testGetSpecialtyById_Found() throws Exception {
        // Setup mock specialty
        String id = "1";
        Specialty specialty = new Specialty();
        specialty.setId(id);
        specialty.setName("Cardiology");
        
        when(specialtyService.getSpecialtyById(id)).thenReturn(Optional.of(specialty));

        // Execute and verify
        mockMvc.perform(get("/api/specialties/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.name", is("Cardiology")));
        
        verify(specialtyService, times(1)).getSpecialtyById(id);
    }

    @Test
    public void testGetSpecialtyById_NotFound() throws Exception {
        // Setup
        String id = "999";
        when(specialtyService.getSpecialtyById(id)).thenReturn(Optional.empty());

        // Execute and verify
        mockMvc.perform(get("/api/specialties/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        
        verify(specialtyService, times(1)).getSpecialtyById(id);
    }
}