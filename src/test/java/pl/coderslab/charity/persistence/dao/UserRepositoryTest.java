package pl.coderslab.charity.persistence.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.persistence.entity.Role;
import pl.coderslab.charity.persistence.entity.User;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    UserRepository userRepository;


    @Test
    public void shouldFindByEmail_returnUser() {
        //given
        User user1 = new User();
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");

        user1.setEmail("test@gmail.com");
        user1.setFirstName("Jan");
        user1.setLastName("Kowalski");
        user1.setPassword("haslo");
        user1.setRoles(new HashSet<>(Arrays.asList(roleUser)));
        entityManager.persist(user1);
        //when
        User result = userRepository.findByEmail("test@gmail.com");
        //then
        assertEquals(user1.getEmail(), result.getEmail());
    }

//    public void clearData() {
//        role
//    }
}