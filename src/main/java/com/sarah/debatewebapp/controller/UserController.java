/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.dao.DebateDao;
import com.sarah.debatewebapp.dao.UserDao;
import com.sarah.debatewebapp.dto.User;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * @author Sarah
 */

@Controller
public class UserController {
    private UserDao userDao;
    private DebateDao debDao;
    private PasswordEncoder encoder;
    
    @Inject
    public UserController(UserDao dao, DebateDao debDao, PasswordEncoder encoder){
        this.userDao = dao;
        this.debDao = debDao;
        this.encoder = encoder;
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
    
    //register user
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/user", method=RequestMethod.POST)
    public User registerUser(@Valid @RequestBody User user, Model model){
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            userDao.createUser(user);
        } catch (DuplicateKeyException e){
            boolean duplicate = true;
            model.addAttribute("duplicate", duplicate);
        }
        return user;
    }
    
//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value="/register", method=RequestMethod.POST)
//    public String registerUser(@Valid HttpServletRequest request){
//        
//        String first = request.getParameter("firstName");
//        String last = request.getParameter("lastName");
//        String email = request.getParameter("email");
//        String uname = request.getParameter("username");
//        String clearPw = request.getParameter("password");
//        String hashPw = encoder.encode(clearPw);
////        String message;
//        
//        User newUser = new User(uname, hashPw, first, last, email, "ROLE_USER");
//        userDao.createUser(newUser);
//        
////        if(first.equals("") || last.equals("") || email.equals("") || uname.equals("") || clearPw.equals("")) {
////            message = "Please fill out all feilds.";
////            request.setAttribute("message", message);
////            return "register";
////        } else {
////            try {
////                hashPw = encoder.encode(clearPw);
////                User newUser = new User(uname, hashPw, first, last, email, "ROLE_USER");
////                userDao.createUser(newUser);
////                return "redirect:/home";
////            } catch (DuplicateKeyException e){
////                message = "That username is taken.";
////                request.setAttribute("message", message);
////                return "register";
////            }
////        }
//        return "redirect:/home";
//        //if nothing else, create a validation method that redirects to a create user method on success
//    }
    
    //create moderator
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @RequestMapping(value="/mod", method=RequestMethod.POST)
    public User createModerator(@Valid @RequestBody User mod){
        mod.setPassword(encoder.encode(mod.getPassword()));
        mod.setRole("ROLE_ADMIN");
        User moderator = userDao.createUser(mod);
        
        return moderator;
    }
    
    @ResponseBody
    @RequestMapping(value="/user/{username}", method=RequestMethod.GET)
    public User getUserByUsername(@PathVariable String username){
        User user = userDao.getUserByUsername(username);
        return user;
    }
    
    //edit user info from user dash
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value="/user", method=RequestMethod.PUT)
    public void updateUserInfo(@Valid @RequestBody User user){
        User ogUser = userDao.getUserById(user.getId());
        user.setWins(ogUser.getWins());
        user.setLosses(ogUser.getLosses());
        user.setTies(ogUser.getTies());
        user.setRole(ogUser.getRole());
        user.setEnabled(ogUser.isEnabled());
        user.setPassword(ogUser.getPassword());
//        if (user.getPassword().equals(ogUser.getPassword())) user.setPassword(ogUser.getPassword());
//        else user.setPassword(encoder.encode(user.getPassword()));
        userDao.updateUser(user);
    }
    
    @RequestMapping(value="/profile/deleteUser", method=RequestMethod.GET)
    public String deleteUser(@RequestParam("userid") int userid, Model model) {
        User user = userDao.getUserById(userid);
        boolean badInput;
        try{
            userDao.deleteUser(userid);
            return "redirect:/home";
        } catch (DataIntegrityViolationException e){
            badInput = true;
            model.addAttribute("badInput", badInput);
            return "redirect:/profile/" + user.getUsername();
        }
        
    } 
    
    @RequestMapping(value="/profile/editRole", method=RequestMethod.GET)
    public String editRole(@RequestParam("userid") int userid) {
        User user = userDao.getUserById(userid);
        userDao.changeAuthority(user);
        return "redirect:/profile/" + user.getUsername();
    } 
    
    @RequestMapping(value={"/profile/disableUser", "/disableUser"}, method=RequestMethod.GET)
    public String disableUser(@RequestParam("username") String username) {
        User user = userDao.getUserByUsername(username);
        userDao.deactivateUser(user);
        return "redirect:/profile/" + username;
    } 
    
}
