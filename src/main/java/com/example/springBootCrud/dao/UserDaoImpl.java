package com.example.springBootCrud.dao;

import com.example.springBootCrud.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> index() {
        return entityManager.createQuery("SELECT user from User user").getResultList();
    }

    @Override
    public void create(User user) {
        System.out.println(user);
        System.out.println(user.getAuthorities());
        entityManager.persist(user);
    }

    @Override
    public User show(long id) {
        return (User) entityManager.createQuery("SELECT user from User user where user.id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(long id) {
        entityManager.createQuery(
                        "DELETE FROM User user WHERE user.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public User getUserByName(String name) {
        return (User) entityManager.createQuery("SELECT user from User user where user.name=:name")
                .setParameter("name", name)
                .getSingleResult();
    }
}

