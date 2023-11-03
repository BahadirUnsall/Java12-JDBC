package com.bahadir.controller;

import com.bahadir.repository.entity.Category;
import com.bahadir.service.CategoryService;
import com.bahadir.util.BAUtils;

import java.util.Optional;

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController() {
        this.categoryService = new CategoryService();
    }

    public void save() {
        String categoryName = BAUtils.readString("Category name: ");
        Category category = Category.builder()
                        .name(categoryName)
                                .build();
        categoryService.save(category);

    }

    public Optional<Category> findById(long categoryId) {
        return categoryService.findById((int) categoryId);
    }
}
