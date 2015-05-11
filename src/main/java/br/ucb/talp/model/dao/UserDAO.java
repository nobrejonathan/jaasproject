/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ucb.talp.model.dao;

import br.ucb.talp.model.beans.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jonathan
 */

@Stateless
@LocalBean
public class UserDAO implements DAO<User> {
    
    @PersistenceContext (unitName = "JAASProjectPU")
    private EntityManager entityManager;

    @Override
    public void save(User entity) {
        entity.setEnabled(true);
        getEntityManager().persist(entity);
    }

    @Override
    public User update(User entity) {
        return getEntityManager(). merge(entity);
    }

    @Override
    public User find(Integer id) {
        return getEntityManager().find(User.class, id);
    }
    
    public User findUserByEmail(String email) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        return findOneResult(User.USER_FIND_BY_EMAIL, params);
    }

    @Override
    public User findOneResult(String namedQuery, Map<String, Object> parameters) {
        Query query = getEntityManager().createNamedQuery(namedQuery);
        
        for (Map.Entry<String,Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return (User) query.getSingleResult();
    }

    @Override
    public void delete(User entity) {
        User toDeleteUser = update(entity);
        getEntityManager().remove(toDeleteUser);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = getEntityManager().createNamedQuery(User.USER_FIND_ALL, User.class);
        return query.getResultList();
    }
    
    /* Getters and Setters */

    private EntityManager getEntityManager() {
        return entityManager;
    }
}
