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
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    Debate createDebate(Debate debate);
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    void updateDebate(Debate debate);
    
    List<String> getAllCategories();
    
    void deleteDebate(int id);
    
    Debate getDebateById(int id);

    List<Debate> getAllPublishedDebates();
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    Rebuttal createRebuttal(Rebuttal rebuttal);

    void setJdbcTemplate(JdbcTemplate jdbcTemp);
    
}