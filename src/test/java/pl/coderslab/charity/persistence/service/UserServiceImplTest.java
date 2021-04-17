package pl.coderslab.charity.persistence.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.persistence.dao.RoleRepository;
import pl.coderslab.charity.persistence.dao.UserRepository;
import pl.coderslab.charity.persistence.entity.Role;
import pl.coderslab.charity.persistence.entity.User;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceImplTest {

    @MockBean
    UserRepository userRepository;
    @MockBean
    RoleRepository roleRepository;
    @MockBean
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImpl userService;


    @Test
    public void whenSearchingByEmail_ThenReturnObject() {
        //given
        User user1 = new User();
        user1.setEmail("test@gmail.com");
        user1.setFirstName("Jan");
        user1.setLastName("Kowalski");
        user1.setPassword("haslo");
        when(userRepository.findByEmail("test@gmail.com")).thenReturn(user1);
        //when
        User result = userService.findByEmail("test@gmail.com");
        //then
        assertEquals(user1.getEmail(), result.getEmail());
    }

    @Test
    public void whenSaveUser_ThenIsSaved() {
        //given
        User user1 = new User();
        user1.setEmail("test@gmail.com");
        user1.setFirstName("Jan");
        user1.setLastName("Kowalski");
        user1.setPassword("haslo");
        Role role = new Role();
        role.setName("ROLE_USER");
        user1.setRoles(new HashSet<Role>(Arrays.asList(role)));
        //when
        userService.registerNewUser(user1);
        //then
        assertNotEquals("haslo", user1.getPassword());
    }
}