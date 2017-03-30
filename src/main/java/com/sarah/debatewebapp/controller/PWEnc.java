/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * @author Sarah
 */

public class PWEnc {
    
    public static void main(String[] args) {
        String clearTxtPw = "stinkybuttskdf";
        //BCrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPw = encoder.encode(clearTxtPw);
        System.out.println(hashedPw);
    }
}
