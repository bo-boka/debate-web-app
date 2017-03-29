/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.dao.DebateDao;
import com.sarah.debatewebapp.dao.UserDao;
import com.sarah.debatewebapp.dto.User;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerUser(@Valid HttpServletRequest request){
        
        String first = request.getParameter("firstName");
        String last = request.getParameter("lastName");
        String email = request.getParameter("email");
        String uname = request.getParameter("username");
        String hashPw = encoder.encode(request.getParameter("password"));
//        String message;
        
        User newUser = new User(uname, hashPw, first, last, email, "ROLE_USER");
        userDao.createUser(newUser);
        
//        if(first.equals("") || last.equals("") || email.equals("") || uname.equals("") || pw.equals("")) {
//            message = "Please fill out all feilds.";
//            request.setAttribute("message", message);
//            return "register";
//        } else {
//            try {
//                User newUser = new User(uname, pw, first, last, email, "ROLE_USER");
//                userDao.createUser(newUser);
//                return "redirect:/home";
//            } catch (DuplicateKeyException e){
//                message = "That username is taken.";
//                request.setAttribute("message", message);
//                return "register";
//            }
//        }
        return "redirect:/home";
    }
    
    //create moderator
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @RequestMapping(value="/mod", method=RequestMethod.POST)
    public User createModerator(@Valid @RequestBody User mod){
        
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
        if (user.getWins() == 0) user.setWins(ogUser.getWins());
        if (user.getLosses() == 0) user.setLosses(ogUser.getLosses());
        if (user.getTies() == 0) user.setTies(ogUser.getTies());
        if (user.getPassword().equals("")) user.setPassword(ogUser.getPassword());
        if (user.getRole() == null) user.setRole(ogUser.getRole());
        userDao.updateUser(user);
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
