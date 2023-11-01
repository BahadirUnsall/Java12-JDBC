package com.bahadir.service;

import com.bahadir.enums.EAdressType;
import com.bahadir.repository.entity.Adress;
import com.bahadir.repository.entity.Information;
import com.bahadir.repository.entity.User;
import com.bahadir.util.HibernateUtility;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    Session session;
    Transaction transaction;



    private void openSession() {
        session = HibernateUtility.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    private void closeSession() {
        transaction.commit();
        session.close();
    }
    public static void save(){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Information information = Information.builder()
                .surname("kaya")
                .middleName("ali")
                .name("mehmet")
                .build();

        List<String> interests = Arrays.asList("Book", "Comics");

        Map<EAdressType, Adress> adres1 = new HashMap<>();
        adres1.put(EAdressType.HOME, Adress.builder()
                .country("Türkiye")
                .city("Ankara")
                .street("Nazilli")
                .build());

        User user = User.builder()
                .password("123456")
                .username("memo")
                .information(information)
                .interest(interests)
                .adresses(adres1)
                .build();
        //------------------
        Information information2 = Information.builder()
                .surname("kış")
                .middleName("osman")
                .name("hakan")
                .build();

        List<String> interests2 = Arrays.asList("PC", "Book");

        Map<EAdressType, Adress> adres2 = new HashMap<>();
        adres2.put(EAdressType.HOME, Adress.builder()
                .country("Amerika")
                .city("LA")
                .street("XStreet")
                .build());

        User user2 = User.builder()
                .password("123456")
                .username("ali")
                .information(information2)
                .interest(interests2)
                .adresses(adres2)
                .build();
        //------------------
        Information information3 = Information.builder()
                .surname("yaz")
                .middleName("mahmut")
                .name("serkan")
                .build();

        List<String> interests3 = Arrays.asList("Software", "Dergi");

        Map<EAdressType, Adress> adres3 = new HashMap<>();
        adres3.put(EAdressType.HOME, Adress.builder()
                .country("İspanya")
                .city("Madrid")
                .street("YStreet")
                .build());

        User user3 = User.builder()
                .password("123456")
                .username("serko")
                .information(information3)
                .interest(interests3)
                .adresses(adres3)
                .build();
        session.save(user);
        session.save(user2);
        session.save(user3);

        transaction.commit();
        session.close();
    }
    public List<User> findAll(){
        openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

        Root<User> root = query.from(User.class);

        query.select(root);

        List<User> resultList = session.createQuery(query).getResultList();
        for (User user : resultList) {
            Hibernate.initialize(user.getAdresses());
            Hibernate.initialize(user.getInterest());
        }

        closeSession();
        return resultList;
    }
    public List<User> findAllHQL(){
        openSession();

        List<User> fromUser = session.createQuery("FROM " + User.class.getSimpleName(),User.class).getResultList();

        closeSession();
        return fromUser;
    }
    public List<User> findAllNativeQuery(){
        openSession();

        String sql = "select * from tblusers";
        List<User> resultList = session.createNativeQuery(sql, User.class).getResultList();

        closeSession();
        return resultList;
    }
    public List<Information> findAllUserInformation(){
        openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Information> query = criteriaBuilder.createQuery(Information.class);
        Root<User> root = query.from(User.class);
        query.select(root.get("information"));
        List<Information> resultList = session.createQuery(query).getResultList();
        closeSession();
        return resultList;
    }
    public List<String> findAllUserInformationName(String hakan){
        openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<User> root = query.from(User.class);
        query.select(root.get("information").get("name"));
        List<String> resultList = session.createQuery(query).getResultList();
        closeSession();
        return resultList;
    }
    public User findById(Long gelenId){
        openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(criteriaBuilder.equal(root.get("id"), gelenId));
        User singleResult = session.createQuery(query).getSingleResult();

        return singleResult;
    }
    public User findByUserName(String username){
        openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(criteriaBuilder.equal(root.get("username"), username));
        User singleResult = session.createQuery(query).getSingleResult();

        return singleResult;
    }
    public List<User> findByUserWithValue(String value){
        openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(criteriaBuilder.like(root.get("information").get("name"), value + "%"));
        List<User> resultList = session.createQuery(query).getResultList();

        return resultList;
    }


}
