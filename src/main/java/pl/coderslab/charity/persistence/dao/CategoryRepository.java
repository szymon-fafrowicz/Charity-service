package pl.coderslab.charity.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.persistence.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
