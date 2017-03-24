/*
 *  Copyright 2017 SarahBoka
 */

package com.sarah.debatewebapp.dao;

import com.sarah.debatewebapp.dto.Debate;
import com.sarah.debatewebapp.dto.Rebuttal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
 * @author Sarah
 */

public class DebateDaoImpl implements DebateDao {

    private JdbcTemplate jdbcTemplate;
    Date dizate;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    @Override
    public void setJdbcTemplate(JdbcTemplate jdbcTemp){
        this.jdbcTemplate = jdbcTemp;
    }
    
    private static final String SQL_GET_STATUS_ID = "SELECT status_id FROM deb_statuses WHERE status = ?";
    private static final String SQL_GET_USER_ID = "SELECT user_id FROM users WHERE username = ?";
    private static final String SQL_GET_CATEGORY_ID = "SELECT category_id FROM categories WHERE category = ?";
    private static final String SQL_ADD_DEBATE = "INSERT INTO debates (resolution, content, status_id, affirmativeUser_id, negativeUser_id, proVotes, conVotes, category_id, date, published)\n" +
"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //CREATE FULL DEBATE
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Debate createDebate(Debate debate){
        dizate = new Date();
        int statusId = jdbcTemplate.queryForObject(SQL_GET_STATUS_ID, Integer.class, debate.getStatus());
        int affUserId = jdbcTemplate.queryForObject(SQL_GET_USER_ID, Integer.class, debate.getAffirmativeUser());
        int negUserId = jdbcTemplate.queryForObject(SQL_GET_USER_ID, Integer.class, debate.getNegativeUser());
        int catId = jdbcTemplate.queryForObject(SQL_GET_CATEGORY_ID, Integer.class, debate.getCategory());        
        jdbcTemplate.update(SQL_ADD_DEBATE,
                debate.getResolution(),
                debate.getContent(),
                statusId, //status automatically set
                affUserId,
                negUserId,
                debate.getProVotes(),
                debate.getConVotes(),
                catId,
                sdf.format(dizate), //date stamp automatically set
                debate.isPublished() //published automatically set
        );
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        debate.setId(id);
        return debate;
    } 
    
    private static final String SQL_ADD_INTRO_DEBATE = "INSERT INTO debates (resolution, content, status_id, affirmativeUser_id, category_id, date, published)\n" +
"	VALUES (?, ?, ?, ?, ?, ?, ?)";
    //CREATE INTRO DEBATE
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Debate createIntroDebate(Debate debate){
        dizate = new Date();
        int affUserId = jdbcTemplate.queryForObject(SQL_GET_USER_ID, Integer.class, debate.getAffirmativeUser());
        int catId = jdbcTemplate.queryForObject(SQL_GET_CATEGORY_ID, Integer.class, debate.getCategory());        
        jdbcTemplate.update(SQL_ADD_INTRO_DEBATE,
                debate.getResolution(),
                debate.getContent(),
                1, //status automatically set
                affUserId,
                catId,
                sdf.format(dizate), //date stamp automatically set
                1 //published automatically set
        );
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        debate.setId(id);
        return debate;
    } 
    
    private static final String SQL_UPDATE_DEBATE = "UPDATE debates SET resolution =?, content=?, status_id=?, affirmativeUser_id=?, negativeUser_id=?, proVotes=?, conVotes=?, category_id=?, date=?, published=? \n" +
"	WHERE debate_id=?";
    //UPDATE DEBATE
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public void updateDebate(Debate debate){
        int statusId = jdbcTemplate.queryForObject(SQL_GET_STATUS_ID, Integer.class, debate.getStatus());
        int affUserId = jdbcTemplate.queryForObject(SQL_GET_USER_ID, Integer.class, debate.getAffirmativeUser());
        Integer negUserId;
        try {
            negUserId = jdbcTemplate.queryForObject(SQL_GET_USER_ID, Integer.class, debate.getNegativeUser());
        } catch(EmptyResultDataAccessException e){
            negUserId = null;
        }
        int catId = jdbcTemplate.queryForObject(SQL_GET_CATEGORY_ID, Integer.class, debate.getCategory());        
        
        jdbcTemplate.update(SQL_UPDATE_DEBATE,
                debate.getResolution(),
                debate.getContent(),
                statusId,
                affUserId,
                negUserId,
                debate.getProVotes(),
                debate.getConVotes(),
                catId,
                debate.getDate(),
                debate.isPublished(),
                debate.getId()
        );
    }
   
    
    private static final String SQL_DELETE_DEBATE = "DELETE FROM debates WHERE debate_id = ?";
    private static final String SQL_DELETE_DEBATE_REBUTTALS = "DELETE FROM rebuttals WHERE debate_id = ?";
    //DELETE DEBATE
    @Override
    public void deleteDebate(int id){
        jdbcTemplate.update(SQL_DELETE_DEBATE, id);
        jdbcTemplate.update(SQL_DELETE_DEBATE_REBUTTALS, id);
    }
    
