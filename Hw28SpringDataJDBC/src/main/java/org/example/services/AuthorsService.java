package org.example.services;

import org.example.entities.Author;
import org.example.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class AuthorsService {
    private final AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public Optional<Author> findById(@PathVariable Long id) {
        return authorsRepository.findById(id);
    }
}
