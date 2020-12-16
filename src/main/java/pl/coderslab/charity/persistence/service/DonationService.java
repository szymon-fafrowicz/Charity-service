package pl.coderslab.charity.persistence.service;

import pl.coderslab.charity.persistence.entity.Donation;

import java.util.Optional;


public interface DonationService {
    Optional<Donation> findById(long id);

    void save(Donation donation);

    int sumOfReceivedBags();

    Object sumOfGifts();
}
