package org.example.services;

import org.example.dtos.CreateOrUpdateCategoryDtoRq;
import org.example.entities.Category;
import org.example.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Category> findAll() {
        return categoriesRepository.findAll();
    }

    public Optional<Category> findByTitle(String title) {
        return categoriesRepository.findByTitle(title);
    }

    public Optional<Category> findById(Long id) {
        return categoriesRepository.findById(id);
    }

    public void deleteById(Long id) {
        categoriesRepository.deleteById(id);
    }

    public void createNewCategory(CreateOrUpdateCategoryDtoRq createOrUpdateCategoryDtoRq) {
        Category newCategory = new Category(createOrUpdateCategoryDtoRq.getId(), createOrUpdateCategoryDtoRq.getTitle());
        categoriesRepository.save(newCategory);
    }

    public void updateCategory(CreateOrUpdateCategoryDtoRq createOrUpdateCategoryDtoRq) {
        Category updatedCategory = new Category(createOrUpdateCategoryDtoRq.getId(), createOrUpdateCategoryDtoRq.getTitle());
        categoriesRepository.save(updatedCategory);
    }
}
