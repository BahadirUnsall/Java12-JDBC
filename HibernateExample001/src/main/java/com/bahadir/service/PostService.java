package com.bahadir.service;

import com.bahadir.repository.entity.Post;
import com.bahadir.util.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class PostService {

    public void createPost(){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Post post = Post.builder()
                .content("Harika bir gün")
                .date(new Date())
                .userId(1L)
                .build();

        session.save(post);

        transaction.commit();
        session.close();
    }
}
