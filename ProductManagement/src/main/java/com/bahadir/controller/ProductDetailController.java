package com.bahadir.controller;

import com.bahadir.repository.entity.Product;
import com.bahadir.repository.entity.ProductDetail;
import com.bahadir.service.ProductDetailService;
import com.bahadir.service.ProductService;
import com.bahadir.util.BAUtils;

import java.util.List;

public class ProductDetailController {
    private final ProductDetailService productDetailService;
    private final ProductService productService;

    public ProductDetailController() {
        this.productDetailService = new ProductDetailService();
        this.productService = new ProductService();
    }

    public void commentAndRateTheProduct() {
        int id = BAUtils.readInt("Yorum yapmak istediginiz urunun id'sini girin.");
        Product product = productService.findById(id);
        if (product == null){
            throw new RuntimeException("Urun bulunamadi!");
        }
        String comment = BAUtils.readString("Yorumunuz: ");
        double score = BAUtils.readDouble("Puaniniz: ");

        ProductDetail productDetail = ProductDetail.builder()
                .product(product)
                .comment(comment)
                .score(score)
                .build();
        productDetailService.save(productDetail);


    }

}
