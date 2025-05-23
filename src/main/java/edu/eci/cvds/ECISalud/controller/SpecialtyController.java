
package edu.eci.cvds.ECISalud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.cvds.ECISalud.model.Specialty;
import edu.eci.cvds.ECISalud.service.SpecialtyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/specialties")
@CrossOrigin(origins = "*")
@Tag(name = "Specialty Controller", description = "API for managing medical specialties")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;
    
    @GetMapping
    @Operation(summary = "Get all specialties", description = "Returns a list of all available medical specialties")
    public ResponseEntity<List<Specialty>> getAllSpecialties() {
        List<Specialty> specialties = specialtyService.getAllSpecialties();
        return new ResponseEntity<>(specialties, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get specialty by ID", description = "Returns a specialty by its ID")
    public ResponseEntity<Specialty> getSpecialtyById(@PathVariable String id) {
        Optional<Specialty> specialty = specialtyService.getSpecialtyById(id);
        if (specialty.isPresent()) {
            return new ResponseEntity<>(specialty.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}