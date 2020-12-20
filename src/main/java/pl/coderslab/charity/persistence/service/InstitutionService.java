package pl.coderslab.charity.persistence.service;

import pl.coderslab.charity.persistence.entity.Institution;

import java.util.List;
import java.util.Optional;



public interface InstitutionService {
    Optional<Institution> findById(long id);

    List<Institution> findAll();

    void save(Institution institution);
}
