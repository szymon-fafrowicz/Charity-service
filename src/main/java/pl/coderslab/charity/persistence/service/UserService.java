package pl.coderslab.charity.persistence.service;

import pl.coderslab.charity.persistence.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    Optional<User> findById(long id);

    User findByEmail(String email);

    List<User> findAll();

    void registerNewUser(User user);

    void deleteById(long id);

}
