package pl.coderslab.charity.persistence.service;

import pl.coderslab.charity.persistence.entity.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryService {
    Optional<Category> findById(long id);

    void save(Category category);

    List<Category> findAll();
}
