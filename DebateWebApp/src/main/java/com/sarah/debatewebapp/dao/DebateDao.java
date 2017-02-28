/*
 *  Copyright 2017 SarahBoka
 */
package com.sarah.debatewebapp.dao;

import com.sarah.debatewebapp.dto.Debate;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;


/*
 * @author Sarah
 */
public interface DebateDao {
    
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    Debate createDebate(Debate debate);
    
    Debate getDebateById(int id);

    List<Debate> getAllPublishedDebates();

    void setJdbcTemplate(JdbcTemplate jdbcTemp);
    
}
