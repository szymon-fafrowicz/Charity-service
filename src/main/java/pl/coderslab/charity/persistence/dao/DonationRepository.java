package pl.coderslab.charity.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.persistence.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM (d.quantity) FROM Donation d")
    Integer sumOfReceivedBags();

    @Query("SELECT COUNT (d) FROM Donation d")
    Integer sumOfDonations();
}
