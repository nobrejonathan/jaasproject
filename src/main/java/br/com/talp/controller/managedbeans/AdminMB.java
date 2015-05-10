/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.talp.controller.managedbeans;

import br.ucb.talp.model.User;
import br.ucb.talp.model.dao.UserDAO;
import br.ucb.talp.model.enums.Role;
import br.ucb.talp.model.lazy.LazyUserDataModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Jonathan
 */
@ManagedBean(name = "adminMB")
@ViewScoped
public class AdminMB implements Serializable {
    private static final long serialVersionUID = -2370885269797580176L;

    @EJB
    private UserDAO dao;

    private User user;

    private User selectedUser;
    
    private LazyDataModel<User> lazyUser;
    
    @PostConstruct
    public void init() {
        setUser(new User());
        setSelectedUser(new User());
        
        refreshData();

        // Adiciona usuários iniciais ao banco, caso não tenha nenhum.
        if (getDao().findAll().isEmpty()) {
            createInitialUsers();
        }
    }
    
    private void refreshData() {
        setLazyUser(new LazyUserDataModel(getDao().findAll()));
    }

    public String saveUser() {
        getDao().save(getUser());
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Salvando: " + getUser().toString());
        setUser(new User());
        refreshData();
        return null;
    }
    
    public void updateUser() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Atualizando: " + getSelectedUser().toString());
        getDao().update(getSelectedUser());
    }
    
    public void delete(User user) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Deletando: " + user.toString());
        getDao().delete(user);
        refreshData();
    }

    public List<Role> getRolesEnum() {
        List<Role> roles = new ArrayList<>();
        roles.addAll(Arrays.asList(Role.values()));
        return roles;
    }

    /*Getters and Setters */
    
    public UserDAO getDao() {
        return dao;
    }

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public LazyDataModel<User> getLazyUser() {
        return lazyUser;
    }

    public void setLazyUser(LazyDataModel<User> lazyUser) {
        this.lazyUser = lazyUser;
    }
    
    private void createInitialUsers() {
        User admin = new User("Jonathan Nobre", "nobre.jonathan@catolica.edu.br", "iamgroot", Role.ADMIN);
        User user = new User("Jeysel Martins", "jeyselmartins@gmail.com", "masterofjava", Role.USER);
        
        getDao().save(admin);
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Criando usuário: " + admin.toString());
        getDao().save(user);
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Criando usuário: " + user.toString());
    }
}
