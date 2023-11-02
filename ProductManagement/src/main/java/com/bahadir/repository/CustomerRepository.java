package com.bahadir.repository;

import com.bahadir.repository.entity.Customer;
import com.bahadir.util.MyRepositoryFactory;

public class CustomerRepository extends MyRepositoryFactory<Customer,Long> {
    public CustomerRepository() {
        super(Customer.class);
    }
}
