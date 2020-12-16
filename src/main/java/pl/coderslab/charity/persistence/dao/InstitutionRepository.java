package pl.coderslab.charity.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.persistence.entity.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

}
