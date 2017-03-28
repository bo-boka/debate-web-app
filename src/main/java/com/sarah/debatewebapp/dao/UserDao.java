/*
 *  Copyright 2017 SarahBoka
 */
package com.sarah.debatewebapp.dao;

import com.sarah.debatewebapp.dto.User;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sarah
 */
public interface UserDao {
    
    void setJdbcTemplate(JdbcTemplate jdbcTemp);
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    User createUser(User user);
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    void updateUser(User user);
    
    User getUserById(int id);
    
    User getUserByUsername(String username);
    
    void deleteUser(int id);
    
    List<User> getAllUsers();
}
