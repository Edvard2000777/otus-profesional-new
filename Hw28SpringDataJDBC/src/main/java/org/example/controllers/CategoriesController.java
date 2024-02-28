package org.example.controllers;

import org.example.dtos.CreateOrUpdateCategoryDtoRq;
import org.example.entities.Category;
import org.example.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public List<Category> findAll() {
        return categoriesService.findAll();
    }

    @GetMapping("/bytitle")
    public Category findByTitle(@RequestParam String title) {
        return categoriesService.findByTitle(title).get();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id) {
        return categoriesService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoriesService.deleteById(id);
    }

    @PostMapping
    public void createNewCategory(@RequestBody CreateOrUpdateCategoryDtoRq createOrUpdateCategoryDtoRq) {
        categoriesService.createNewCategory(createOrUpdateCategoryDtoRq);
    }

    @PutMapping
    public void updateCategory(@RequestBody CreateOrUpdateCategoryDtoRq createOrUpdateCategoryDtoRq) {
        categoriesService.updateCategory(createOrUpdateCategoryDtoRq);
    }
}