    private static final String SQL_GET_DEBATE_BY_ID = "SELECT debates.debate_id AS id, resolution, debates.content AS deb_content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, debates.date AS deb_date, published, rebuttal_id, rebuttals.content AS reb_content, rebU.username AS rebUser, `reb_types`.type, rebuttals.date AS reb_date, position FROM debates\n" +
"	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id\n" +
"	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id\n" +
"    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id\n" +
"    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id\n" +
"    LEFT OUTER JOIN `rebuttals` ON debates.debate_id = `rebuttals`.debate_id\n" +
"    LEFT OUTER JOIN `users` AS rebU ON rebuttals.user_id = rebU.user_id\n" +
"    LEFT OUTER JOIN `reb_types` ON rebuttals.type_id = `reb_types`.type_id\n" +
"    WHERE debates.debate_id = ?";
    //GET A DEBATE
    @Override
    public Debate getDebateById(int id){
        try{
            List<Debate> debate = (List<Debate>)jdbcTemplate.query(SQL_GET_DEBATE_BY_ID, new DebateExtractor(), id);
            return debate.get(0);
        }catch(EmptyResultDataAccessException | IndexOutOfBoundsException e){
            return null;
        }
    }
    
    private static final String SQL_GET__PUBLISHED_DEBATE_BY_ID = "SELECT debates.debate_id AS id, resolution, debates.content AS deb_content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, debates.date AS deb_date, published, rebuttal_id, rebuttals.content AS reb_content, rebU.username AS rebUser, `reb_types`.type, rebuttals.date AS reb_date, position FROM debates\n" +
"	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id\n" +
"	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id\n" +
"    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id\n" +
"    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id\n" +
"    LEFT OUTER JOIN `rebuttals` ON debates.debate_id = `rebuttals`.debate_id\n" +
"    LEFT OUTER JOIN `users` AS rebU ON rebuttals.user_id = rebU.user_id\n" +
"    LEFT OUTER JOIN `reb_types` ON rebuttals.type_id = `reb_types`.type_id\n" +
"    WHERE debates.published AND debates.debate_id = ?";
    //GET A PUBLISHED DEBATE
    @Override
    public Debate getPublishedDebateById(int id){
        try{
            List<Debate> debate = (List<Debate>)jdbcTemplate.query(SQL_GET__PUBLISHED_DEBATE_BY_ID, new DebateExtractor(), id);
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
    //GET ALL PUB DEBATES
    @Override
    public List<Debate> getAllPublishedDebates(){
        List<Debate> allPubDebs;
        allPubDebs = (List<Debate>) jdbcTemplate.query(SQL_GET_ALL_PBLSHD_DEBATES, new DebateExtractor());       
        return allPubDebs;
    }
    
    private static final String SQL_GET_ALL_DEBATES = "SELECT debates.debate_id AS id, resolution, debates.content AS deb_content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, debates.date AS deb_date, published, rebuttal_id, rebuttals.content AS reb_content, rebU.username AS rebUser, `reb_types`.type, rebuttals.date AS reb_date, position FROM debates\n" +
"	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id\n" +
"	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id\n" +
"    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id\n" +
"    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id\n" +
"    LEFT OUTER JOIN `rebuttals` ON debates.debate_id = `rebuttals`.debate_id\n" +
"    LEFT OUTER JOIN `users` AS rebU ON rebuttals.user_id = rebU.user_id\n" +
"    LEFT OUTER JOIN `reb_types` ON rebuttals.type_id = `reb_types`.type_id\n" +
"    ORDER BY debates.date DESC";
    //GET ALL DEBATES, INCLUDING NON PUB
    @Override
    public List<Debate> getAllDebates(){
        List<Debate> allDebs;
        allDebs = (List<Debate>) jdbcTemplate.query(SQL_GET_ALL_DEBATES, new DebateExtractor());
        return allDebs;
     }
    
    private static final String SQL_ADD_REBUTTAL= "INSERT INTO rebuttals (content, user_id, debate_id, type_id, date, position)\n" +
"	VALUE (?, ?, ?, ?, ?, ?)";
    private static final String SQL_GET_REB_TYPE_ID = "SELECT type_id FROM reb_types WHERE type= ?";
    //CREATE REBUTTAL
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @Override
    public Rebuttal createRebuttal(Rebuttal rebuttal){
        Debate deb = getDebateById(rebuttal.getDebateId());
        if (deb.getRebuttals().size() < 5) {
            dizate = new Date();
            int userId = jdbcTemplate.queryForObject(SQL_GET_USER_ID, Integer.class, rebuttal.getUser());
            int typeId = jdbcTemplate.queryForObject(SQL_GET_REB_TYPE_ID, Integer.class, rebuttal.getType());
            jdbcTemplate.update(SQL_ADD_REBUTTAL,
                    rebuttal.getContent(),
                    userId,
                    rebuttal.getDebateId(),
                    typeId,
                    sdf.format(dizate),
                    rebuttal.isPosition()
                    );
            int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            rebuttal.setId(id);
            if (getDebateById(rebuttal.getDebateId()).getRebuttals().size() == 5) {
                deb.setStatus("voting");
                updateDebate(deb);
            }
            return rebuttal;
        } else {
            deb.setStatus("voting");
            return null;
        }
        
    }   
    
    private static final String SQL_GET_ALL_CATEGORIES = "SELECT category FROM categories";
    //GET CATEGORIES
    @Override
    public List<String> getAllCategories(){
        return jdbcTemplate.queryForList(SQL_GET_ALL_CATEGORIES, String.class);
    }
    
    private static final String SQL_SEARCH_BY_RESOLUTION = "SELECT debates.debate_id AS id, resolution, debates.content AS deb_content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, debates.date AS deb_date, published, rebuttal_id, rebuttals.content AS reb_content, rebU.username AS rebUser, `reb_types`.type, rebuttals.date AS reb_date, position FROM debates\n" +
"	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id\n" +
"	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id\n" +
"    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id\n" +
"    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id\n" +
"    LEFT OUTER JOIN `rebuttals` ON debates.debate_id = `rebuttals`.debate_id\n" +
"    LEFT OUTER JOIN `users` AS rebU ON rebuttals.user_id = rebU.user_id\n" +
"    LEFT OUTER JOIN `reb_types` ON rebuttals.type_id = `reb_types`.type_id\n" +
"    WHERE debates.published AND debates.resolution LIKE ? ORDER BY debates.date DESC";
    //SEARCH RESOLUTION
    @Override
    public List<Debate> searchDebatesByResolution(String resolution){
        try{
            resolution = "%" + resolution + "%";
            return (List<Debate>) jdbcTemplate.query(SQL_SEARCH_BY_RESOLUTION, new DebateExtractor(), resolution);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    //SEARCH BLOGS BY CATEGORY
    private static final String SQL_SEARCH_BY_CATEGORY = "SELECT debates.debate_id AS id, resolution, debates.content AS deb_content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, debates.date AS deb_date, published, rebuttal_id, rebuttals.content AS reb_content, rebU.username AS rebUser, `reb_types`.type, rebuttals.date AS reb_date, position FROM debates\n" +
"	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id\n" +
"	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id\n" +
"    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id\n" +
"    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id\n" +
"    LEFT OUTER JOIN `rebuttals` ON debates.debate_id = `rebuttals`.debate_id\n" +
"    LEFT OUTER JOIN `users` AS rebU ON rebuttals.user_id = rebU.user_id\n" +
"    LEFT OUTER JOIN `reb_types` ON rebuttals.type_id = `reb_types`.type_id\n" +
"    WHERE debates.published AND categories.category = ? ORDER BY debates.date DESC";
    @Override
    public List<Debate> searchDebatesByCategory(String searchCategory) {
        try {
            return (List<Debate>) jdbcTemplate.query(SQL_SEARCH_BY_CATEGORY, new DebateExtractor(), searchCategory);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //SEARCH BLOGS BY USER (aff and neg)
    private static final String SQL_SEARCH_BY_USER = "SELECT debates.debate_id AS id, resolution, debates.content AS deb_content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, debates.date AS deb_date, published, rebuttal_id, rebuttals.content AS reb_content, rebU.username AS rebUser, `reb_types`.type, rebuttals.date AS reb_date, position FROM debates\n" +
"	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id\n" +
"	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id\n" +
"    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id\n" +
"    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id\n" +
"    LEFT OUTER JOIN `rebuttals` ON debates.debate_id = `rebuttals`.debate_id\n" +
"    LEFT OUTER JOIN `users` AS rebU ON rebuttals.user_id = rebU.user_id\n" +
"    LEFT OUTER JOIN `reb_types` ON rebuttals.type_id = `reb_types`.type_id\n" +
"    WHERE debates.published AND affU.username = ? OR negU.username = ? ORDER BY debates.date DESC";
    @Override
    public List<Debate> searchDebatesByUser(String searchUser) {
        try {
            return (List<Debate>) jdbcTemplate.query(SQL_SEARCH_BY_USER, new DebateExtractor(), searchUser, searchUser);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //SEARCH BLOGS BY DATE
    private static final String SQL_SEARCH_BY_DATE = "SELECT debates.debate_id AS id, resolution, debates.content AS deb_content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, debates.date AS deb_date, published, rebuttal_id, rebuttals.content AS reb_content, rebU.username AS rebUser, `reb_types`.type, rebuttals.date AS reb_date, position FROM debates\n" +
"	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id\n" +
"	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id\n" +
"    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id\n" +
"    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id\n" +
"    LEFT OUTER JOIN `rebuttals` ON debates.debate_id = `rebuttals`.debate_id\n" +
"    LEFT OUTER JOIN `users` AS rebU ON rebuttals.user_id = rebU.user_id\n" +
"    LEFT OUTER JOIN `reb_types` ON rebuttals.type_id = `reb_types`.type_id\n" +
"    WHERE debates.published AND debates.date = ? ORDER BY debates.date DESC";

    @Override
    public List<Debate> searchDebatesByDate(String searchDate) {
        try {
            return (List<Debate>) jdbcTemplate.query(SQL_SEARCH_BY_DATE, new DebateExtractor(), searchDate);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    
    //SET EXTRACTOR
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






