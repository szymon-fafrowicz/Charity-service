package pl.coderslab.charity.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.persistence.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleName);

}
