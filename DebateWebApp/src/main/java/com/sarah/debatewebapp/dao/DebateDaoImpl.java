/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.dao;

import com.sarah.debatewebapp.dto.Debate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/*
 * @author Sarah
 */

public class DebateDaoImpl {
    

    
    private JdbcTemplate jdbcTemplate;
    
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemp){
        this.jdbcTemplate = jdbcTemp;
    }
    
    //get all published blogs
    
    private static final String SQL_GET_ALL_PBLSHD_DEBATES = "SELECT debates.debate_id AS id, resolution, content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, date, published FROM debates\n" +
"	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id\n" +
"	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id\n" +
"    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id\n" +
"    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id\n" +
"    WHERE debates.published ORDER BY debates.date DESC";
   
    public List<Debate> getAllPublishedDebates(){
        List<Debate> allPubDebs;
        allPubDebs = (List<Debate>) jdbcTemplate.query(SQL_GET_ALL_PBLSHD_DEBATES, new DebateMapper());
        
        return allPubDebs;
    }
    //ADD REBUTTALS!!!!!!!!!
    private static final class DebateMapper implements RowMapper<Debate>{
        
        @Override
        public Debate mapRow(ResultSet rs, int rowNum) throws SQLException {
            
            Debate mappedD = new Debate();
            int id = rs.getInt("id");
            String res = rs.getString("resolution");
            String content = rs.getString("content");
            String status = rs.getString("status");
            String affUser = rs.getString("username");
            String negUser = rs.getString("username");
            int proV = rs.getInt("proVotes");
            int conV = rs.getInt("conVotes");
            String cat = rs.getString("category");
            String date = rs.getString("date");
            boolean pub = rs.getBoolean("published");
            
            mappedD.setId(id);
            mappedD.setResolution(res);
            mappedD.setContent(content);
            mappedD.setStatus(status);
            mappedD.setAffirmativeUser(affUser);
            mappedD.setNegativeUser(negUser);
            mappedD.setProVotes(proV);
            mappedD.setConVotes(conV);
            mappedD.setCategory(cat);
            mappedD.setDate(date);
            mappedD.setPublished(pub);
            
            return mappedD;
        }
    }
}








//    HashMap<Integer, Debate> debMap = new HashMap<>();
//   
//    Debate d1 = new Debate("Chron is better than dabs.", "argumental;kdfksd", "mdb8r", "marijuana", "2017-02-21", false);
//    Debate d2 = new Debate("Elliot Smith is better than Bob Dylan.", "argumental;kdfksd", "2truDebator", "music", "2017-02-21", true);
//    Debate d3 = new Debate("Going vegan is substantially better for the environment", "argumental;kdfksd", "mdb8r", "ethics", "2017-02-22", true);
//    Debate d4 = new Debate("Peter Singer is one of the greatest thinkers of our time.", "argumental;kdfksd", "snowOwl22", "philosophy", "2017-02-23", true);
//    Debate d5 = new Debate("Excessive advertisments are hurting the American mind.", "argumental;kdfksd", "snowOwl22", "politics", "2017-02-24", true);
//    Debate d6 = new Debate("Capitalism is actually pretty inefficient.", "argumental;kdfksd", "sawadeeka", "politics", "2017-02-24", true);
//    Debate d7 = new Debate("Cats are ninjas.", "argumental;kdfksd", "2truDebator", "cats", "2017-02-21", true);
    
//    public DebateDaoImpl(){
//        d1.setId(1);
//        debMap.put(1, d1);
//        d2.setId(2);
//        debMap.put(2, d2);
//        d3.setId(3);
//        debMap.put(3, d3);
//        d4.setId(4);
//        debMap.put(4, d4);
//        d5.setId(5);
//        debMap.put(5, d5);
//        d6.setId(6);
//        debMap.put(6, d6);
//        d7.setId(7);
//        debMap.put(7, d7);
//        
//    }
//    
//    public List<Debate> getAllDebates(){
//        List<Debate> debArr = new ArrayList<>();
//        for (Integer d : debMap.keySet()) {
//            debArr.add(debMap.get(d));
//        }
//        return debArr;
//    }
//    
//    public List<String> getCategories(){
//        List<String> categories = new ArrayList<>();
//        for (Integer d : debMap.keySet()) {
//            categories.add(debMap.get(d).getCategory());
//        }
//        return categories;
//    }
//    
//    public List<String> getAllUsers(){
//        List<String> users = new ArrayList<>();
//        for (Integer d : debMap.keySet()) {
//            users.add(debMap.get(d).getAffirmativeUser());
//        }
//        return users;
//    }
//    
//    public Debate getDebateById(int id){
//        return debMap.get(id);
//    }