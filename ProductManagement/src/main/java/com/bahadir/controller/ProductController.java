package com.bahadir.controller;

import com.bahadir.repository.CategoryRepository;
import com.bahadir.repository.entity.Category;
import com.bahadir.repository.entity.Customer;
import com.bahadir.repository.entity.Product;
import com.bahadir.service.CategoryService;
import com.bahadir.service.ProductService;
import com.bahadir.util.BAUtils;

import java.util.List;
import java.util.Optional;

public class ProductController {
    private final ProductService productService;
    private final CategoryController categoryController;

    public ProductController(){
        productService = new ProductService();
        categoryController = new CategoryController();
    }

    public void save(){
        String productname = BAUtils.readString("Product name girin");
        Double price = BAUtils.readDouble("Price girin");
        int stock = BAUtils.readInt("Stok girin");
        long categoryId = BAUtils.readInt("Kategori belirle");
        Optional<Category> category = categoryController.findById(categoryId);

        Product product = Product.builder()
                .price(price)
                .name(productname)
                .stock(stock)
                .category(category.get())
                .build();
        productService.save(product);
    }


    public void buyProduct(Customer customer) {
        int id = BAUtils.readInt("Almak istediğiniz ürün id'sini girin");
        int amount = BAUtils.readInt("Kaç adet almak istiyosunuz");

        Optional<Product> product = productService.buyProduct(id,amount);

        product.get().getCustomerList().add(customer);
        product.get().setStock(product.get().getStock()-amount);

        productService.update(product.get());

    }

    public void listProductsWhereStockLessThanTen() {
        List<Product> products = productService.listProductsWhereStockLessThanTen();
        System.out.println("Stogu 10'dan az olan urunler ");
        products.forEach(System.out::println);
    }
}
