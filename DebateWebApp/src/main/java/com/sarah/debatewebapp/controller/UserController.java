/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.dao.UserDao;
import com.sarah.debatewebapp.dto.User;
import java.security.Principal;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    //returns profile page from user dropdown menu
    @RequestMapping(value={"/profile"}, method = RequestMethod.GET)
    public String displayProfile(Model model, Principal principal){
        currentUser = principal.getName(); 
        User user = dao.getUserByUsername(currentUser);
        model.addAttribute("oneUser", user);
        return "profile";
    }
    
    //gets user, puts in model, returns profile page
    @RequestMapping(value="/singleUser/{username}", method=RequestMethod.GET)
    public String getUserByUsername(@PathVariable String user, Model model){
        User aUser = dao.getUserByUsername(user);
        model.addAttribute("oneUser", aUser);
        return "profile";
    }
}
