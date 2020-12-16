package pl.coderslab.charity.persistence.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.persistence.entity.Category;

import java.util.Optional;

@Service
@Transactional
public interface CategoryService {
    public Optional<Category> findById(long id);

    public void save(Category category);
}
