package com.bahadir.repository;

import com.bahadir.repository.entity.Product;
import com.bahadir.util.MyRepositoryFactory;

public class ProductRepository extends MyRepositoryFactory<Product,Long> {
    public ProductRepository() {
        super(Product.class);
    }

}
