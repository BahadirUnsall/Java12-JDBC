package com.bahadir.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyRepositoryFactory<T,ID> implements ICrud<T,ID>{
    private Session session;
    private Transaction transaction;

    private CriteriaBuilder criteriaBuilder;

    Class<T> clazz;

    public MyRepositoryFactory(Class<T> clazz){
        this.clazz = clazz;
    }

    private void openSession() {
        session = HibernateUtility.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    private void closeSession() {
        transaction.commit();
        session.close();
    }


    @Override
    public T save(T entity) {
        openSession();
        session.save(entity);
        //session.persist(entity); --> JPA 'daki save
        closeSession();
        return entity;
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entities) {
        openSession();
        entities.forEach(entity ->{
            session.save(entity);
        });
        closeSession();
        return entities;
    }

    @Override
    public T update(T entity) {
        openSession();
        //session.update(entity);
        T guncellenen = (T)session.merge(entity); //JPA 'DAKİ update
        closeSession();
        return guncellenen;
    }

    @Override
    public void deleteById(ID id) {
        openSession();
        session.delete(session.get(clazz, (Serializable) id));
        closeSession();
    }

    @Override
    public void delete(T entity) {
        openSession();
        session.delete(entity);
        //session.remove();
        closeSession();
    }

    @Override
    public Optional<T> findById(ID id) {
        openSession();
        Optional<T> obj = Optional.ofNullable(session.get(clazz, (Serializable) id));
        closeSession();
        return obj;
    }

    @Override
    public boolean existsById(ID id) {
        return findById(id).isPresent();
    }

    @Override
    public List<T> findAll() {
        openSession();
        //session.createNativeQuery(); --> Bu metod içine direkt sql yazılabilir.
        List<T> resultlist = session.createQuery("FROM " + clazz.getSimpleName(), clazz).getResultList();
        closeSession();
        return resultlist;
    }

    /*
        Select * From tbl where columnName = value
     */
    @Override
    public <E> List<T> findByColumnNameAndValue(String columnName, E value) {
        openSession();
        List<T> resultList = session.createQuery("FROM " + clazz.getSimpleName() + " WHERE " + columnName + "=" + value, clazz).getResultList();
        closeSession();
        return resultList;
    }

    /*
        findByEntity metodu ile Burada yapılmak istenen bir sınıf içindeki alanların metod tarafından okunulması ve içlerindeki her bir alanın içindeki değerlerin kontrol
        edeceğiz. Onların içinden null olmayanları sorguya dahil etmesidir. Böylece esnek sorgulama sistemi kazandırılmaya çalışılacaktır.
        Bu işlemin adına REVERSE ENGINEERING denir.
     */


    @Override
    public List<T> findByEntity(T entity) {
        openSession();
        criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);

        Root<T> root = criteriaQuery.from(clazz);

        List<Predicate> predicates = new ArrayList<>(); // Sorguda kullancağımız kriterlerin listesi

        //Sınıf içindeki tüm fieldları dizi olarak döner.
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);//field'ı erişime açmak için
            try {
                Object value = field.get(entity);
                if (value!=null && !field.getName().equals("id")){
                    if (field.getType().isAssignableFrom(Integer.class)){
                        predicates.add(criteriaBuilder.equal(root.get(field.getName()),value));
                    }else if (field.getType().isAssignableFrom(String.class)){
                        predicates.add(criteriaBuilder.like(root.get(field.getName()), (Expression<String>) value));
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
        List<T> resultList = session.createQuery(criteriaQuery).getResultList();
        closeSession();

        //Yukarıdaki sınıfın içindeki fieldların adlarını ve null olmayanların tespit edilip değerlerini ekranda gösteriniz.
        return resultList;
    }
}
