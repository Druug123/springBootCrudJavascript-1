package com.example.springBootCrud.dao;

import com.example.springBootCrud.model.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT role from Role role").getResultList();
    }

    @Override
    public void saveRole(Role role){
        entityManager.persist(role);
    }
}
