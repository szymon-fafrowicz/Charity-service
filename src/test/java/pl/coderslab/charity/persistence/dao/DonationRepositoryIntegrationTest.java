package pl.coderslab.charity.persistence.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.persistence.entity.Category;
import pl.coderslab.charity.persistence.entity.Donation;
import pl.coderslab.charity.persistence.entity.Institution;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class DonationRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DonationRepository donationRepository;


    @Test
    public void shouldSumAllBags_returnZero() {
        //given
        Integer sum = null;
        //when
        Integer result = donationRepository.sumOfReceivedBags();
        //then
        assertEquals(sum, result);
    }

    @Test
    public void shouldSumDonations_returnZero() {
        //given
        Integer sum = 0;
        //when
        Integer result = donationRepository.sumOfDonations();
        //then
        assertEquals(sum, result);
    }


    @Test
    public void shouldSumAllBags_returnSum() {
        //given
        Donation don1 = deliverDonation(10);
        Donation don2 = deliverDonation(15);
        entityManager.persist(don1);
        entityManager.persist(don2);

        Integer sum = 25;
        //when
        Integer result = donationRepository.sumOfReceivedBags();
        //then
        assertEquals(sum, result);
    }

    @Test
    public void shouldSumDonations_returnSum() {
        //given
        Donation don1 = deliverDonation(10);
        Donation don2 = deliverDonation(15);
        entityManager.persist(don1);
        entityManager.persist(don2);

        Integer sum = 2;
        //when
        Integer result = donationRepository.sumOfDonations();
        //then
        assertEquals(sum, result);
    }

    private Donation deliverDonation(Integer quantity) {
        Institution institution = new Institution();
        institution.setName("Testowa");
        institution.setDescription("Test");
        entityManager.persist(institution);

        Category category = new Category();
        category.setName("Testowa");
        entityManager.persist(category);

        Donation don = new Donation();
        don.setQuantity(quantity);
        don.setCategories(Arrays.asList(category));
        don.setInstitution(institution);
        don.setPhoneNumber("123456789");
        don.setCity("Test City");
        don.setPickUpDate(LocalDate.parse("2021-12-30"));
        don.setPickUpTime(LocalTime.parse("13:00"));
        don.setStreet("Test");
        don.setZipCode("1234");

        return don;
    }
}