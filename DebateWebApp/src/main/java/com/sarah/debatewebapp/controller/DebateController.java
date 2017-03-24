/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.dao.DebateDao;
import com.sarah.debatewebapp.dao.UserDao;
import com.sarah.debatewebapp.dto.Debate;
import com.sarah.debatewebapp.dto.Rebuttal;
import com.sarah.debatewebapp.dto.User;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * @user Sarah
 */

@Controller
public class DebateController {
    
    UserDao userDao;
    DebateDao dao;
    Debate aDebate;
    String currentUser;
    
    @Inject
    public DebateController(DebateDao dao, UserDao userDao){
        this.dao = dao;
        this.userDao = userDao;
    }
    
    //display page methods
    
    @RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
    public String displayHome(Model model){
        List<String> categories = dao.getAllCategories();
        model.addAttribute("categories", categories);
        
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);
        
//        List<Debate> debs = dao.getAllPublishedDebates();
//        model.addAttribute("debates", debs);
        return "home";
    }
    
    //gets username with dashboard
    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
    public String displayDash(Model model, Principal principal){      
//        List<String> categories = dao.getAllCategories();
//        model.addAttribute("categories", categories);
        currentUser = principal.getName();
        return "dashboard";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String displayLogin(Model model){
        return "login";
    }
    
    //gets a pub debate, puts it in the model, and returns single page
    @RequestMapping(value="/debate/{id}", method=RequestMethod.GET)
    public String getSingleDebate(@PathVariable int id, Model model){
        
        aDebate = dao.getPublishedDebateById(id);
        model.addAttribute("oneDebate", aDebate);
        
        List<String> categories = dao.getAllCategories();
        model.addAttribute("categories", categories);
        
        return "single";
    } 
    
    //goes to list of debs by category from home page
    @RequestMapping(value = "/categories/{category}", method = RequestMethod.GET)
    public String displayDebatesByCategory(@PathVariable String category, Model model){
        List<Debate> result = dao.searchDebatesByCategory(category);
        model.addAttribute("debates", result);
        return "search";
    }
    
    
    //create challenge rebuttal & update debate meths rely on aDebate variable changed in getSingleDebate method above
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/debate/challenge", method=RequestMethod.POST)
    //add @Valid as parameter
    public Rebuttal createChallenge(@RequestBody Rebuttal rebuttal){
        rebuttal.setDebateId(aDebate.getId());
        rebuttal.setUser(currentUser);
        aDebate.setNegativeUser(currentUser);
        aDebate.setStatus("live");
        dao.createRebuttal(rebuttal);
        dao.updateDebate(aDebate);
        return rebuttal;
    } 
    
    //create reply rebuttals
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/debate/rebuttal", method=RequestMethod.POST)
    //add @Valid as parameter
    public Rebuttal createRebuttal(@RequestBody Rebuttal rebuttal){
        rebuttal.setDebateId(aDebate.getId());
        rebuttal.setUser(currentUser);
        rebuttal.setPosition(currentUser.equals(aDebate.getAffirmativeUser()));
        dao.createRebuttal(rebuttal);
        return rebuttal;
    }
    
    //create debate from user dash
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/debate", method=RequestMethod.POST)
    //add @Valid as parameter when adding validation
    public Debate createDebate(@RequestBody Debate debate){
        debate.setAffirmativeUser(currentUser);
        dao.createIntroDebate(debate);
        return debate;
    }
    
    //get all pub debates for home page
    @ResponseBody
    @RequestMapping(value="/debates", method=RequestMethod.GET)
    public List<Debate> getAllPublishedDebates(){
        return dao.getAllPublishedDebates();
    }
    
    //get all pub debates for user dash
    @ResponseBody
    @RequestMapping(value="/userDebates", method=RequestMethod.GET)
    public List<Debate> getAllUserDebates(){
        List<Debate> allDs = dao.getAllPublishedDebates();
        List<Debate> userDs = new ArrayList<>();
        for(Debate d : allDs){
            if (d.getAffirmativeUser().equals(currentUser)){
                userDs.add(d);
            } else if (d.getNegativeUser() != null){
                if (d.getNegativeUser().equals(currentUser)){
                    userDs.add(d);
                }
            }
        }
        return userDs;
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value="/debate/votePro", method=RequestMethod.PUT)
    public void updateDebateProVotes(){
        aDebate.setProVotes(aDebate.getProVotes() + 1);
        dao.updateDebate(aDebate);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value="/debate/voteCon", method=RequestMethod.PUT)
    public void updateDebateConVotes(){
        aDebate.setConVotes(aDebate.getConVotes() + 1);
        dao.updateDebate(aDebate);
    }
    
    //gets a debate for edit modal
    @ResponseBody
    @RequestMapping(value="/debate/deb/{id}", method=RequestMethod.GET)
    public Debate getDebateById(@PathVariable int id){
        return dao.getDebateById(id);
    }
    
    //edit debate button
    //add @Valid twice
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value="/debate/deb/{id}", method=RequestMethod.PUT)
    public void updateDebate(@PathVariable int id, @RequestBody Debate deb){
        dao.updateDebate(deb);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value="/debate/deb/{id}", method=RequestMethod.DELETE)
    public void deleteDebate(@PathVariable int id) {
        dao.deleteDebate(id);
    }
    
//    //search by resolution
//    @ResponseBody
//    @RequestMapping(value = "/searchRes/{res}", method = RequestMethod.GET)
//    public List<Debate> displayDebatesByResolution(@PathVariable String res){
//        List<Debate> result = dao.searchDebatesByResolution(res);
//        return result;
//    }
//    
//    //search by category
//    @ResponseBody
//    @RequestMapping(value = "/searchCategory/{category}", method = RequestMethod.GET)
//    public List<Debate> displayDebatesByCategory(@PathVariable String category){
//        List<Debate> result = dao.searchDebatesByCategory(category);
//        return result;
//    }
//    
//    @ResponseBody
//    @RequestMapping(value = "/searchUser/{user}", method = RequestMethod.GET)
//    public List<Debate> displayDebatesByUser(@PathVariable String user){
//        List<Debate> result = dao.searchDebatesByUser(user);
//        return result;
//    }
//    
//    @ResponseBody
//    @RequestMapping(value = "/searchDate/{date}", method = RequestMethod.GET)
//    public List<Debate> displayDebatesByDate(@PathVariable String date){
//        List<Debate> result = dao.searchDebatesByDate(date);
//        return result;
//    }
}
