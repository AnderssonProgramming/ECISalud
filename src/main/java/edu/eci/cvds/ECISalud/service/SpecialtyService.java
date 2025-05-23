
package edu.eci.cvds.ECISalud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.cvds.ECISalud.model.Specialty;
import edu.eci.cvds.ECISalud.repository.SpecialtyRepository;

@Service
public class SpecialtyService {
    
    @Autowired
    private SpecialtyRepository specialtyRepository;
    
    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }
    
    public Optional<Specialty> getSpecialtyById(String id) {
        return specialtyRepository.findById(id);
    }
}