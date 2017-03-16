/*
 *  Copyright 2017 SarahBoka
 */
package com.sarah.debatewebapp.dao;

import com.sarah.debatewebapp.dto.Debate;
import com.sarah.debatewebapp.dto.Rebuttal;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/*
 * @author Sarah
 */
public interface DebateDao {
    
    void setJdbcTemplate(JdbcTemplate jdbcTemp);
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    Debate createDebate(Debate debate);
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    Debate createIntroDebate(Debate debate);
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    void updateDebate(Debate debate);
    
    List<String> getAllCategories();
    
    void deleteDebate(int id);
    
    Debate getDebateById(int id);
    
    Debate getPublishedDebateById(int id);

    List<Debate> getAllPublishedDebates();
    
    List<Debate> getAllDebates();
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    Rebuttal createRebuttal(Rebuttal rebuttal);
    
}
