
package edu.eci.cvds.ECISalud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.eci.cvds.ECISalud.model.Specialty;

@Repository
public interface SpecialtyRepository extends MongoRepository<Specialty, String> {
}