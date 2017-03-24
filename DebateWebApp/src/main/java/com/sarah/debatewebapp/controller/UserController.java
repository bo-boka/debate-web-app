/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.dao.DebateDao;
import com.sarah.debatewebapp.dao.UserDao;
import com.sarah.debatewebapp.dto.User;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
    UserDao userDao;
    DebateDao debDao;
    String currentUser;
    
    @Inject
    public UserController(UserDao dao, DebateDao debDao){
        this.userDao = dao;
        this.debDao = debDao;
    }
    
    //gets and displays user profile
    @RequestMapping(value = "/profile/{user}", method = RequestMethod.GET)
    public String displayDebatesByCategory(@PathVariable String user, Model model){
        User aUser = userDao.getUserByUsername(user);
        model.addAttribute("oneUser", aUser);
        return "profile";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String displaySignUpPage(){
        return "register";
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String doPost(HttpServletRequest request){
        
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User newUser = new User(username, password, firstName, lastName, email, "ROLE_USER");
        userDao.createUser(newUser);
        return "redirect:/home";
    }
    
//    //returns profile page from user dropdown menu
//    @RequestMapping(value={"/profile"}, method = RequestMethod.GET)
//    public String displayProfile(Model model, Principal principal){
//        currentUser = principal.getName(); 
//        User user = userDao.getUserByUsername(currentUser);
//        model.addAttribute("oneUser", user);
//        return "profile";
//    }
//    
//    //gets user from listed debates on homepage, puts in model, returns profile page
//    @RequestMapping(value="/singleUser/{id}", method=RequestMethod.GET)
//    public String getUserByUsername(@PathVariable("id")int id, Model model){
//        Debate deb = debDao.getDebateById(id);
//        String user = deb.getAffirmativeUser();
//        User aUser = userDao.getUserByUsername(user);
//        model.addAttribute("oneUser", aUser);
//        return "profile";
//    }
//    
//    //gets user from listed users links on homepage, puts in model, returns profile page
//    @RequestMapping(value="/profile/{id}", method=RequestMethod.GET)
//    public String getUserById(@PathVariable("id")int id, Model model){
//        User aUser = userDao.getUserById(id);
//        model.addAttribute("oneUser", aUser);
//        return "profile";
//    }
//    
//    @ResponseBody
//    @RequestMapping(value="/users", method=RequestMethod.GET)
//    public List<User> getAllUsers(){
//        return userDao.getAllUsers();
//    }
}
