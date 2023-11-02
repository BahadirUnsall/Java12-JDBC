package com.bahadir.service;

import com.bahadir.repository.CustomerRepository;
import com.bahadir.repository.entity.Customer;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    public void register(Customer customer){
        customerRepository.save(customer);
    }
}

