package com.bahadir.repository;

import com.bahadir.repository.entity.Customer;
import com.bahadir.util.MyRepositoryFactory;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class CustomerRepository extends MyRepositoryFactory<Customer,Long> {
    public CustomerRepository() {
        super(Customer.class);
    }

    public Optional<Customer> findCustomerByIdentity(String identity) {
        openSession();
        CriteriaQuery<Customer> query = getCriteriaBuilder().createQuery(Customer.class);
        Root<Customer> root = query.from(Customer.class);
        query.select(root).where(getCriteriaBuilder().equal(root.get("identity"),identity));

        List<Customer> resultList = getSession().createQuery(query).getResultList();

        closeSession();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));

    }
}
