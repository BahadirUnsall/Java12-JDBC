package com.bahadir.service;

import com.bahadir.repository.CustomerRepository;
import com.bahadir.repository.entity.Customer;

import java.util.Optional;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    public void register(Customer customer){
        customerRepository.save(customer);
    }

    public Optional<Customer> findCustomerByIdentity(String identity,String password) {
        Optional<Customer> customer = customerRepository.findCustomerByIdentity(identity);
        if (customer.isPresent()){
            if (customer.get().getPassword().equals(password)){
                System.out.println(customer.get().getInformation().getFirst_name() + " Hoşgeldiniz..");
                return customer;
            }else {
                System.out.println("Password is not valid!");
            }
        }else{
            System.out.println("Customer Not Found");
        }
        return Optional.empty();
    }
}

