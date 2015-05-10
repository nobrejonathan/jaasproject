/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ucb.talp.controller.managedbeans;

import br.ucb.talp.model.User;
import br.ucb.talp.model.dao.UserDAO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jonathan
 */
@ManagedBean (name = "userMB")
@ViewScoped
public class UserMB implements Serializable {
    private static final long serialVersionUID = -3850410812737331744L;
    
    @EJB
    private UserDAO userDAO;
    
    public List<User> getAllUsers() {
        return getUserDAO().findAll();
    }

    private UserDAO getUserDAO() {
        return userDAO;
    }
}
