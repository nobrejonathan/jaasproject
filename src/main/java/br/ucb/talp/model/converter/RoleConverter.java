/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ucb.talp.model.converter;

import br.ucb.talp.model.enums.Role;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Jonathan
 */
@FacesConverter (value = "RoleConverter",forClass = Role.class)
public class RoleConverter extends EnumConverter {

    public RoleConverter() {
        super(Role.class);
    }
}
