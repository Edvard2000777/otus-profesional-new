package org.example.repositories;

import org.example.entities.Author;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorsRepository extends ListCrudRepository<Author, Long> {
}