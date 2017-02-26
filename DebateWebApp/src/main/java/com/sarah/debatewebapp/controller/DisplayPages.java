/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * @author Sarah
 */
@Controller
public class DisplayPages {

    @RequestMapping(value={"/","/home"}, method = RequestMethod.GET)
    public String displayHome(){
        return "home";
    }
    
    @RequestMapping(value="/dashboard", method = RequestMethod.GET)
    public String displayDash(){
        return "dashboard";
    }
}
