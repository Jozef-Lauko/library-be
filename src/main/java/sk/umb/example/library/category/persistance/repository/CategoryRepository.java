package sk.umb.example.library.category.persistance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.example.library.category.persistance.entity.CategoryEntity;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findAll(String category);

    Iterable<CategoryEntity> findByCategory(String category);
}
