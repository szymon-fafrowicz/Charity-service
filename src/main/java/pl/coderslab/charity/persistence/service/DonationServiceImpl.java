package pl.coderslab.charity.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.persistence.dao.DonationRepository;
import pl.coderslab.charity.persistence.entity.Donation;

import java.util.Optional;

@Service
@Transactional
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
        donationRepository.save(donation);
    }

    @Override
    public int sumOfReceivedBags() {
        return donationRepository.sumOfReceivedBags() !=null ? donationRepository.sumOfReceivedBags() : 0;
    }

    @Override
    public int sumOfDonations() {
        return donationRepository.sumOfDonations() !=null ? donationRepository.sumOfDonations() : 0;
    }

}
