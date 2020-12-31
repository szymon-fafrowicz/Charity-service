package pl.coderslab.charity.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.persistence.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByEmail(String email);

}
