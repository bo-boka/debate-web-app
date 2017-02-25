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
    
   
    Debate d1 = new Debate("Chron is better than dabs.", "argumental;kdfksd", "mdb8r", "marijuana", "02/21/2017", false);
    Debate d2 = new Debate("Elliot Smith is better than Bob Dylan.", "argumental;kdfksd", "2truDebator", "music", "02/21/2017", true);
    Debate d3 = new Debate("Going vegan is substantially better for the environment", "argumental;kdfksd", "mdb8r", "ethics", "02/22/2017", true);
    Debate d4 = new Debate("Peter Singer is one of the greatest thinkers of our time.", "argumental;kdfksd", "snowOwl22", "philosophy", "02/23/2017", true);
    Debate d5 = new Debate("Excessive advertisments are hurting the American mind.", "argumental;kdfksd", "snowOwl22", "politics", "02/24/2017", true);
    Debate d6 = new Debate("Capitalism is actually pretty inefficient.", "argumental;kdfksd", "sawadeeka", "politics", "02/24/2017", true);
    Debate d7 = new Debate("Cats are ninjas.", "argumental;kdfksd", "2truDebator", "cats", "02/21/2017", true);
    
    public DebateDaoImpl(){
        debMap.put(1, d1);
        debMap.put(2, d2);
        debMap.put(3, d3);
        debMap.put(4, d4);
        debMap.put(5, d5);
        debMap.put(6, d6);
        debMap.put(7, d7);
        
    }
    
    public List<Debate> getAll(){
        List<Debate> debArr = new ArrayList<>();
        for (Integer d : debMap.keySet()) {
            debArr.add(debMap.get(d));
        }
        return debArr;
    }
}
