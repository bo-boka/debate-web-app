/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.dao.DebateDao;
import com.sarah.debatewebapp.dto.Debate;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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
public class DashboardController {
    
    DebateDao dao;
    
    @Inject
    public DashboardController(DebateDao dao){
        this.dao = dao;
    }
    
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/debate", method=RequestMethod.POST)
    //add @Valid as parameter when adding validation
    public Debate createDebate(@RequestBody Debate debate){
        dao.createDebate(debate);
        return debate;
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value="/debate", method=RequestMethod.DELETE)
    public void deleteDebate(@PathVariable int id) {
        dao.deleteDebate(id);
    }
}
