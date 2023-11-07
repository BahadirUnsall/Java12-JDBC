package com.bahadir.app;

import com.bahadir.controller.CategoryController;
import com.bahadir.controller.CustomerController;
import com.bahadir.controller.ProductController;
import com.bahadir.controller.ProductDetailController;
import com.bahadir.repository.entity.Customer;
import com.bahadir.util.BAUtils;

import java.util.HashMap;
import java.util.Optional;

public class Menu {
    private final CustomerController customerController;
    private final CategoryController categoryController;
    private final ProductController productController;
    private final ProductDetailController productDetailController;

    public Menu() {
        this.customerController = new CustomerController();
        this.categoryController = new CategoryController();
        this.productController = new ProductController();
        this.productDetailController = new ProductDetailController();
    }

    public void menu(){
        HashMap<Integer,String> menuItems = new HashMap<>();
        menuItems.put(1,"Admin");
        menuItems.put(2,"Customer");

        int key = BAUtils.menu(menuItems);

        switch (key){
            case 1:
                adminMenu();
                break;
            case 2:
                customerMenu();
                break;
        }
    }


    private void customerMenu() {
        HashMap<Integer,String> menuItems = new HashMap<>();
        menuItems.put(1,"Kayıt ol");
        menuItems.put(2,"Giriş yap");

        int key = BAUtils.menu(menuItems);

        switch (key){
            case 1:
                customerController.register();
                break;
            case 2:
                Optional<Customer> login = customerController.login();
                if (login.isPresent()){
                    customerManagerMenu(login.get());
                }
                break;
        }
    }

    private void customerManagerMenu(Customer customer) {
        HashMap<Integer,String> menuItems = new HashMap<>();
        menuItems.put(1,"Satın Al");
        menuItems.put(2,"Urune Yorum yap ve puan ver");
        menuItems.put(3,"Stogu bitmek üzere olan urunleri listele(10 ve adet ve asagısı)");
        menuItems.put(4,"Urune gore yorumları goster");


        int key = BAUtils.menu(menuItems);

        switch (key){
            case 1:
                productController.buyProduct(customer);
                break;
            case 2:
                productDetailController.commentAndRateTheProduct();
                break;
            case 3:
                productController.listProductsWhereStockLessThanTen();
                break;
        }
    }

    private void adminMenu() {
        HashMap<Integer,String> menuItems = new HashMap<>();
        menuItems.put(1,"Product ekle");
        menuItems.put(2,"Category ekle");


        int key = BAUtils.menu(menuItems);

        switch (key){
            case 1:
                productController.save();
                break;
            case 2:
                categoryController.save();
                break;

        }
    }
}
