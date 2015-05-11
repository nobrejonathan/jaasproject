/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ucb.talp.controller.util;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author Jonathan
 */
public class SHAGenerator {
    
    public static String sha512 (String password, String salt) {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(512);
        encoder.setIterations(1024);
        return encoder.encodePassword(password, salt);
    }
    
}
