package pl.coderslab.charity.persistence.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.persistence.entity.Role;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    RoleRepository roleRepository;

    @Test
    public void shouldFindByName_returnRole() {
        //given
        Role role = new Role();
        role.setName("ROLE_USER");
//        entityManager.persist(role);
        //when
        Role result = roleRepository.findByName("ROLE_USER");
        List<Role> all = roleRepository.findAll();
        //then
        assertEquals(result.getName(), role.getName());
    }
}