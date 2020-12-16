package pl.coderslab.charity.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.persistence.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {

}
