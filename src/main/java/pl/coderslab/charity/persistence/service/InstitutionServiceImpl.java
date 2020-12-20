package pl.coderslab.charity.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.persistence.dao.InstitutionRepository;
import pl.coderslab.charity.persistence.entity.Institution;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InstitutionServiceImpl implements InstitutionService {
    InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public Optional<Institution> findById(long id) {
        return institutionRepository.findById(id);
    }

    @Override
    public List<Institution> findAll() {
        List<Institution> all = institutionRepository.findAll();
        return all;
    }

    @Override
    public void save(Institution institution) {
        institutionRepository.save(institution);
    }
}
