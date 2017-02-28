/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.dao;

import com.sarah.debatewebapp.dto.Debate;
import com.sarah.debatewebapp.dto.Rebuttal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

/*
 * @author Sarah
 */

public class DebateDaoImpl implements DebateDao {

    private JdbcTemplate jdbcTemplate;  
    
    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemp){
        this.jdbcTemplate = jdbcTemp;
    }
    
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Debate createDebate(Debate debate){
        return new Debate();
    }
    
    //USE % WHEN WRITING SEARCH QUERIES
    
    private static final String SQL_GET_DEBATE_BY_ID = "SELECT debates.debate_id AS id, resolution, debates.content AS deb_content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, debates.date AS deb_date, published, rebuttal_id, rebuttals.content AS reb_content, rebU.username AS rebUser, `reb_types`.type, rebuttals.date AS reb_date, position FROM debates\n" +
"	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id\n" +
"	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id\n" +
"    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id\n" +
"    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id\n" +
"    LEFT OUTER JOIN `rebuttals` ON debates.debate_id = `rebuttals`.debate_id\n" +
"    LEFT OUTER JOIN `users` AS rebU ON rebuttals.user_id = rebU.user_id\n" +
"    LEFT OUTER JOIN `reb_types` ON rebuttals.type_id = `reb_types`.type_id\n" +
"    WHERE debates.published AND debates.debate_id = ?";
    
    @Override
    public Debate getDebateById(int id){
        try{
            List<Debate> debate = (List<Debate>)jdbcTemplate.query(SQL_GET_DEBATE_BY_ID, new DebateExtractor(), id);
            return debate.get(0);
        }catch(EmptyResultDataAccessException | IndexOutOfBoundsException e){
            return null;
        }
    }
    
    private static final String SQL_GET_ALL_PBLSHD_DEBATES = "SELECT debates.debate_id AS id, resolution, debates.content AS deb_content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, debates.date AS deb_date, published, rebuttal_id, rebuttals.content AS reb_content, rebU.username AS rebUser, `reb_types`.type, rebuttals.date AS reb_date, position FROM debates\n" +
"	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id\n" +
"	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id\n" +
"    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id\n" +
"    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id\n" +
"    LEFT OUTER JOIN `rebuttals` ON debates.debate_id = `rebuttals`.debate_id\n" +
"    LEFT OUTER JOIN `users` AS rebU ON rebuttals.user_id = rebU.user_id\n" +
"    LEFT OUTER JOIN `reb_types` ON rebuttals.type_id = `reb_types`.type_id\n" +
"    WHERE debates.published ORDER BY debates.date DESC";
    
    @Override
    public List<Debate> getAllPublishedDebates(){
        List<Debate> allPubDebs;
        allPubDebs = (List<Debate>) jdbcTemplate.query(SQL_GET_ALL_PBLSHD_DEBATES, new DebateExtractor());       
  
        return allPubDebs;
    }
    
    private static class DebateExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException, DataAccessException {

            Map<Integer, Debate> map = new LinkedHashMap<>();
            ArrayList<Rebuttal> rebList = new ArrayList<>();
            Debate debate = null;
            while (rs.next()) {
                int id = rs.getInt("id");
                debate = map.get(id);
                if (debate == null) {
                    String res = rs.getString("resolution");
                    String content = rs.getString("deb_content");
                    String status = rs.getString("status");
                    String affUser = rs.getString("affirmativeUser");
                    String negUser = rs.getString("negativeUser");
                    int proV = rs.getInt("proVotes");
                    int conV = rs.getInt("conVotes");
                    String cat = rs.getString("category");
                    String date = rs.getString("deb_date");
                    boolean pub = rs.getBoolean("published");
                    debate = new Debate(id, res, content, status, affUser, negUser, proV, conV, cat, date, pub);
                    map.put(id, debate);
                    rebList = new ArrayList<>();
                }
                if (rs.getInt("rebuttal_id") != 0) {
                    Rebuttal rebuttal;          
                    int reb_id = rs.getInt("rebuttal_id");
                    String reb_content = rs.getString("reb_content");
                    String user = rs.getString("rebUser");
                    int deb_id = rs.getInt("id");
                    String type = rs.getString("type");
                    String reb_date = rs.getString("reb_date");
                    boolean position = rs.getBoolean("position");
                    rebuttal = new Rebuttal(reb_id, reb_content, user, deb_id, type, reb_date, position);
                    rebList.add(rebuttal);
                    debate.setRebuttals(rebList);
                } else {
                    debate.setRebuttals(new ArrayList<Rebuttal>());
                }
            }
            return new ArrayList<>(map.values());
        }
    }
}

    //get all published debates with RowMapper 
