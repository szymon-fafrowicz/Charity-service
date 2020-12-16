package pl.coderslab.charity.persistence.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.persistence.entity.Donation;

import java.util.Optional;

@Service
@Transactional
public interface DonationService {
    public Optional<Donation> findById(long id);

    public void save(Donation donation);
}
