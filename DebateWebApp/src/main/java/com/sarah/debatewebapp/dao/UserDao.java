/*
 *  Copyright 2017 SarahBoka
 */
package com.sarah.debatewebapp.dao;

import com.sarah.debatewebapp.dto.User;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Sarah
 */
public interface UserDao {
    
    void setJdbcTemplate(JdbcTemplate jdbcTemp);
    
    User createUser(User user);
    
    void updateUser(User user);
    
    User getUserById(int id);
    
    void deleteUser(int id);
    
    List<User> getAllUsers();
}
