package com.bahadir.service;

import com.bahadir.enums.EAdressType;
import com.bahadir.repository.entity.Adress;
import com.bahadir.repository.entity.Information;
import com.bahadir.repository.entity.User;
import com.bahadir.util.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    public void save(){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<String> interests = Arrays.asList("Book", "Comics");
        Map<EAdressType, Adress> address1 = new HashMap<>();
        address1.put(EAdressType.HOME,Adress.builder()
                .city("İstanbul")
                .country("Türkiye")
                .street("Balkan cad.")
                .build());

        User user = User.builder()
                .information(Information.builder()
                        .name("Bahadır")
                        .surname("Ünsal")
                        .build())
                .adresses(address1)
                .password("1234")
                .username("bahadirUnsal")
                .interest(interests)
                .build();
        session.save(user);

        transaction.commit();
        session.close();
    }
}
