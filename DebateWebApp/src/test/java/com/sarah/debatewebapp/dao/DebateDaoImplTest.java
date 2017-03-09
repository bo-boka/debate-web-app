/*
 *  Copyright 2017 SarahBoka
 */
package com.sarah.debatewebapp.dao;

import com.sarah.debatewebapp.dto.Debate;
import java.util.ArrayList;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
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
//        cleaner.execute("DELETE FROM categories WHERE 1=1");
//        cleaner.execute("DELETE FROM rebuttals WHERE 1=1");

    }
    
    Debate[] introDebatesForTesting = {
        new Debate(1, "Technology has allowed bloody hongis to participate in the global conversation of sweet mates.", "After the Tui is skived off, you add all the pearler quater-acre patches to the pavlova you've got yourself a meal.", "debatinNotHatin", "music", "2017-01-21", true),
        new Debate(2, "Elliot Smith is better than Bob Dylan.", "Mean while, in Shortland Street, Cardigan Bay and a Taniwha were up to no good","2truDebator", "music", "2017-03-27", true),
        new Debate(3, "Going vegan is substantially better for the environment", "The random force of his rooting was on par with Uncle Bully's wicked L&P.","mdb8r", "ethics", "2017-02-03", true),
        new Debate(4, "Peter Singer is one of the greatest thinkers of our time.", "Put the jug on will you bro, all these fully sick tomato sauces can wait till later.", "snowOwl22", "philosophy", "2017-06-29", true),
        new Debate(5, "Chron is better than dabs.", "Sup bro! Where's the chips bro, this stoked seabed is as sweet as as a same same but different", "mdb8r", "marijuana", "2017-11-20", false),
        new Debate(6, "Excessive advertisments are hurting the American mind.", "The first prize for skiving off goes to...", "snowOwl22", "politics", "2015-01-21", false),
        new Debate(7, "Capitalism is actually pretty inefficient.", "Fred Dagg and his outrageously awesome Monopoly", "sawadeeka", "politics", "2013-12-13", true),
        new Debate(8, "New Zealand version with Queen Street and stuff", "The naff force of his playing rugby was on par with the Armed Offenders Squad's rough as guts stubbies.", "mdb8r", "cats", "2014-07-11", true),
        new Debate(9, "Every time I see those heaps good Grandpa's slippers", "The next Generation of hard case sad guys have already munted over at Pack n' Save.", "cheesinForTheWeekend", "history", "2016-02-15", false),
        new Debate(10, "Bro, cookie times are really beached as good with buzzy lengths of number 8 wire, aye.", "Put the jug on will you bro, all these mean as toasted sandwichs can wait till later.", "cheesinForTheWeekend", "cats", "2017-12-27", true),
        new Debate(11, "Rhys Darby was packing a sad when the stuffed cooking up.", "some uni student is just Manus Morissette in disguise, to find the true meaning of life, one must start pashing with the jersey, mate.", "debatinNotHatin", "history", "2017-09-01", true),
        new Debate(12, "Hairy Maclary from Donaldson's Dairy and his hard yakka weka.", " cookie times are really beached as good with buzzy lengths of number 8 wire, aye. You have no idea how rip-off our mint Edmonds Cook Books were aye.", "sawadeeka", "politics", "2017-06-14", true)
    };
    
    Debate[] duplicateIntroDebates = {
        new Debate(1, "Technology has allowed bloody hongis to participate in the global conversation of sweet mates.", "After the Tui is skived off, you add all the pearler quater-acre patches to the pavlova you've got yourself a meal. ", "debatinNotHatin", "music", "2017-10-05", true),
        new Debate(2, "Elliot Smith is better than Bob Dylan.", "Mean while, in Shortland Street, Cardigan Bay and a Taniwha were up to no good","2truDebator", "music", "2017-03-27", true),
        new Debate(3, "Going vegan is substantially better for the environment", "The random force of his rooting was on par with Uncle Bully's wicked L&P.","mdb8r", "ethics", "2017-02-03", true),
        new Debate(4, "Peter Singer is one of the greatest thinkers of our time.", "Put the jug on will you bro, all these fully sick tomato sauces can wait till later.", "snowOwl22", "philosophy", "2017-06-29", true),
        new Debate(5, "Chron is better than dabs.", "Sup bro! Where's the chips bro, this stoked seabed is as sweet as as a same same but different", "mdb8r", "marijuana", "2017-11-20", false),
        new Debate(6, "Excessive advertisments are hurting the American mind.", "The first prize for skiving off goes to...", "snowOwl22", "politics", "2015-01-21", false),
        new Debate(7, "Capitalism is actually pretty inefficient.", "Fred Dagg and his outrageously awesome Monopoly", "sawadeeka", "politics", "2013-12-13", true),
        new Debate(8, "New Zealand version with Queen Street and stuff", "The naff force of his playing rugby was on par with the Armed Offenders Squad's rough as guts stubbies.", "mdb8r", "cats", "2014-07-11", true),
        new Debate(9, "Every time I see those heaps good Grandpa's slippers", "The next Generation of hard case sad guys have already munted over at Pack n' Save.", "cheesinForTheWeekend", "history", "2016-02-15", false),
        new Debate(10, "Bro, cookie times are really beached as good with buzzy lengths of number 8 wire, aye.", "Put the jug on will you bro, all these mean as toasted sandwichs can wait till later.", "cheesinForTheWeekend", "cats", "2017-12-27", true),
        new Debate(11, "Rhys Darby was packing a sad when the stuffed cooking up.", "some uni student is just Manus Morissette in disguise, to find the true meaning of life, one must start pashing with the jersey, mate.", "debatinNotHatin", "history", "2017-09-01", true),
        new Debate(12, "Hairy Maclary from Donaldson's Dairy and his hard yakka weka.", " cookie times are really beached as good with buzzy lengths of number 8 wire, aye. You have no idea how rip-off our mint Edmonds Cook Books were aye.", "sawadeeka", "politics", "2017-06-14", true)
    };
    
    Debate[] similarIntroDebates = {
        new Debate(1, "Technology hasn't allowed blood hongis to participate in the global convos of sweet mates.", "After the Tui is skived off, you add all the pearler quater-acre patches to the pavlova you've got yourself a meal. ", "debatinNotHatin", "music", "2017-10-05", true),
        new Debate(2, "Elliot Smith is better than Bob Dylan.", "Mean while, in Shortland Street, Cardigan Bay and a Taniwha were up to no good","2truDebator", "music", "2017-04-27", true),
        new Debate(3, "Going vegan is substantially better for the environment", "The random force of his rooting was on par with Uncle Bully's wicked L&P.","mdb8r", "ethics", "2017-02-12", true),
        new Debate(4, "Peter Singer is one of the greatest thinkers of our time.", "Put the jug on will you bro, all these fully sick tomato sauces can wait till later.", "snowOwl22", "music", "2017-06-29", true),
        new Debate(5, "Chron is better than dabs.", "Sup bro! Where's the chips bro, this stoked seabed is as sweet as as a same same but different", "2truDebator", "marijuana", "2017-11-20", false),
        new Debate(6, "Excessive advertisments are hurting the American mind.", "The first prize for skiving off goes to.", "snowOwl22", "politics", "2017-01-21", false),
        new Debate(7, "Crapitalism is actually pretty inefficient.", "Fred Dagg and his outrageously awesome Monopoly.", "sawadeeka", "philosophy", "2013-12-13", true),
        new Debate(8, "New Zealand version with Queen Street and stuff.", "The naff force of his playing rugby was on par with the Armed Offenders Squad's rough as guts stubbies.", "mdb8r", "cats", "2014-07-11", true),
        new Debate(9, "Every time I see those heaps good Grandpa's slippers", "The next Generation of hard case sad guys have already munted over at Pack n' Save.", "debatinNotHatin", "history", "2016-02-15", false),
        new Debate(10, "Bro, cookie times are really beached as good with buzzy lengths of number 8 wire, aye.", "Put the jug on will you bro, all these mean as toasted sandwichs can wait till later.", "cheesinForTheWeekend", "cats", "2017-10-27", true),
        new Debate(11, "Rhys Darby was packing a sad when the stuffed cooking up.", "some uni student is just Manus Morissette in disguise, to find the true meaning of life, one must start pashing with the jersey, mate.", "sawadeeka", "history", "2017-09-01", true),
        new Debate(12, "Hairy Maclary from Donaldson's Dairy and his hard yakka weka.", " cookie times are really beached as good with buzzy lengths of number 8 wire, aye. You have no idea how rip-off our mint Edmonds Cook Books were aye.", "sawadeeka", "music", "2017-06-14", true)
    };
    
    Debate[] engagedDebatesForTesting = {
        new Debate(1, "Technology has allowed bloody hongis to participate in the global conversation of sweet mates.", "After the Tui is skived off, you add all the pearler quater-acre patches to the pavlova you've got yourself a meal.", "live", "debatinNotHatin", "sawadeeka", 6, 3, "music", "2017-01-21",  new ArrayList<>(), true),
        new Debate(2, "Elliot Smith is better than Bob Dylan.", "Mean while, in Shortland Street, Cardigan Bay and a Taniwha were up to no good", "live", "2truDebator", "mdb8r", 6, 10, "music", "2017-03-27", new ArrayList<>(), true),
        new Debate(3, "Going vegan is substantially better for the environment", "The random force of his rooting was on par with Uncle Bully's wicked L&P.", "wash", "mdb8r", "snowOwl22", 4, 4, "ethics", "2017-02-03", new ArrayList<>(), true),
        new Debate(4, "Peter Singer is one of the greatest thinkers of our time.", "Put the jug on will you bro, all these fully sick tomato sauces can wait till later.", "conWon", "snowOwl22", "mdb8r", 5, 7, "philosophy", "2017-06-29", new ArrayList<>(), true),
        new Debate(5, "Chron is better than dabs.", "Sup bro! Where's the chips bro, this stoked seabed is as sweet as as a same same but different", "live", "mdb8r", "debatinNotHatin", 0, 0, "marijuana", "2017-11-20", new ArrayList<>(), false),
        new Debate(6, "Excessive advertisments are hurting the American mind.", "The first prize for skiving off goes to...", "live","cheesinForTheWeekend", "snowOwl22", 0, 0, "politics", "2015-01-21", new ArrayList<>(), false),
        new Debate(7, "Capitalism is actually pretty inefficient.", "Fred Dagg and his outrageously awesome Monopoly", "proWon", "sawadeeka", "snowOwl22", 11, 8, "politics", "2013-12-13", new ArrayList<>(), true),
    };
    
    Debate[] duplicateEngagedDebates = {
        new Debate(1, "Technology has allowed bloody hongis to participate in the global conversation of sweet mates.", "After the Tui is skived off, you add all the pearler quater-acre patches to the pavlova you've got yourself a meal.", "live", "debatinNotHatin", "sawadeeka", 6, 3, "music", "2017-01-21",  new ArrayList<>(), true),
        new Debate(2, "Elliot Smith is better than Bob Dylan.", "Mean while, in Shortland Street, Cardigan Bay and a Taniwha were up to no good", "live", "2truDebator", "mdb8r", 6, 10, "music", "2017-03-27", new ArrayList<>(), true),
        new Debate(3, "Going vegan is substantially better for the environment", "The random force of his rooting was on par with Uncle Bully's wicked L&P.", "wash", "mdb8r", "snowOwl22", 4, 4, "ethics", "2017-02-03", new ArrayList<>(), true),
        new Debate(4, "Peter Singer is one of the greatest thinkers of our time.", "Put the jug on will you bro, all these fully sick tomato sauces can wait till later.", "conWon", "snowOwl22", "mdb8r", 5, 7, "philosophy", "2017-06-29", new ArrayList<>(), true),
        new Debate(5, "Chron is better than dabs.", "Sup bro! Where's the chips bro, this stoked seabed is as sweet as as a same same but different", "live", "mdb8r", "debatinNotHatin", 0, 0, "marijuana", "2017-11-20", new ArrayList<>(), false),
        new Debate(6, "Excessive advertisments are hurting the American mind.", "The first prize for skiving off goes to...", "live","cheesinForTheWeekend", "snowOwl22", 0, 0, "politics", "2015-01-21", new ArrayList<>(), false),
        new Debate(7, "Capitalism is actually pretty inefficient.", "Fred Dagg and his outrageously awesome Monopoly", "proWon", "sawadeeka", "snowOwl22", 11, 8, "politics", "2013-12-13", new ArrayList<>(), true),
    };
    
    Debate[] similarEngagedDebates = {
        new Debate(1, "Technology has allowed hongis to participate in the global conversation of sweet mates.", "After the Tui is skived off, you add all the pearler quater-acre patches to the pavlova you've got yourself a meal.", "live", "debatinNotHatin", "sawadeeka", 6, 3, "music", "2017-01-21",  new ArrayList<>(), true),
        new Debate(2, "Elliot Smith is better than Bob Dylan.", "Mean while, in Shortland Street, Cardigan Bay and a Taniwha were up to no good", "conWon", "2truDebator", "mdb8r", 6, 10, "music", "2017-03-27", new ArrayList<>(), true),
        new Debate(3, "Going vegan is substantially better for the environment", "The random force of his rooting was on par with Uncle Bully's wicked L&P.", "wash", "sawadeeka", "snowOwl22", 4, 4, "ethics", "2017-02-03", new ArrayList<>(), true),
        new Debate(4, "Peter Singer is one of the greatest thinkers of our time.", "Put the jug on will you bro, all these fully sick tomato sauces can wait till later", "conWon", "snowOwl22", "mdb8r", 4, 7, "philosophy", "2017-06-29", new ArrayList<>(), true),
        new Debate(5, "Chron is better than dabs.", "Sup bro! Where's the chips bro, this stoked seabed is as sweet as as a same same but different", "live", "mdb8r", "debatinNotHatin", 0, 0, "politics", "2017-11-20", new ArrayList<>(), false),
        new Debate(6, "Excessive advertisments are hurting the American mind.", "The first prize for skiving off goes to...", "live","cheesinForTheWeekend", "snowOwl22", 0, 0, "politics", "2015-01-22", new ArrayList<>(), false),
        new Debate(7, "Capitalism is actually pretty inefficient.", "Fred Dagg and his outrageously awesome Monopoly", "proWon", "sawadeeka", "snowOwl22", 11, 9, "politics", "2013-12-13", new ArrayList<>(), true),
    };
    
    @Test
    public void addOneToEmptyDaoTest(){
        Debate testDebate = new Debate(1, "Terrrrrrst Resolution.", "Some stuff in this test content.", "2truDebator", "music", "2017-02-28", true);
        testDao.createDebate(testDebate);
        Debate testDebateRetrieve = testDao.getDebateById(testDebate.getId());
        Assert.assertEquals(testDebate.getId(), testDebateRetrieve.getId());
        Assert.assertEquals(testDebate.getResolution(), testDebateRetrieve.getResolution());
        Assert.assertEquals(testDebate.getContent(), testDebateRetrieve.getContent());
        Assert.assertEquals(testDebate.getAffirmativeUser(), testDebateRetrieve.getAffirmativeUser());
        Assert.assertEquals(testDebate.getCategory(), testDebateRetrieve.getCategory());

        junit.framework.Assert.assertEquals("Debate stored matches Debate retrieved.", 1, testDao.getAllDebates().size());
    }
    
    @Test
    public void testAgainstEmptyDao(){
        junit.framework.Assert.assertNull("Asking for a nonexistent debate should return null", testDao.getDebateById(123));
        junit.framework.Assert.assertNotNull("List all debates shouldn't be null.", testDao.getAllDebates());
        junit.framework.Assert.assertEquals("Expectedd debate count of all published debates is nonzero against empty dao.", 0, testDao.getAllDebates().size());
    }
    
    @Test
    public void testCreateDebate(){
        Debate testDebate = introDebatesForTesting[0];
        Debate testCreateDebate = testDao.createDebate(testDebate);
        
        Assert.assertEquals(testDebate.getId(), testCreateDebate.getId());
        Assert.assertEquals(testDebate.getResolution(), testCreateDebate.getResolution());
        Assert.assertEquals(testDebate.getContent(), testCreateDebate.getContent());
        Assert.assertEquals(testDebate.getAffirmativeUser(), testCreateDebate.getAffirmativeUser());
        Assert.assertEquals(testDebate.getCategory(), testCreateDebate.getCategory());
        Assert.assertEquals(testDebate.getDate(), testCreateDebate.getDate());
        Assert.assertEquals(testDebate.isPublished(), testCreateDebate.isPublished());
        junit.framework.Assert.assertEquals("Returned debate does not match expected", testDebate, testCreateDebate);
        junit.framework.Assert.assertNotNull("List of all debates should not be null", testDao.getAllDebates());
        junit.framework.Assert.assertEquals("Expected debate count deos not match after adding one.", 1, testDao.getAllDebates().size());
    }
    
    @Test
    public void testUpdateDebate(){
        
        testDao.createDebate(engagedDebatesForTesting[0]);
        similarEngagedDebates[0].setId(engagedDebatesForTesting[0].getId());
        testDao.updateDebate(similarEngagedDebates[0]);
        
        Assert.assertEquals(similarEngagedDebates[0].getResolution(), testDao.getDebateById(similarEngagedDebates[0].getId()).getResolution());
        Assert.assertEquals(similarEngagedDebates[0].getContent(), testDao.getDebateById(similarEngagedDebates[0].getId()).getContent());
        Assert.assertEquals(similarEngagedDebates[0].getStatus(), testDao.getDebateById(similarEngagedDebates[0].getId()).getStatus());
        Assert.assertEquals(similarEngagedDebates[0].getAffirmativeUser(), testDao.getDebateById(similarEngagedDebates[0].getId()).getAffirmativeUser());
        Assert.assertEquals(similarEngagedDebates[0].getNegativeUser(), testDao.getDebateById(similarEngagedDebates[0].getId()).getNegativeUser());
        Assert.assertEquals(similarEngagedDebates[0].getCategory(), testDao.getDebateById(similarEngagedDebates[0].getId()).getCategory());
        Assert.assertEquals(similarEngagedDebates[0].getDate(), testDao.getDebateById(similarEngagedDebates[0].getId()).getDate());
        //debates doesn't match because pro/conVotes aren't entered and dates don't change
//        junit.framework.Assert.assertEquals("Updated debate does not match expected", similarEngagedDebates[0], testDao.getDebateById(similarEngagedDebates[0].getId()));
//        junit.framework.Assert.assertTrue("Returned debate in getAllDebates does not match expected.", testDao.getAllDebates().contains(similarEngagedDebates[0]));
        junit.framework.Assert.assertNotNull("List of all debates should not be null.", testDao.getAllDebates());
        junit.framework.Assert.assertEquals("Expected debate count of 'all debates' does not match after replacing one debate.", 1, testDao.getAllDebates().size());
    }
    
    @Test
    public void testAddMultipleDebates(){
        for (Debate debate : introDebatesForTesting) {
            testDao.createDebate(debate);
        }
        junit.framework.Assert.assertNotNull("List of all debates should not be null.", testDao.getAllDebates());
        junit.framework.Assert.assertEquals("Expected debate count of 'all debates' does not match after adding several debates.",
                introDebatesForTesting.length, testDao.getAllDebates().size());
        for (Debate d : introDebatesForTesting){
            Assert.assertEquals(d.getResolution(), testDao.getDebateById(d.getId()).getResolution());
            Assert.assertEquals(d.getContent(), testDao.getDebateById(d.getId()).getContent());
            Assert.assertEquals(d.getAffirmativeUser(), testDao.getDebateById(d.getId()).getAffirmativeUser());
            Assert.assertEquals(d.getStatus(), testDao.getDebateById(d.getId()).getStatus());
            Assert.assertEquals(d.getCategory(), testDao.getDebateById(d.getId()).getCategory());
            
        }
    }
    
    @Test
    public void testUpdateMultipleDebates(){
        for (Debate debate : engagedDebatesForTesting) {
            testDao.createDebate(debate);
        }
        for(Debate d : similarEngagedDebates){
            testDao.updateDebate(d);
        }
        junit.framework.Assert.assertNotNull("List of debates shouldn't be null.", testDao.getAllDebates());
        junit.framework.Assert.assertEquals("Expected debate count does not match after updated debates.", engagedDebatesForTesting.length, testDao.getAllDebates().size());
        for (Debate d : engagedDebatesForTesting){
            Assert.assertEquals(d.getResolution(), testDao.getDebateById(d.getId()).getResolution());
            Assert.assertEquals(d.getContent(), testDao.getDebateById(d.getId()).getContent());
            Assert.assertEquals(d.getAffirmativeUser(), testDao.getDebateById(d.getId()).getAffirmativeUser());
            Assert.assertEquals(d.getCategory(), testDao.getDebateById(d.getId()).getCategory());
        }
    }
    
    @Test
    public void testAddSimilarDebates(){
        for (Debate debate : engagedDebatesForTesting) {
            testDao.createDebate(debate);
        }
        for (Debate debate : similarEngagedDebates) {
            testDao.createDebate(debate);
        }
        junit.framework.Assert.assertNotNull("List of all debates should not be null.", testDao.getAllDebates());
        junit.framework.Assert.assertEquals("Expected debate count does not match after adding several similar debates.",
                engagedDebatesForTesting.length + similarEngagedDebates.length, testDao.getAllDebates().size());
    }
    
    @Test
    public void testAddAndRemoveOneDebate(){
        
        testDao.createDebate(introDebatesForTesting[0]);
        testDao.deleteDebate(introDebatesForTesting[0].getId());
        junit.framework.Assert.assertNotNull("List of all debates should not be null.", testDao.getAllDebates());
        junit.framework.Assert.assertNull("Get debate should return null after being removed.", testDao.getDebateById(introDebatesForTesting[0].getId()));
        junit.framework.Assert.assertEquals("Expected debate count of 'all debates' should be zero when adding/removing a single debate.", 0, testDao.getAllDebates().size());
    }
    
    @Test
    public void testAddAndRemoveDebateTwice() {
        testDao.createDebate(introDebatesForTesting[0]);
        testDao.deleteDebate(introDebatesForTesting[0].getId());
        testDao.deleteDebate(introDebatesForTesting[0].getId());

        junit.framework.Assert.assertNotNull("List of all debates should not be null.", testDao.getAllDebates());
        junit.framework.Assert.assertNull("Debate should return null after being removed.", testDao.getDebateById(introDebatesForTesting[0].getId()));
        junit.framework.Assert.assertEquals("Expected debate count of 'all debates' should be zero when adding/removing a single debate twice.", 0, testDao.getAllDebates().size());
    }
    
   
//    @Test
//    public void testCreateRebuttal() {
//        System.out.println("createRebuttal");
//        Rebuttal rebuttal = null;
//        DebateDaoImpl instance = new DebateDaoImpl();
//        Rebuttal expResult = null;
//        Rebuttal result = instance.createRebuttal(rebuttal);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of getAllCategories method, of class DebateDaoImpl.
//     */
//    @Test
//    public void testGetAllCategories() {
//        System.out.println("getAllCategories");
//        DebateDaoImpl instance = new DebateDaoImpl();
//        List<String> expResult = null;
//        List<String> result = instance.getAllCategories();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteDebate method, of class DebateDaoImpl.
//     */
//    @Test
//    public void testDeleteDebate() {
//        System.out.println("deleteDebate");
//        int id = 0;
//        DebateDaoImpl instance = new DebateDaoImpl();
//        instance.deleteDebate(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of getAllPublishedDebates method, of class DebateDaoImpl.
//     */
//    @Test
//    public void testGetAllPublishedDebates() {
//        System.out.println("getAllPublishedDebates");
//        DebateDaoImpl instance = new DebateDaoImpl();
//        List<Debate> expResult = null;
//        List<Debate> result = instance.getAllPublishedDebates();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
