package org.example.repositories;

import org.example.entities.Category;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CategoriesRepository extends ListCrudRepository<Category, Long> {

    Optional<Category> findByTitle(String title);
}