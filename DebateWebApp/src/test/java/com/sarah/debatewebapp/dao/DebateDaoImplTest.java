/*
 *  Copyright 2017 SarahBoka
 */
package com.sarah.debatewebapp.dao;

import com.sarah.debatewebapp.dto.Debate;
import com.sarah.debatewebapp.dto.Rebuttal;
import java.util.List;
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
public class DebateDaoImplTest {
    
    private DebateDao testDao;
    
    public DebateDaoImplTest() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext factory = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        testDao = factory.getBean("debateJdbcDao", DebateDao.class);
        JdbcTemplate cleaner = factory.getBean("jdbcTemplateBean", JdbcTemplate.class);
        cleaner.execute("DELETE FROM debates WHERE 1=1");
        cleaner.execute("DELETE FROM rebuttals WHERE 1=1");
        cleaner.execute("DELETE FROM users WHERE 1=1");
        cleaner.execute("DELETE FROM categories WHERE 1=1");
    }
    
    Debate[] debatesForTesting = {
        new Debate("Technology has allowed bloody hongis to participate in the global conversation of sweet mates.", "After the Tui is skived off, you add all the pearler quater-acre patches to the pavlova you've got yourself a meal. ", "debatinNotHatin", "music", "2017-10-05", true),
        new Debate("Elliot Smith is better than Bob Dylan.", "Mean while, in Shortland Street, Cardigan Bay and a Taniwha were up to no good","2truDebator", "music", "2017-03-27", true),
        new Debate("Going vegan is substantially better for the environment", "The random force of his rooting was on par with Uncle Bully's wicked L&P.","mdb8r", "ethics", "2017-02-03", true),
        new Debate("Peter Singer is one of the greatest thinkers of our time.", "Put the jug on will you bro, all these fully sick tomato sauces can wait till later.", "snowOwl22", "philosophy", "2017-06-29", true),
        new Debate("Chron is better than dabs.", "Sup bro! Where's the chips bro, this stoked seabed is as sweet as as a same same but different", "mdb8r", "marijuana", "2017-11-20", false),
        new Debate("Excessive advertisments are hurting the American mind.", "The first prize for skiving off goes to...", "snowOwl22", "politics", "2015-01-21", false),
        new Debate("Capitalism is actually pretty inefficient.", "Fred Dagg and his outrageously awesome Monopoly", "sawadeeka", "politics", "2013-12-13", true),
        new Debate("New Zealand version with Queen Street and stuff", "The naff force of his playing rugby was on par with the Armed Offenders Squad's rough as guts stubbies.", "mdb8r", "cats", "2014-07-11", true),
        new Debate("Every time I see those heaps good Grandpa's slippers", "The next Generation of hard case sad guys have already munted over at Pack n' Save.", "cheesinForTheWeekend", "history", "2016-02-15", false),
        new Debate("Bro, cookie times are really beached as good with buzzy lengths of number 8 wire, aye.", "Put the jug on will you bro, all these mean as toasted sandwichs can wait till later.", "cheesinForTheWeekend", "cats", "2017-12-27", true),
        new Debate("Rhys Darby was packing a sad when the stuffed cooking up.", "some uni student is just Manus Morissette in disguise, to find the true meaning of life, one must start pashing with the jersey, mate.", "debatinNotHatin", "history", "2017-09-01", true),
        new Debate("Hairy Maclary from Donaldson's Dairy and his hard yakka weka.", " cookie times are really beached as good with buzzy lengths of number 8 wire, aye. You have no idea how rip-off our mint Edmonds Cook Books were aye.", "sawadeeka", "politics", "2017-06-14", true)
    };
    
    Debate[] duplicateDebates = {
        new Debate("Technology has allowed bloody hongis to participate in the global conversation of sweet mates.", "After the Tui is skived off, you add all the pearler quater-acre patches to the pavlova you've got yourself a meal. ", "debatinNotHatin", "music", "2017-10-05", true),
        new Debate("Elliot Smith is better than Bob Dylan.", "Mean while, in Shortland Street, Cardigan Bay and a Taniwha were up to no good","2truDebator", "music", "2017-03-27", true),
        new Debate("Going vegan is substantially better for the environment", "The random force of his rooting was on par with Uncle Bully's wicked L&P.","mdb8r", "ethics", "2017-02-03", true),
        new Debate("Peter Singer is one of the greatest thinkers of our time.", "Put the jug on will you bro, all these fully sick tomato sauces can wait till later.", "snowOwl22", "philosophy", "2017-06-29", true),
        new Debate("Chron is better than dabs.", "Sup bro! Where's the chips bro, this stoked seabed is as sweet as as a same same but different", "mdb8r", "marijuana", "2017-11-20", false),
        new Debate("Excessive advertisments are hurting the American mind.", "The first prize for skiving off goes to...", "snowOwl22", "politics", "2015-01-21", false),
        new Debate("Capitalism is actually pretty inefficient.", "Fred Dagg and his outrageously awesome Monopoly", "sawadeeka", "politics", "2013-12-13", true),
        new Debate("New Zealand version with Queen Street and stuff", "The naff force of his playing rugby was on par with the Armed Offenders Squad's rough as guts stubbies.", "mdb8r", "cats", "2014-07-11", true),
        new Debate("Every time I see those heaps good Grandpa's slippers", "The next Generation of hard case sad guys have already munted over at Pack n' Save.", "cheesinForTheWeekend", "history", "2016-02-15", false),
        new Debate("Bro, cookie times are really beached as good with buzzy lengths of number 8 wire, aye.", "Put the jug on will you bro, all these mean as toasted sandwichs can wait till later.", "cheesinForTheWeekend", "cats", "2017-12-27", true),
        new Debate("Rhys Darby was packing a sad when the stuffed cooking up.", "some uni student is just Manus Morissette in disguise, to find the true meaning of life, one must start pashing with the jersey, mate.", "debatinNotHatin", "history", "2017-09-01", true),
        new Debate("Hairy Maclary from Donaldson's Dairy and his hard yakka weka.", " cookie times are really beached as good with buzzy lengths of number 8 wire, aye. You have no idea how rip-off our mint Edmonds Cook Books were aye.", "sawadeeka", "politics", "2017-06-14", true)
    };
    
    Debate[] similarDebates = {
        new Debate("Technology hasn't allowed blood hongis to participate in the global convos of sweet mates.", "After the Tui is skived off, you add all the pearler quater-acre patches to the pavlova you've got yourself a meal. ", "debatinNotHatin", "music", "2017-10-05", true),
        new Debate("Elliot Smith is better than Bob Dylan.", "Mean while, in Shortland Street, Cardigan Bay and a Taniwha were up to no good","2truDebator", "music", "2017-04-27", true),
        new Debate("Going vegan is substantially better for the environment", "The random force of his rooting was on par with Uncle Bully's wicked L&P.","mdb8r", "ethics", "2017-02-12", true),
        new Debate("Peter Singer is one of the greatest thinkers of our time.", "Put the jug on will you bro, all these fully sick tomato sauces can wait till later.", "snowOwl22", "music", "2017-06-29", true),
        new Debate("Chron is better than dabs.", "Sup bro! Where's the chips bro, this stoked seabed is as sweet as as a same same but different", "2truDebator", "marijuana", "2017-11-20", false),
        new Debate("Excessive advertisments are hurting the American mind.", "The first prize for skiving off goes to.", "snowOwl22", "politics", "2017-01-21", false),
        new Debate("Crapitalism is actually pretty inefficient.", "Fred Dagg and his outrageously awesome Monopoly.", "sawadeeka", "philosophy", "2013-12-13", true),
        new Debate("New Zealand version with Queen Street and stuff.", "The naff force of his playing rugby was on par with the Armed Offenders Squad's rough as guts stubbies.", "mdb8r", "cats", "2014-07-11", true),
        new Debate("Every time I see those heaps good Grandpa's slippers", "The next Generation of hard case sad guys have already munted over at Pack n' Save.", "debatinNotHatin", "history", "2016-02-15", false),
        new Debate("Bro, cookie times are really beached as good with buzzy lengths of number 8 wire, aye.", "Put the jug on will you bro, all these mean as toasted sandwichs can wait till later.", "cheesinForTheWeekend", "cats", "2017-10-27", true),
        new Debate("Rhys Darby was packing a sad when the stuffed cooking up.", "some uni student is just Manus Morissette in disguise, to find the true meaning of life, one must start pashing with the jersey, mate.", "sawadeeka", "history", "2017-09-01", true),
        new Debate("Hairy Maclary from Donaldson's Dairy and his hard yakka weka.", " cookie times are really beached as good with buzzy lengths of number 8 wire, aye. You have no idea how rip-off our mint Edmonds Cook Books were aye.", "sawadeeka", "music", "2017-06-14", true)
    };
    
    @Test
    public void addOneToEmptyDaoTest(){
        Debate testDebate = new Debate(1, "Terrrrrrst Resolution.", "Some stuff in this test content.","2truDebator", "music", "2016-06-06", true);
        testDao.createDebate(testDebate);
        Debate testDebateAgain = testDao.getDebateById(testDebate.getId());
    }
    /**
     * Test of createDebate method, of class DebateDaoImpl.
     */
    @Test
    public void testCreateDebate() {
        System.out.println("createDebate");
        Debate debate = null;
        DebateDaoImpl instance = new DebateDaoImpl();
        Debate expResult = null;
        Debate result = instance.createDebate(debate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRebuttal method, of class DebateDaoImpl.
     */
    @Test
    public void testCreateRebuttal() {
        System.out.println("createRebuttal");
        Rebuttal rebuttal = null;
        DebateDaoImpl instance = new DebateDaoImpl();
        Rebuttal expResult = null;
        Rebuttal result = instance.createRebuttal(rebuttal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDebate method, of class DebateDaoImpl.
     */
    @Test
    public void testUpdateDebate() {
        System.out.println("updateDebate");
        Debate debate = null;
        DebateDaoImpl instance = new DebateDaoImpl();
        instance.updateDebate(debate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllCategories method, of class DebateDaoImpl.
     */
    @Test
    public void testGetAllCategories() {
        System.out.println("getAllCategories");
        DebateDaoImpl instance = new DebateDaoImpl();
        List<String> expResult = null;
        List<String> result = instance.getAllCategories();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteDebate method, of class DebateDaoImpl.
     */
    @Test
    public void testDeleteDebate() {
        System.out.println("deleteDebate");
        int id = 0;
        DebateDaoImpl instance = new DebateDaoImpl();
        instance.deleteDebate(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDebateById method, of class DebateDaoImpl.
     */
    @Test
    public void testGetDebateById() {
        System.out.println("getDebateById");
        int id = 0;
        DebateDaoImpl instance = new DebateDaoImpl();
        Debate expResult = null;
        Debate result = instance.getDebateById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPublishedDebates method, of class DebateDaoImpl.
     */
    @Test
    public void testGetAllPublishedDebates() {
        System.out.println("getAllPublishedDebates");
        DebateDaoImpl instance = new DebateDaoImpl();
        List<Debate> expResult = null;
        List<Debate> result = instance.getAllPublishedDebates();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
