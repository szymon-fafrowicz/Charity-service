package pl.coderslab.charity.persistence.service;

import pl.coderslab.charity.persistence.entity.Category;

import java.util.Optional;


public interface CategoryService {
    public Optional<Category> findById(long id);

    public void save(Category category);
}
