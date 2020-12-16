package pl.coderslab.charity.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.persistence.dao.DonationRepository;
import pl.coderslab.charity.persistence.entity.Donation;

import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {
    DonationRepository donationRepository;

    @Autowired
    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public Optional<Donation> findById(long id) {
        return donationRepository.findById(id);
    }

    @Override
    public void save(Donation donation) {
        donationRepository.delete(donation);
    }
}
