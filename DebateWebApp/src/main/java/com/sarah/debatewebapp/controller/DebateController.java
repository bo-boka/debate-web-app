/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.dao.DebateDao;
import com.sarah.debatewebapp.dto.Debate;
import com.sarah.debatewebapp.dto.Rebuttal;
import java.security.Principal;
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
 * @author Sarah
 */

@Controller
public class DebateController {
    
    DebateDao dao;
    Debate aDebate;
    String currentUser;
    
    @Inject
    public DebateController(DebateDao dao){
        this.dao = dao;
    }
    
    //display page methods
    
    //gets 
    @RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
    public String displayHome(Model model){
        List<String> categories = dao.getAllCategories();
        model.addAttribute("categories", categories);
        
//        List<Debate> debs = dao.getAllPublishedDebates();
//        model.addAttribute("debates", debs);
        return "home";
    }
    
    //gets username with dashboard
    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
    public String displayDash(Model model, Principal principal){      
        List<String> categories = dao.getAllCategories();
        model.addAttribute("categories", categories);
        currentUser = principal.getName();
        return "dashboard";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String displayLogin(Model model){
        return "login";
    }
    
    //gets a pub debate, puts it in the model, and returns single page
    @RequestMapping(value="/singleDebate/{id}", method=RequestMethod.GET)
    public String getSingleDebate(@PathVariable int id, Model model){
        
        aDebate = dao.getPublishedDebateById(id);
        model.addAttribute("oneDebate", aDebate);
        
        List<String> categories = dao.getAllCategories();
        model.addAttribute("categories", categories);
        
        return "single";
    }   
    //create rebuttal relies on aDebate variable changed in getSingleDebate method above
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/singleDebate/rebuttal", method=RequestMethod.POST)
    //add @Valid as parameter
    public Rebuttal createRebuttal(@RequestBody Rebuttal rebuttal){
        rebuttal.setDebateId(aDebate.getId());
        rebuttal.setUser(currentUser);
        aDebate.setNegativeUser(currentUser);
        dao.createRebuttal(rebuttal);
        return rebuttal;
    }  
    
    //create debate from user dash
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/debate", method=RequestMethod.POST)
    //add @Valid as parameter when adding validation
    public Debate createDebate(@RequestBody Debate debate){
        debate.setAffirmativeUser("debatinNotHatin");
        dao.createIntroDebate(debate);
        return debate;
    }
    
    //get all pub debates for home page
    @ResponseBody
    @RequestMapping(value="/debates", method=RequestMethod.GET)
    public List<Debate> getAllPublishedDebates(){
        return dao.getAllPublishedDebates();
    }
    
    //gets a debate for edit modal
    @ResponseBody
    @RequestMapping(value="/singleDebate/debate/{id}", method=RequestMethod.GET)
    public Debate getDebateById(@PathVariable int id){
        return dao.getDebateById(id);
    }
    
    //edit debate button
    //add @Valid twice
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value="/singleDebate/debate/{id}", method=RequestMethod.PUT)
    public void updateDebate(@PathVariable int id, @RequestBody Debate deb){
        dao.updateDebate(deb);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value="/singleDebate/debate/{id}", method=RequestMethod.DELETE)
    public void deleteDebate(@PathVariable int id) {
        dao.deleteDebate(id);
    }
}
