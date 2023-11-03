package com.bahadir.app;

import com.bahadir.controller.CategoryController;
import com.bahadir.controller.CustomerController;
import com.bahadir.controller.ProductController;
import com.bahadir.repository.entity.Customer;
import com.bahadir.util.BAUtils;

import java.util.HashMap;

public class Menu {
    private final CustomerController customerController;
    private final CategoryController categoryController;
    private final ProductController productController;

    public Menu() {
        this.customerController = new CustomerController();
        this.categoryController = new CategoryController();
        this.productController = new ProductController();
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
                customerController.login();
                break;
        }
    }

    private void customerManagerMenu(Customer customer) {
        HashMap<Integer,String> menuItems = new HashMap<>();
        menuItems.put(1,"Satın Al");


        int key = BAUtils.menu(menuItems);

        switch (key){
            case 1:
                productController.buyProduct(customer);
                break;
            case 2:
                System.out.println("Giriş yap");
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
