package com.bahadir.service;

import com.bahadir.repository.ProductDetailRepository;
import com.bahadir.repository.entity.Product;
import com.bahadir.repository.entity.ProductDetail;

import java.util.List;

public class ProductDetailService {
    private final ProductDetailRepository productDetailRepository;

    public ProductDetailService() {
        this.productDetailRepository = new ProductDetailRepository();
    }

    public void save(ProductDetail productDetail) {
        productDetailRepository.save(productDetail);
    }

}
