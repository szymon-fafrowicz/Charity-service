package pl.coderslab.charity.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.persistence.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByEmail(String email);

}
