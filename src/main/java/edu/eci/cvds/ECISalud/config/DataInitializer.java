package edu.eci.cvds.ECISalud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import edu.eci.cvds.ECISalud.model.Specialty;
import edu.eci.cvds.ECISalud.repository.SpecialtyRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if we already have specialties
        if (specialtyRepository.count() == 0) {
            // Create the 4 required specialties
            
            Specialty generalMedicine = new Specialty();
            generalMedicine.setName("Medicina General");
            generalMedicine.setDescription("Consulta de medicina general para diagnóstico y tratamiento de enfermedades comunes.");
            generalMedicine.setDoctor("Dr. Carlos Rodríguez");
            generalMedicine.setLocation("Consultorio 101, Edificio Principal");
            generalMedicine.setImageUrl("/images/medicina-general.jpg");
            specialtyRepository.save(generalMedicine);
            
            Specialty psychology = new Specialty();
            psychology.setName("Psicología");
            psychology.setDescription("Servicio de atención psicológica para evaluación, diagnóstico y tratamiento de problemas de salud mental.");
            psychology.setDoctor("Dra. Ana Martínez");
            psychology.setLocation("Consultorio 205, Edificio Anexo");
            psychology.setImageUrl("/images/psicologia.jpg");
            specialtyRepository.save(psychology);
            
            Specialty orthopedics = new Specialty();
            orthopedics.setName("Ortopedia");
            orthopedics.setDescription("Especialidad médica dedicada al diagnóstico, tratamiento, rehabilitación y prevención de lesiones del sistema musculoesquelético.");
            orthopedics.setDoctor("Dr. Javier Pérez");
            orthopedics.setLocation("Consultorio 310, Edificio Principal");
            orthopedics.setImageUrl("/images/ortopedia.jpg");
            specialtyRepository.save(orthopedics);
            
            Specialty dentistry = new Specialty();
            dentistry.setName("Odontología");
            dentistry.setDescription("Especialidad médica dedicada al diagnóstico, tratamiento y prevención de enfermedades del aparato estomatognático.");
            dentistry.setDoctor("Dra. Laura Gómez");
            dentistry.setLocation("Consultorio 102, Edificio Principal");
            dentistry.setImageUrl("/images/odontologia.jpg");
            specialtyRepository.save(dentistry);
        }
    }
}
