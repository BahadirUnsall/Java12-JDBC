package com.bahadir.service;

import com.bahadir.repository.ProductRepository;
import com.bahadir.repository.entity.Product;

import java.util.Optional;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(){
        productRepository = new ProductRepository();
    }
    public void save(Product product) {
        productRepository.save(product);
    }

    public Optional<Product> buyProduct(int id,int amount) {
        Optional<Product> product = productRepository.findById((long) id);
        if (product.isPresent()){
            if (product.get().getStock() >= amount){
                ///product.get().setStock(product.get().getStock()-amount);
                return product;

            }else{
                System.out.println("Stok yetersiz!");
            }
        }else{
            System.out.println("Product bulunamadı!");
        }

        return Optional.empty();
    }

    public void update(Product product) {
        productRepository.update(product);
    }
}
