package com.bahadir.repository;

import com.bahadir.repository.entity.Product;
import com.bahadir.repository.entity.ProductDetail;
import com.bahadir.util.HibernateUtility;
import com.bahadir.util.MyRepositoryFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ProductDetailRepository extends MyRepositoryFactory<ProductDetail,Long> {
    Session session;
    public ProductDetailRepository() {
        super(ProductDetail.class);
        session = HibernateUtility.getSessionFactory().openSession();
    }

}
