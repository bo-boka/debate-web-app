/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.dao.DebateDao;
import com.sarah.debatewebapp.dto.Debate;
import com.sarah.debatewebapp.dto.Rebuttal;
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
    
    @Inject
    public DebateController(DebateDao dao){
        this.dao = dao;
    }
    
    //display page methods listed first
    @RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
    public String displayHome(Model model){
        List<String> categories = dao.getAllCategories();
        model.addAttribute("categories", categories);
        List<Debate> debs = dao.getAllPublishedDebates();
        model.addAttribute("debates", debs);
        return "home";
    }
    
    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
    public String displayDash(Model model){      
        List<String> categories = dao.getAllCategories();
        model.addAttribute("categories", categories);
        return "dashboard";
    }
    
    @RequestMapping(value="/admin", method=RequestMethod.GET)
    public String displayAdmin(Model model){
        List<String> categories = dao.getAllCategories();
        model.addAttribute("categories", categories);
        return "admin";
    }
    
    //gets a debate, puts it in the model, and returns single page
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
        dao.createRebuttal(rebuttal);
        return rebuttal;
    }  
    
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/debate", method=RequestMethod.POST)
    //add @Valid as parameter when adding validation
    public Debate createDebate(@RequestBody Debate debate){
        dao.createDebate(debate);
        return debate;
    }
    
    @ResponseBody
    @RequestMapping(value="/debates", method=RequestMethod.GET)
    public List<Debate> getAllPublishedDebates(){
        return dao.getAllPublishedDebates();
    }
    
    @ResponseBody
    @RequestMapping(value="/singleDebate/debate/{id}", method=RequestMethod.GET)
    public Debate getDebateById(@PathVariable int id){
        return dao.getDebateById(id);
    }
    
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
