package com.bahadir.repository;

import com.bahadir.repository.entity.Category;
import com.bahadir.util.MyRepositoryFactory;

public class CategoryRepository extends MyRepositoryFactory<Category,Long> {
    public CategoryRepository() {
        super(Category.class);
    }
}
