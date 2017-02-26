/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.dao;

import com.sarah.debatewebapp.dto.Debate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * @author Sarah
 */

public class DebateDaoImpl {
    
    HashMap<Integer, Debate> debMap = new HashMap<>();
    
   
    Debate d1 = new Debate("Chron is better than dabs.", "argumental;kdfksd", "mdb8r", "marijuana", "2017-02-21", false);
    Debate d2 = new Debate("Elliot Smith is better than Bob Dylan.", "argumental;kdfksd", "2truDebator", "music", "2017-02-21", true);
    Debate d3 = new Debate("Going vegan is substantially better for the environment", "argumental;kdfksd", "mdb8r", "ethics", "2017-02-22", true);
    Debate d4 = new Debate("Peter Singer is one of the greatest thinkers of our time.", "argumental;kdfksd", "snowOwl22", "philosophy", "2017-02-23", true);
    Debate d5 = new Debate("Excessive advertisments are hurting the American mind.", "argumental;kdfksd", "snowOwl22", "politics", "2017-02-24", true);
    Debate d6 = new Debate("Capitalism is actually pretty inefficient.", "argumental;kdfksd", "sawadeeka", "politics", "2017-02-24", true);
    Debate d7 = new Debate("Cats are ninjas.", "argumental;kdfksd", "2truDebator", "cats", "2017-02-21", true);
    
    public DebateDaoImpl(){
        d1.setId(1);
        debMap.put(1, d1);
        d2.setId(2);
        debMap.put(2, d2);
        d3.setId(3);
        debMap.put(3, d3);
        d4.setId(4);
        debMap.put(4, d4);
        d5.setId(5);
        debMap.put(5, d5);
        d6.setId(6);
        debMap.put(6, d6);
        d7.setId(7);
        debMap.put(7, d7);
        
    }
    
    public List<Debate> getAllDebates(){
        List<Debate> debArr = new ArrayList<>();
        for (Integer d : debMap.keySet()) {
            debArr.add(debMap.get(d));
        }
        return debArr;
    }
    
    public List<String> getCategories(){
        List<String> categories = new ArrayList<>();
        for (Integer d : debMap.keySet()) {
            categories.add(debMap.get(d).getCategory());
        }
        return categories;
    }
    
    public List<String> getAllUsers(){
        List<String> users = new ArrayList<>();
        for (Integer d : debMap.keySet()) {
            users.add(debMap.get(d).getAffirmativeUser());
        }
        return users;
    }
    
    public Debate getDebateById(int id){
        return debMap.get(id);
    }
}
