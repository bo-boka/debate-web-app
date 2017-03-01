/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.dao.DebateDao;
import com.sarah.debatewebapp.dto.Debate;
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

/**
 *
 * @author Sarah
 */

@Controller
public class HomeController {
    
    DebateDao dao;
    
    @Inject
    public HomeController(DebateDao dao){
        this.dao = dao;
    }
    
    @ResponseBody
    @RequestMapping(value="/debates", method=RequestMethod.GET)
    public List<Debate> getAllPublishedDebates(){
        return dao.getAllPublishedDebates();
    }
    
    
    @RequestMapping(value="/singleDebate/{id}", method=RequestMethod.GET)
    public String getSingleDebate(@PathVariable int id, Model model){
        
        Debate aDebate = dao.getDebateById(id);
        model.addAttribute("oneDebate", aDebate);
        
        return "single";
    }
    
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/debate", method=RequestMethod.POST)
    //add @Valid as parameter when adding validation
    public Debate createDebate(@RequestBody Debate debate){
        dao.createDebate(debate);
        return debate;
    }
    

}
