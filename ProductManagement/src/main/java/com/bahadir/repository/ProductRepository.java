package com.bahadir.repository;

import com.bahadir.repository.entity.Product;
import com.bahadir.util.HibernateUtility;
import com.bahadir.util.MyRepositoryFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ProductRepository extends MyRepositoryFactory<Product,Long> {
    Session session;
    public ProductRepository() {
        super(Product.class);
        session = HibernateUtility.getSessionFactory().openSession();
    }

    public List<Product> listProductsWhereStockLessThanTen() {
        String sql = "SELECT * FROM tbl_product WHERE stock < 10;";
        NativeQuery<Product> nativeQuery = session.createNativeQuery(sql, Product.class);
        List<Product> resultList = nativeQuery.getResultList();
        return resultList;
    }
}
