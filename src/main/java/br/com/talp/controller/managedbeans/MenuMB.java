/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.talp.controller.managedbeans;

import br.ucb.talp.model.User;
import br.ucb.talp.model.enums.Role;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan
 */
@ManagedBean(name = "menu")
@SessionScoped
public class MenuMB implements Serializable {

    private static final long serialVersionUID = -4989977457937715156L;

    public Boolean isAdmin() {
        //Recupera o HttpSessionAtual para verificar qual é o Role do Usuário
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        User user = (User) session.getAttribute("user");
        return true;///user != null && user.getRole().equals(Role.ADMIN);
    }

}
