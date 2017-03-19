/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.dao.UserDao;
import java.security.Principal;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * @author Sarah
 */

@Controller
public class UserController {
    UserDao dao;
    String currentUser;
    
    @Inject
    public UserController(UserDao dao){
        this.dao = dao;
    }
    
    @RequestMapping(value={"/profile"}, method = RequestMethod.GET)
    public String displayProfile(Model model, Principal principal){
        currentUser = principal.getName(); //get user by username meth
//        model.addAttribute()
        return "profile";
    }
}
