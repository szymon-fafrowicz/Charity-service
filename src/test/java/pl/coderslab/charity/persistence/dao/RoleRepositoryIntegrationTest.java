package pl.coderslab.charity.persistence.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.persistence.entity.Role;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest

public class RoleRepositoryIntegrationTest {

    @Autowired
   private TestEntityManager entityManager;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void shouldFindByName_returnRoleUser() {
        //given
        Role role = new Role();
        role.setName("ROLE_USER");
        entityManager.persist(role);
        //when
        Role result = roleRepository.findByName("ROLE_USER");
        //then
        assertEquals(result, role);
    }
}