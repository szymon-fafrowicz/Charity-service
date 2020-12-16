package pl.coderslab.charity.persistence.service;

import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.persistence.entity.Institution;

import java.util.List;
import java.util.Optional;


@Transactional
public interface InstitutionService {
    public Optional<Institution> findById(long id);

    public List<Institution> findAll();

    public void save(Institution institution);
}
