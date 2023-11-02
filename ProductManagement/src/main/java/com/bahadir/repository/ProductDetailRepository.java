package com.bahadir.repository;

import com.bahadir.repository.entity.ProductDetail;
import com.bahadir.util.MyRepositoryFactory;

public class ProductDetailRepository extends MyRepositoryFactory<ProductDetail,Long> {
    public ProductDetailRepository() {
        super(ProductDetail.class);
    }
}
