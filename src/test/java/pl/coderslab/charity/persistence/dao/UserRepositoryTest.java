package pl.coderslab.charity.persistence.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.persistence.entity.Role;
import pl.coderslab.charity.persistence.entity.User;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void shouldFindByEmail_returnUser() {
        //given
        String email = "test@gmail.com";
        User user1 = deliverUser(email);
        entityManager.persist(user1);
        //when
        User result = userRepository.findByEmail("test@gmail.com");
        //then
        assertEquals(email, result.getEmail());
        assertEquals(Long.valueOf(1), result.getId());
    }

    private User deliverUser(String email) {
        User user = new User();
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        user.setEmail(email);
        user.setFirstName("Jan");
        user.setLastName("Kowalski");
        user.setPassword("haslo");
        user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
        return user;
    }
}