//    private static final String SQL_GET_ALL_PBLSHD_DEBATES = "SELECT debates.debate_id AS id, resolution, content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, date, published FROM debates\n" +
//"	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id\n" +
//"	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id\n" +
//"    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id\n" +
//"    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id\n" +
//"    WHERE debates.published ORDER BY debates.date DESC";
//    //matching rebuttal query
//    private static final String SQL_GET_ALL_PBLSHD_REBUTTALS = "SELECT rebuttal_id AS id, content, `users`.username, debate_id, `reb_types`.type, date, position FROM rebuttals\n" +
//"	LEFT OUTER JOIN `users` ON rebuttals.user_id = `users`.user_id\n" +
//"    LEFT OUTER JOIN `reb_types` ON rebuttals.type_id = `reb_types`.type_id\n" +
//"    ORDER BY rebuttals.date DESC";
//    
//    @Override
//    public List<Debate> getAllPublishedDebates(){
//        List<Debate> allPubDebs;
//        allPubDebs = (List<Debate>) jdbcTemplate.query(SQL_GET_ALL_PBLSHD_DEBATES, new DebateMapper());       
//        
//        List<Rebuttal> allRebs;
//        allRebs = (List<Rebuttal>) jdbcTemplate.query(SQL_GET_ALL_PBLSHD_REBUTTALS, new RebuttalMapper());
//        
//        ArrayList<Rebuttal> rebs = new ArrayList<>();
//        for (Debate d : allPubDebs){
//            for (Rebuttal r : allRebs){
//                if (d.getId() == r.getDebate()){
//                    rebs.add(r);
//                }
//            }
//            d.setRebuttals(rebs);
//        }
//        return allPubDebs;
//    }
//    
//
//    private static final class DebateMapper implements RowMapper<Debate>{
//        
//        @Override
//        public Debate mapRow(ResultSet rs, int rowNum) throws SQLException {           
//            Debate mappedD = new Debate();
//            int id = rs.getInt("id");
//            String res = rs.getString("resolution");
//            String content = rs.getString("content");
//            String status = rs.getString("status");
//            String affUser = rs.getString("affirmativeUser");
//            String negUser = rs.getString("negativeUser");
//            int proV = rs.getInt("proVotes");
//            int conV = rs.getInt("conVotes");
//            String cat = rs.getString("category");
//            String date = rs.getString("date");
//            boolean pub = rs.getBoolean("published");
//            
//            mappedD.setId(id);
//            mappedD.setResolution(res);
//            mappedD.setContent(content);
//            mappedD.setStatus(status);
//            mappedD.setAffirmativeUser(affUser);
//            mappedD.setNegativeUser(negUser);
//            mappedD.setProVotes(proV);
//            mappedD.setConVotes(conV);
//            mappedD.setCategory(cat);
//            mappedD.setDate(date);
//            mappedD.setPublished(pub);
//            
//            return mappedD;
//        }
//    }
//    
//    private static final class RebuttalMapper implements RowMapper<Rebuttal>{
//        
//        @Override
//        public Rebuttal mapRow(ResultSet rs, int rowNum) throws SQLException { 
//            Rebuttal mappedR = new Rebuttal();
//            
//            int id = rs.getInt("id");
//            String content = rs.getString("content");
//            String user = rs.getString("username");
//            int deb_id = rs.getInt("debate_id");
//            String type = rs.getString("type");
//            String date = rs.getString("date");
//            boolean position = rs.getBoolean("position");
//            
//            mappedR.setId(id);
//            mappedR.setContent(content);
//            mappedR.setUser(user);
//            mappedR.setDebate(deb_id);
//            mappedR.setType(type);
//            mappedR.setDate(date);
//            mappedR.setPosition(position);
//            
//            return mappedR;
//        }          
//    }






