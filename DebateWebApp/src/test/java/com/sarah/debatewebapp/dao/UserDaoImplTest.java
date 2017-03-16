/*
 *  Copyright 2017 SarahBoka
 */
package com.sarah.debatewebapp.dao;

import com.sarah.debatewebapp.dto.User;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Sarah
 */
public class UserDaoImplTest {
    
    private UserDao testDao;
    
    public UserDaoImplTest() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext factory = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        testDao = factory.getBean("userJdbcDao", UserDao.class);
        JdbcTemplate cleaner = factory.getBean("jdbcTemplateBeanUser", JdbcTemplate.class);
        cleaner.execute("DELETE FROM rebuttals WHERE 1=1");
        cleaner.execute("DELETE FROM authorities WHERE 1=1");
        cleaner.execute("DELETE FROM debates WHERE 1=1"); 
        cleaner.execute("DELETE FROM users WHERE 1=1"); 
        
    }
    
    User[] usersForTesting = {
        new User(1, "debatinNotHatin", "password", "Ally", "Smith", "asmith@debator.com", 0, 0, 0, "ROLE_USER", true),
	new User(2, "2truDebator", "password", "Bob", "Gualla", "email@sendit.com", 0, 0, 0, "ROLE_USER", true),
        new User(3, "cheesinForTheWeekend", "password", "Lana", "Brown", "email@sendin.com", 0, 0, 0, "ROLE_USER", true),
        new User(4, "mdb8r", "password", "Arielle", "Cho", "thisemail@gmail.com", 0, 0, 0, "ROLE_USER", true),
        new User(5, "snowOwl22", "password", "Gertrude", "Hammerquist", "hammyq@gmail.com", 0, 0, 0, "ROLE_USER", true),
        new User(6, "sawadeeka", "password", "Juan", "Jimenez", "jimenez@gmail.com", 0, 0, 0, "ROLE_USER", true),
	new User(7, "smoothDeb", "password", "Neena", "Gupta", "neenag@gmail.com", 0, 0, 0, "ROLE_USER", true)
    };
    
    User[] duplicateUsers = {
        new User(1, "debatinNotHatin", "password", "Ally", "Smith", "asmith@debator.com", 0, 0, 0, "ROLE_USER", true),
	new User(2, "2truDebator", "password", "Bob", "Gualla", "email@sendit.com", 0, 0, 0, "ROLE_USER", true),
        new User(3, "cheesinForTheWeekend", "password", "Lana", "Brown", "email@sendin.com", 0, 0, 0, "ROLE_USER", true),
        new User(4, "mdb8r", "password", "Arielle", "Cho", "thisemail@gmail.com", 0, 0, 0, "ROLE_USER", true),
        new User(5, "snowOwl22", "password", "Gertrude", "Hammerquist", "hammyq@gmail.com", 0, 0, 0, "ROLE_USER", true),
        new User(6, "sawadeeka", "password", "Juan", "Jimenez", "jimenez@gmail.com", 0, 0, 0, "ROLE_USER", true),
	new User(7, "smoothDeb", "password", "Neena", "Gupta", "neenag@gmail.com", 0, 0, 0, "ROLE_USER", true)
    };
    
    User[] similarUsers = {
        new User(1, "debatingNotHating", "password", "Ally", "Smith", "asmith@debator.com", 0, 0, 0, "ROLE_USER", true),
	new User(2, "truDebator", "password", "Bobby", "Gualla", "email@sendit.com", 0, 0, 0, "ROLE_USER", true),
        new User(3, "cheesingForTheWeekend", "password", "Lana", "Brown", "email@sendin.com", 0, 0, 0, "ROLE_USER", true),
        new User(4, "mdb8rr", "password", "Arielle", "Cho", "thisemail@gmail.com", 0, 0, 0, "ROLE_USER", true),
        new User(5, "snowOwl2", "password", "Gertrude", "Hammerquist", "hammyq@gmail.com", 0, 0, 0, "ROLE_USER", true),
        new User(6, "sawadeekap", "password", "Juan", "Jimenez", "jimenez@ggggg.com", 0, 0, 0, "ROLE_USER", true),
	new User(7, "smooveDebator", "password", "Neena", "Gupta", "neenag@gmail.com", 0, 0, 0, "ROLE_USER", true)
    };
    
    @Test
    public void addOneToEmptyDaoTest() {
        
        User testUser = new User(1, "newgsername", "password", "firstn", "lastn", "emailad", 0, 0, 0, "ROLE_USER", true);
        testDao.createUser(testUser);
        User testUserAgain = testDao.getUserById(testUser.getId());

        Assert.assertEquals(testUser.getId(), testUserAgain.getId());
        Assert.assertEquals(testUser.getUsername(), testUserAgain.getUsername());
        Assert.assertEquals(testUser.getPassword(), testUserAgain.getPassword());
        Assert.assertEquals(testUser.getFirstName(), testUserAgain.getFirstName());
        Assert.assertEquals(testUser.getLastName(), testUserAgain.getLastName());
        Assert.assertEquals(testUser.getEmail(), testUserAgain.getEmail());
        Assert.assertEquals(testUser.getWins(), testUserAgain.getWins());
        Assert.assertEquals(testUser.getTies(), testUserAgain.getTies());
        Assert.assertEquals(testUser.getTotalDebates(), testUserAgain.getTotalDebates());
        Assert.assertEquals(testUser.getRole(), testUserAgain.getRole());
        Assert.assertEquals(testUser.isEnabled(), testUserAgain.isEnabled());

        junit.framework.Assert.assertEquals("User stored, vs. user retrieved does not match",
                testUser, testUserAgain);
    }

    @Test
    public void testAgainstEmptyDAO() {

        junit.framework.Assert.assertNull("Asking for a non existant user should return null.", testDao.getUserById(945));
        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
        junit.framework.Assert.assertEquals("Expected user count of 'all users' is nonzero with empty DAO.", 0, testDao.getAllUsers().size());
    }

    @Test
    public void testAddOneUser() {
        User testUser = usersForTesting[0];
        User testUserAgain = testDao.createUser(testUser);

        Assert.assertEquals(testUser.getId(), testUserAgain.getId());
        Assert.assertEquals(testUser.getUsername(), testUserAgain.getUsername());
        Assert.assertEquals(testUser.getPassword(), testUserAgain.getPassword());
        Assert.assertEquals(testUser.getFirstName(), testUserAgain.getFirstName());
        Assert.assertEquals(testUser.getLastName(), testUserAgain.getLastName());
        Assert.assertEquals(testUser.getEmail(), testUserAgain.getEmail());
        Assert.assertEquals(testUser.getWins(), testUserAgain.getWins());
        Assert.assertEquals(testUser.getTies(), testUserAgain.getTies());
        Assert.assertEquals(testUser.getTotalDebates(), testUserAgain.getTotalDebates());
        Assert.assertEquals(testUser.getRole(), testUserAgain.getRole());
        Assert.assertEquals(testUser.isEnabled(), testUserAgain.isEnabled());
        junit.framework.Assert.assertEquals("Returned user does not match expected.", testUser, testUserAgain);
        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
        junit.framework.Assert.assertEquals("Expected user count of 'all users' does not match after adding one user.", 1, testDao.getAllUsers().size());
        junit.framework.Assert.assertTrue("Returned user in getAllUsers does not match expected.", testDao.getAllUsers().contains(testUser));

    }

    @Test
    public void testUpdateUser() {
        testDao.createUser(usersForTesting[0]);
        similarUsers[0].setId(usersForTesting[0].getId());
        testDao.updateUser(similarUsers[0]);

        Assert.assertEquals(similarUsers[0].getId(), testDao.getUserById(similarUsers[0].getId()).getId());
        Assert.assertEquals(similarUsers[0].getUsername(), testDao.getUserById(similarUsers[0].getId()).getUsername());
        Assert.assertEquals(similarUsers[0].getPassword(), testDao.getUserById(similarUsers[0].getId()).getPassword());
        Assert.assertEquals(similarUsers[0].getFirstName(), testDao.getUserById(similarUsers[0].getId()).getFirstName());
        Assert.assertEquals(similarUsers[0].getLastName(), testDao.getUserById(similarUsers[0].getId()).getLastName());
        Assert.assertEquals(similarUsers[0].getEmail(), testDao.getUserById(similarUsers[0].getId()).getEmail());
        Assert.assertEquals(similarUsers[0].getWins(), testDao.getUserById(similarUsers[0].getId()).getWins());
        Assert.assertEquals(similarUsers[0].getTies(), testDao.getUserById(similarUsers[0].getId()).getTies());
        Assert.assertEquals(similarUsers[0].getTotalDebates(), testDao.getUserById(similarUsers[0].getId()).getTotalDebates());
        Assert.assertEquals(similarUsers[0].getRole(), testDao.getUserById(similarUsers[0].getId()).getRole());
        Assert.assertEquals(similarUsers[0].isEnabled(), testDao.getUserById(similarUsers[0].getId()).isEnabled());
        junit.framework.Assert.assertEquals("Updated user get does not match expected.", similarUsers[0], testDao.getUserById(similarUsers[0].getId()));
        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
        junit.framework.Assert.assertEquals("Expected user count of 'all users' does not match after replacing one user.", 1, testDao.getAllUsers().size());
        junit.framework.Assert.assertTrue("Returned user in getAllUsers does not match expected.", testDao.getAllUsers().contains(similarUsers[0]));
    }

    @Test
    public void testAddMultipleUsers() {
        for (User user : usersForTesting) {
            testDao.createUser(user);
        }

        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
        junit.framework.Assert.assertEquals("Expected user count of 'all users' does not match after adding several users.",
                usersForTesting.length, testDao.getAllUsers().size());

        for (int i = 0; i < usersForTesting.length; i++) {
            
            Assert.assertEquals(usersForTesting[i].getId(), testDao.getUserById(usersForTesting[i].getId()).getId());
            Assert.assertEquals(usersForTesting[i].getUsername(), testDao.getUserById(usersForTesting[i].getId()).getUsername());
            Assert.assertEquals(usersForTesting[i].getPassword(), testDao.getUserById(usersForTesting[i].getId()).getPassword());
            Assert.assertEquals(usersForTesting[i].getFirstName(), testDao.getUserById(usersForTesting[i].getId()).getFirstName());
            Assert.assertEquals(usersForTesting[i].getLastName(), testDao.getUserById(usersForTesting[i].getId()).getLastName());
            Assert.assertEquals(usersForTesting[i].getEmail(), testDao.getUserById(usersForTesting[i].getId()).getEmail());
            Assert.assertEquals(usersForTesting[i].getWins(), testDao.getUserById(usersForTesting[i].getId()).getWins());
            Assert.assertEquals(usersForTesting[i].getTies(), testDao.getUserById(usersForTesting[i].getId()).getTies());
            Assert.assertEquals(usersForTesting[i].getTotalDebates(), testDao.getUserById(usersForTesting[i].getId()).getTotalDebates());
            Assert.assertEquals(usersForTesting[i].getRole(), testDao.getUserById(usersForTesting[i].getId()).getRole());
            Assert.assertEquals(usersForTesting[i].isEnabled(), testDao.getUserById(usersForTesting[i].getId()).isEnabled());
            junit.framework.Assert.assertEquals("Returned user does not match expected.", usersForTesting[i], testDao.getUserById(usersForTesting[i].getId()));
            junit.framework.Assert.assertTrue("Returned user in getAllUsers does not match expected.", testDao.getAllUsers().contains(usersForTesting[i]));
        }

    }

    @Test
    public void testUpdateMultipleUsers() {
        for (User user : usersForTesting) {
            testDao.createUser(user);
        }

        for (int i = 0; i < usersForTesting.length; i++) {
            similarUsers[i].setId(usersForTesting[i].getId());
            testDao.updateUser(similarUsers[i]);
        }

        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
        junit.framework.Assert.assertEquals("Expected user count of 'all users' does not match after replacing several users.",
                usersForTesting.length, testDao.getAllUsers().size());

        for (int i = 0; i < similarUsers.length; i++) {
            Assert.assertEquals(similarUsers[i].getId(), testDao.getUserById(similarUsers[i].getId()).getId());
            Assert.assertEquals(similarUsers[i].getUsername(), testDao.getUserById(similarUsers[i].getId()).getUsername());
            Assert.assertEquals(similarUsers[i].getPassword(), testDao.getUserById(similarUsers[i].getId()).getPassword());
            Assert.assertEquals(similarUsers[i].getFirstName(), testDao.getUserById(similarUsers[i].getId()).getFirstName());
            Assert.assertEquals(similarUsers[i].getLastName(), testDao.getUserById(similarUsers[i].getId()).getLastName());
            Assert.assertEquals(similarUsers[i].getEmail(), testDao.getUserById(similarUsers[i].getId()).getEmail());
            Assert.assertEquals(similarUsers[i].getWins(), testDao.getUserById(similarUsers[i].getId()).getWins());
            Assert.assertEquals(similarUsers[i].getTies(), testDao.getUserById(similarUsers[i].getId()).getTies());
            Assert.assertEquals(similarUsers[i].getTotalDebates(), testDao.getUserById(similarUsers[i].getId()).getTotalDebates());
            Assert.assertEquals(similarUsers[i].getRole(), testDao.getUserById(similarUsers[i].getId()).getRole());
            Assert.assertEquals(similarUsers[i].isEnabled(), testDao.getUserById(similarUsers[i].getId()).isEnabled());
            junit.framework.Assert.assertEquals("Get user does not match expected return on update.", similarUsers[i], testDao.getUserById(similarUsers[i].getId()));
            junit.framework.Assert.assertTrue("Returned user in getAllUsers does not match expected.", testDao.getAllUsers().contains(similarUsers[i]));
        }

    }

    @Test
    public void testAddSimilarUsers() {
        for (User user : usersForTesting) {
            testDao.createUser(user);
        }
        for (User user : similarUsers) {
            testDao.createUser(user);
        }
        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
        junit.framework.Assert.assertEquals("Expected user count does not match after adding several similar users.",
                usersForTesting.length + similarUsers.length, testDao.getAllUsers().size());
        junit.framework.Assert.assertEquals("Expected user count of 'all users' does not match after adding several similar users.",
                usersForTesting.length + similarUsers.length, testDao.getAllUsers().size());
        for (int i = 0; i < similarUsers.length; i++) {
            junit.framework.Assert.assertEquals("Get user does not match expected return on addition of similar user.", similarUsers[i],
                    testDao.getUserById(similarUsers[i].getId()));
            junit.framework.Assert.assertTrue("Returned user in getAllUsers does not match expected.", testDao.getAllUsers().contains(similarUsers[i]));
        }
        for (int i = 0; i < usersForTesting.length; i++) {
            junit.framework.Assert.assertEquals("Get user does not match expected return on addition of similar user.", usersForTesting[i],
                    testDao.getUserById(usersForTesting[i].getId()));
            junit.framework.Assert.assertTrue("Returned user in getAllUsers does not match expected.", testDao.getAllUsers().contains(usersForTesting[i]));
        }
    }

    @Test
    public void testAddAndRemoveOneUser() {
        testDao.createUser(usersForTesting[0]);
        testDao.deleteUser(usersForTesting[0].getId());

        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
        junit.framework.Assert.assertNull("Get user should return null after being removed.", testDao.getUserById(usersForTesting[0].getId()));
        junit.framework.Assert.assertEquals("Expected user count of 'all users' should be zero when adding/removing a single user.", 0, testDao.getAllUsers().size());
    }

    @Test
    public void testAddAndRemoveUserTwice() {
        testDao.createUser(usersForTesting[0]);
        testDao.deleteUser(usersForTesting[0].getId());
        testDao.deleteUser(usersForTesting[0].getId());

        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
        junit.framework.Assert.assertNull("User should return null after being removed.", testDao.getUserById(usersForTesting[0].getId()));
        junit.framework.Assert.assertEquals("Expected user count of 'all users' should be zero when adding/removing a single user twice.", 0, testDao.getAllUsers().size());
    }

    @Test
    public void testAddAndRemoveMultipleUsers() {

        for (User user : usersForTesting) {
            testDao.createUser(user);
        }

        int usersAdded = usersForTesting.length;
        for (int i = 0; i < usersForTesting.length; i += 2) {
            testDao.deleteUser(usersForTesting[i].getId());
            usersAdded--;
        }

        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
        junit.framework.Assert.assertEquals("Expected user count of 'all users' does not match after adding & removing several users.",
                usersAdded, testDao.getAllUsers().size());

        for (int i = 0; i < usersForTesting.length; i++) {
            if (i % 2 == 1) {
                junit.framework.Assert.assertEquals("Returned user does not match expected.", usersForTesting[i], testDao.getUserById(usersForTesting[i].getId()));
            } else {
                junit.framework.Assert.assertNull("User should be removed and return null.", testDao.getUserById(usersForTesting[i].getId()));
            }
        }
    }

    @Test
    public void testAddAndRemoveUsersMultipleTimes() {
        for (User user : usersForTesting) {
            testDao.createUser(user);
        }
        for (User user : usersForTesting) {
            testDao.deleteUser(user.getId());
        }
        for (User user : usersForTesting) {
            testDao.createUser(user);
        }
        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
        junit.framework.Assert.assertEquals("Expected user count of 'all users' should be zero when adding/removing a all users.",
                usersForTesting.length, testDao.getAllUsers().size());
        for (int i = 0; i < usersForTesting.length; i++) {
            User user = usersForTesting[i];
            junit.framework.Assert.assertEquals("User should return after being re-added.", user, testDao.getUserById(user.getId()));
            testDao.deleteUser(user.getId());
            junit.framework.Assert.assertNull("User should return null after being removed.", testDao.getUserById(user.getId()));
        }
        junit.framework.Assert.assertEquals("Expected user count of 'all users' should be zero when adding/removing a all users.", 0, testDao.getAllUsers().size());
    }

    @Test
    public void testUserCountOnAddition() {
        // Add all users and check that count increments appropriately
        for (int i = 0; i < usersForTesting.length; i++) {
            testDao.createUser(usersForTesting[i]);
            junit.framework.Assert.assertEquals("Expected " + (i + 1) + " users after adding users.",
                    i + 1, testDao.getAllUsers().size());
        }
    }

    @Test
    public void testUserCountOnUpdate() {
        // Add all users and check that count increments appropriately
        for (int i = 0; i < usersForTesting.length; i++) {
            testDao.createUser(usersForTesting[i]);
            similarUsers[i].setId(usersForTesting[i].getId());
            testDao.updateUser(similarUsers[i]);
            junit.framework.Assert.assertEquals("Expected " + (i + 1) + " users after updating users.",
                    i + 1, testDao.getAllUsers().size());
        }
    }

    @Test
    public void testUserCountAfterRemoval() {

        // Add all users
        for (int i = 0; i < usersForTesting.length; i++) {
            testDao.createUser(usersForTesting[i]);
        }

        // Remove users one by one and test that count decrements properly
        for (int i = 0; i < usersForTesting.length; i++) {
            testDao.deleteUser(usersForTesting[i].getId());
            junit.framework.Assert.assertEquals("Expected " + (usersForTesting.length - i - 1) + " users after removing users.",
                    usersForTesting.length - i - 1, testDao.getAllUsers().size());
        }
    }

    @Test
    public void testUsersAfterRemovalOfNonExistent() {
        // Add all users
        for (int i = 0; i < usersForTesting.length; i++) {
            testDao.createUser(usersForTesting[i]);
        }
        testDao.deleteUser(100);
        junit.framework.Assert.assertEquals("Expected " + usersForTesting.length + " users after removing users.",
                usersForTesting.length, testDao.getAllUsers().size());
    }

    @Test
    public void testUserCountAfterTwiceRemoved() {
        // Add all users
        for (int i = 0; i < usersForTesting.length; i++) {
            testDao.createUser(usersForTesting[i]);
        }
        // Remove users one by one and test that count decrements properly
        for (int i = 0; i < usersForTesting.length; i++) {
            testDao.deleteUser(usersForTesting[i].getId());
        }
        junit.framework.Assert.assertEquals("Expected " + 0 + " users after removing users.",
                0, testDao.getAllUsers().size());
        // Remove users one by one and test that count decrements properly
        for (int i = 0; i < usersForTesting.length; i++) {
            testDao.deleteUser(usersForTesting[i].getId());
        }
        junit.framework.Assert.assertEquals("Expected " + 0 + " users after attempting to re-remove users.",
                0, testDao.getAllUsers().size());
    }

