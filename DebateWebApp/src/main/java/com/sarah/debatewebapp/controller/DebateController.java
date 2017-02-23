/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.dao.DebateDaoImpl;
import com.sarah.debatewebapp.dto.Debate;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Sarah
 */

@Controller
public class DebateController {
    
    DebateDaoImpl dao = new DebateDaoImpl();
    
    public DebateController(){
        
    }
    
    @RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
    public String displayHome(){
        return "home";
    }
    
    @ResponseBody
    @RequestMapping(value="/debates", method=RequestMethod.GET)
    public List<Debate> getAllDebates(){
        return dao.getAll();
    }
}
