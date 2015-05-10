/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ucb.talp.model;

import br.ucb.talp.model.enums.Role;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Jonathan
 */
@Entity
@Table (name = "users")
@SecondaryTable (name = "roles")
@NamedQueries ({
    @NamedQuery (name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery (name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email =:email")
})
public class User implements Serializable {
    
    private static final long serialVersionUID = -2409572720346001824L;
    
    public static final String USER_FIND_ALL = "User.findAll";
    public static final String USER_FIND_BY_EMAIL = "User.findByEmail";

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column (nullable = false)
    private String name;
    
    @Column (length = 100, nullable = false, unique = false)
    private String email;
    
    @Column (length = 130, nullable = false, updatable = true)
    private String password;
    
    @Enumerated (EnumType.STRING)
    @Column (table = "roles", name = "role")
    private Role role;
    
    public User() {}

    public User(String name, String email, String password, Role role) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setRole(role);
    }
    
    /* Getters and Setters */
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.sha512Hex(password);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + getId() + ", name=" + getName() + ", email=" + getEmail() + ", password=" + getPassword() + ", role=" + getRole().getRole() + '}';
    }
}