//  SEARCH TESTS
    
//    @Test
//    public void testSearchUsersByResolution() {
//        for (User user : usersForTesting) {
//            testDao.createUser(user);
//        }
//        for (User user : similarUsers) {
//            testDao.createUser(user);
//        }
//
//        Assert.assertEquals(usersForTesting[0].getId(), testDao.getUserById(usersForTesting[0].getId()).getId());
//        Assert.assertEquals(usersForTesting[0].getUsername(), testDao.getUserById(usersForTesting[0].getId()).getUsername());
//        Assert.assertEquals(usersForTesting[0].getPassword(), testDao.getUserById(usersForTesting[0].getId()).getPassword());
//        Assert.assertEquals(usersForTesting[0].getFirstName(), testDao.getUserById(usersForTesting[0].getId()).getFirstName());
//        Assert.assertEquals(usersForTesting[0].getLastName(), testDao.getUserById(usersForTesting[0].getId()).getLastName());
//        Assert.assertEquals(usersForTesting[0].getEmail(), testDao.getUserById(usersForTesting[0].getId()).getEmail());
//        Assert.assertEquals(usersForTesting[0].getWins(), testDao.getUserById(usersForTesting[0].getId()).getWins());
//        Assert.assertEquals(usersForTesting[0].getTies(), testDao.getUserById(usersForTesting[0].getId()).getTies());
//        Assert.assertEquals(usersForTesting[0].getTotalDebates(), testDao.getUserById(usersForTesting[0].getId()).getTotalDebates());
//        Assert.assertEquals(usersForTesting[0].getRole(), testDao.getUserById(usersForTesting[0].getId()).getRole());
//        Assert.assertEquals(usersForTesting[0].isEnabled(), testDao.getUserById(usersForTesting[0].getId()).isEnabled());
//        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
//        String testSearch = usersForTesting[0].getTitle();
//        List<User> testSearchResults = testDao.searchUsersByTitle(testSearch);
//        
//        Assert.assertEquals(2, testSearchResults.size());
//
//        Assert.assertTrue(testSearchResults.contains(usersForTesting[1]));
//        
//        Assert.assertTrue(testSearchResults.contains(similarUsers[1]));
//        
//    }
//    
//    @Test
//    public void testSearchUsersByCategory() {
//        for (User user : usersForTesting) {
//            testDao.createUser(user);
//        }
//        for (User user : similarUsers) {
//            testDao.createUser(user);
//        }
//
//        Assert.assertEquals(usersForTesting[0].getTags(), testDao.getUserById(usersForTesting[0].getId()).getTags());
//        Assert.assertEquals(usersForTesting[0].getTitle(), testDao.getUserById(usersForTesting[0].getId()).getTitle());
//        Assert.assertEquals(usersForTesting[0].getId(), testDao.getUserById(usersForTesting[0].getId()).getId());
//        Assert.assertEquals(usersForTesting[0].getContent(), testDao.getUserById(usersForTesting[0].getId()).getContent());
//        Assert.assertEquals(usersForTesting[0].getAuthor(), testDao.getUserById(usersForTesting[0].getId()).getAuthor());
//        Assert.assertEquals(usersForTesting[0].getDate(), testDao.getUserById(usersForTesting[0].getId()).getDate());
//        Assert.assertEquals(usersForTesting[0].getCategory(), testDao.getUserById(usersForTesting[0].getId()).getCategory());
//
//        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
//        String testSearch = usersForTesting[0].getCategory();
//        
//        List<User> testSearchResults = testDao.searchUsersByCategory(testSearch);
//        
//        String testSearchPublished = usersForTesting[5].getCategory();
//        List<User> testSearchResultsPub = testDao.searchUsersByCategory(testSearchPublished);
//        
//        Assert.assertEquals(0, testSearchResults.size());
//        
//        Assert.assertEquals(3, testSearchResultsPub.size());
//        
//        Assert.assertTrue("if true, results contain category", testSearchResultsPub.contains(usersForTesting[14]));
//        
//        
//    }
//    
//    @Test
//    public void testSearchUsersByAuthor() {
//        for (User user : usersForTesting) {
//            testDao.createUser(user);
//        }
//        for (User user : similarUsers) {
//            testDao.createUser(user);
//        }
//
//        Assert.assertEquals(usersForTesting[0].getTags(), testDao.getUserById(usersForTesting[0].getId()).getTags());
//        Assert.assertEquals(usersForTesting[0].getTitle(), testDao.getUserById(usersForTesting[0].getId()).getTitle());
//        Assert.assertEquals(usersForTesting[0].getId(), testDao.getUserById(usersForTesting[0].getId()).getId());
//        Assert.assertEquals(usersForTesting[0].getContent(), testDao.getUserById(usersForTesting[0].getId()).getContent());
//        Assert.assertEquals(usersForTesting[0].getAuthor(), testDao.getUserById(usersForTesting[0].getId()).getAuthor());
//        Assert.assertEquals(usersForTesting[0].getDate(), testDao.getUserById(usersForTesting[0].getId()).getDate());
//        Assert.assertEquals(usersForTesting[0].getCategory(), testDao.getUserById(usersForTesting[0].getId()).getCategory());
//
//        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
//        String testSearch = usersForTesting[0].getAuthor();
//        
//        List<User> testSearchResults = testDao.searchUsersByAuthor(testSearch);
//        
//       
//        Assert.assertEquals(4, testSearchResults.size());
//        
//        Assert.assertTrue(testSearchResults.contains(similarUsers[1]));
//        
//    }
//    
//    @Test
//    public void testSearchUsersByTags() {
//        
//        for (User user : usersForTesting) {
//            
//            user.getTags().add("#ouch");
//            user.getTags().add("#winning");
//            user.getTags().add("#dood");
//            testDao.createUser(user);
//        }
//        for (User user : similarUsers) {
//            user.getTags().add("#dank");
//            user.getTags().add("#royal");
//            user.getTags().add("#ouch");
//            testDao.createUser(user);
//        }
//
//        Assert.assertEquals(usersForTesting[0].getTags(), testDao.getUserById(usersForTesting[0].getId()).getTags());
//        Assert.assertEquals(usersForTesting[0].getTitle(), testDao.getUserById(usersForTesting[0].getId()).getTitle());
//        Assert.assertEquals(usersForTesting[0].getId(), testDao.getUserById(usersForTesting[0].getId()).getId());
//        Assert.assertEquals(usersForTesting[0].getContent(), testDao.getUserById(usersForTesting[0].getId()).getContent());
//        Assert.assertEquals(usersForTesting[0].getAuthor(), testDao.getUserById(usersForTesting[0].getId()).getAuthor());
//        Assert.assertEquals(usersForTesting[0].getDate(), testDao.getUserById(usersForTesting[0].getId()).getDate());
//        Assert.assertEquals(usersForTesting[0].getCategory(), testDao.getUserById(usersForTesting[0].getId()).getCategory());
//
//        junit.framework.Assert.assertNotNull("List of all users should not be null.", testDao.getAllUsers());
//        String testSearch = usersForTesting[0].getTags().get(0);
//        
//        List<User> testSearchResults = testDao.searchUsersByTags(testSearch);
//        
//       
//        Assert.assertEquals(14, testSearchResults.size());
//        
////        Assert.assertTrue(testSearchResults.contains(similarUsers[1]));
//        
//    }
//   
}
