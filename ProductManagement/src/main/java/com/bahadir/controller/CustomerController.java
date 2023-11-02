package com.bahadir.controller;

import com.bahadir.repository.entity.Customer;
import com.bahadir.repository.entity.Information;
import com.bahadir.service.CustomerService;
import com.bahadir.util.BAUtils;

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
}
