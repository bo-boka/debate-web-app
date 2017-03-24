/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.dao.DebateDao;
import com.sarah.debatewebapp.dto.Debate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * @author Sarah
 */

@Controller
public class SearchController extends HttpServlet{
    
    DebateDao dao;
    
    @Inject
    public SearchController(DebateDao dao){
        this.dao = dao;
    }

    @RequestMapping(value={"/search", "/profile/search", "/singleUser/search", "/debate/search"}, method=RequestMethod.POST)
    public String doPost(HttpServletRequest request){
        
        List<Debate> debs = new ArrayList<>();
        String searchOption = request.getParameter("searchOption");
        String searchInfo = request.getParameter("searchInfo");

        if (searchInfo.equals("")){
            debs = dao.getAllDebates();
        } else if (searchOption.equals("---")){
            request.setAttribute("badInput", true); //change to message
        } else if (searchOption.equals("resolution")){
            debs = dao.searchDebatesByResolution(searchInfo);
        } else if (searchOption.equals("category")) {
            debs = dao.searchDebatesByCategory(searchInfo);
        } else if (searchOption.equals("user")) {
            debs = dao.searchDebatesByUser(searchInfo);
        } else if (searchOption.equals("date")) {
            debs = dao.searchDebatesByDate(searchInfo);
        }

        request.setAttribute("debates", debs);
            
        return "search";
    }
}
