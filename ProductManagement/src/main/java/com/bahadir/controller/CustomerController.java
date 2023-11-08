package com.bahadir.controller;

import com.bahadir.repository.entity.Customer;
import com.bahadir.repository.entity.Information;
import com.bahadir.service.CustomerService;
import com.bahadir.util.BAUtils;

import java.util.Optional;

public class CustomerController {
    private CustomerService customerService;

    public CustomerController() {
        this.customerService = new CustomerService();
    }

    public void register(){
        String firstname = BAUtils.readString("İsminiz: ");
        String lastname = BAUtils.readString("Soy İsminiz: ");
        String email = BAUtils.readString("Email: ");

        Information information = Information.builder()
                .first_name(firstname)
                .last_name(lastname)
                .email(email)
                .build();

        String password = BAUtils.readString("Sifreniz: ");
        String identityNo = BAUtils.readString("Tcniz: ");

        Customer customer = Customer.builder()
                .identity(identityNo)
                .password(password)
                .information(information)
                .build();

        customerService.register(customer);
    }

    public Optional<Customer> login() {
        String identity = BAUtils.readString("tcnizi girin");
        String password = BAUtils.readString("şifrenizi girin");

       return customerService.findCustomerByIdentity(identity,password);

    }

    public void upgradeAccount(Customer customer) {
        System.out.println("1-GOLD");
        System.out.println("2-PLATINUM");
        String value = BAUtils.readString("Hangi uyelige geciceksiniz ?");
        customerService.upgradeAccount(customer,value);

    }
}
