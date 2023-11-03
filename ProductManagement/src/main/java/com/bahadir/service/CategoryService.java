package com.bahadir.service;

import com.bahadir.repository.CategoryRepository;
import com.bahadir.repository.entity.Category;

import java.util.Optional;

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService() {
        this.categoryRepository = new CategoryRepository();
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }
    public Optional<Category> findById(int id){
        return categoryRepository.findById((long) id);
    }
}
