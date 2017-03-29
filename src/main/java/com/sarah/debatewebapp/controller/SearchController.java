/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.controller;

import com.sarah.debatewebapp.dao.DebateDao;
import com.sarah.debatewebapp.dao.UserDao;
import com.sarah.debatewebapp.dto.Debate;
import com.sarah.debatewebapp.dto.User;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * @author Sarah
 */

@Controller
public class SearchController extends HttpServlet{
    
    private DebateDao debDao;
    private UserDao userDao;
    
    @Inject
    public SearchController(DebateDao debDao, UserDao userDao){
        this.debDao = debDao;
        this.userDao = userDao;
    }

    @RequestMapping(value={"/search", "/profile/search", "/debate/search"}, method=RequestMethod.POST)
    public String doPost(HttpServletRequest request, Model model){
        
        List<String> categories = debDao.getAllCategories();
        model.addAttribute("categories", categories);
        
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);
        
        List<Debate> debs = new ArrayList<>();
        String searchOption = request.getParameter("searchOption");
        String searchInfo = request.getParameter("searchInfo");

        if (searchInfo.equals("")){
            request.setAttribute("badInput", true); 
        } else if (searchOption.equals("---")){
            request.setAttribute("badInput", true); 
        } else if (searchOption.equals("resolution")){
            debs = debDao.searchDebatesByResolution(searchInfo);
        } else if (searchOption.equals("category")) {
            debs = debDao.searchDebatesByCategory(searchInfo);
        } else if (searchOption.equals("user")) {
            debs = debDao.searchDebatesByUser(searchInfo);
        } else if (searchOption.equals("date")) {
            debs = debDao.searchDebatesByDate(searchInfo);
        }
        
        if (debs.isEmpty()) request.setAttribute("badInput", true);

        request.setAttribute("debates", debs);
            
        return "search";
    }
}
