/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ucb.talp.model.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Jonathan
 */
public interface DAO<T> {
    
    public void save(T entity);
    public T update(T entity);
    public T find(Integer id);
    public T findOneResult(String namedQuery, Map<String,Object> parameters);
    public void delete(T entity);
    public List<T> findAll();
    
}
