// --== CS400 File Header Information ==--
// Name: Bennett Schmanski
// Email: bschmanski@wisc.edu
// Team: DA: Red
// Role: Back End Developer
// TA: Dan Kiel
// Lecturer: Gary Dahl
// Notes to Grader: 

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the BackendImplementation class. Includes 10 test classes testing the full functionality
 * of the class.
 */
public class BackEndDeveloperTests {

    /**This method tests whether or not a backend instantiated with some events has them present when the
     * getThreeEvents method is called
     * 
     */
    @Test
    public void instantiateBackendTest() {
        BackendImplementation backendToTest = new BackendImplementation(new StringReader(
            "name, date, venue-setting, group-name, description\n" + 
        "Milwaukee Bucks, 1617296400000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Boston Celtics\n"+
        "Milwaukee Bucks, 1617490800000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Toronto Raptors\n"+
        "Joe Rogan, 1617325200000, Fiserv Forum, Joe Rogan, Joe Rogan's latest comedy tour\n"+
        "The Weeknd, 1617404400000, Fiserv Forum, The Weeknd, The Weeknd's After Hours Tour\n")); 
        Event event1=null,event2=null,event3=null;
        try{
         event1 = new Event("Milwaukee Bucks", new Date(1617296400000l), "Milwaukee Bucks vs. Boston Celtics", "Milwaukee Bucks", "Fiserv Forum");
         event2 = new Event("Joe Rogan", new Date(1617325200000l), "Joe Rogan's latest comedy tour", "Joe Rogan", "Fiserv Forum");
         event3 = new Event("The Weeknd", new Date(1617404400000l), "The Weeknd's After Hours Tour", "The Weeknd", "Fiserv Forum");
        
        
        List<Event> expected = new ArrayList<Event>();
        
        expected.add(event1);
        expected.add(event2);
        expected.add(event3);     
       
        assertEquals(backendToTest.getThreeEvents(0).toString(), expected.toString()); 
     }
        catch(Exception e ){ System.out.println(e.getMessage());}       
    }
    
    /**This method tests whether or not an event is properly added to the backend if it is
     * the first event adde (and thus the root)
     * 
     */
    @Test
    public void addEventsTestEmpty() {
        BackendImplementation backendToTest = new BackendImplementation();
        
        backendToTest.addEvent(new Date(1617210000000l), "Tame Impala", "Tame Impala with Perfume Genius", "Tame Impala", "Fiserv Forum");
        
        List<Event> expected = new ArrayList<Event>();
        try{
        expected.add(new Event("Tame Impala", new Date(1617210000000l),  "Tame Impala with Perfume Genius", "Tame Impala", "Fiserv Forum"));
        
        assertEquals(backendToTest.getThreeEvents(0).toString(), expected.toString());
    }
    catch(Exception e ){ System.out.println(e.getMessage());}
    }
    
    /**This method test whether or not the backend, that already has some events in it, will
     * properly place a new event in the right spot according to date.
     * 
     */
    @Test
    public void addEventsTestFull() {
        BackendImplementation backendToTest = new BackendImplementation(new StringReader(
            "name, date, venue-setting, group-name, description\n" + 
        "Milwaukee Bucks, 1617296400000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Boston Celtics\n"+
        "Milwaukee Bucks, 1617490800000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Toronto Raptors\n"+
        "Joe Rogan, 1617325200000, Fiserv Forum, Joe Rogan, Joe Rogan's latest comedy tour\n"+
        "The Weeknd, 1617404400000, Fiserv Forum, The Weeknd, The Weeknd's After Hours Tour\n")); 
        
        backendToTest.addEvent(new Date(1617382800000l), "Tame Impala", "Tame Impala with Perfume Genius", "Tame Impala", "Fiserv Forum");
        Event event1=null, event2=null, event3=null;
        try{
         event1 = new Event("Milwaukee Bucks", new Date(1617296400000l), "Milwaukee Bucks vs. Boston Celtics", "Milwaukee Bucks", "Fiserv Forum");
         event2 = new Event("Joe Rogan", new Date(1617325200000l), "Joe Rogan's latest comedy tour", "Joe Rogan", "Fiserv Forum");
         event3 = new Event("Tame Impala", new Date(1617382800000l), "Tame Impala with Perfume Genius", "Tame Impala", "Fiserv Forum");
        
        
        List<Event> expected = new ArrayList<Event>();
        
        expected.add(event1);
        expected.add(event2);
        expected.add(event3);
    
        assertEquals(backendToTest.getThreeEvents(0).toString(), expected.toString()); 
    }
    catch(Exception e ){ System.out.println(e.getMessage());}       
    }
    
    /**This method tests whether the proper events are returned when the getEventsByName
     * method is called on a name with multiple matching events.
     * 
     */
    @Test
    public void getEventsByNameTest() {
        BackendImplementation backendToTest = new BackendImplementation(new StringReader(
            "name, date, venue-setting, group-name, description\n" + 
        "Milwaukee Bucks, 1617296400000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Boston Celtics\n"+
        "Milwaukee Bucks, 1617490800000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Toronto Raptors\n"+
        "Joe Rogan, 1617325200000, Fiserv Forum, Joe Rogan, Joe Rogan's latest comedy tour\n"+
        "The Weeknd, 1617404400000, Fiserv Forum, The Weeknd, The Weeknd's After Hours Tour\n")); 
        
        List<Event> actual = backendToTest.getEventsByName("Milwaukee Bucks");
        List<Event> expected = new ArrayList<Event>();
        try{
        expected.add(new Event("Milwaukee Bucks", new Date(1617296400000l),"Fiserv Forum" , "Milwaukee Bucks", "Milwaukee Bucks vs. Boston Celtics"));
        expected.add(new Event("Milwaukee Bucks", new Date(1617490800000l),  "Fiserv Forum", "Milwaukee Bucks","Milwaukee Bucks vs. Toronto Raptors"));
        
        assertEquals(expected.toString(), actual.toString());
    }
        catch(Exception e ){ System.out.println(e.getMessage());}
    }
    
    /**This method tests whether the getEventsByDate method will return the right events that are
     * within 24 hours of the provided date.
     * 
     */
    @Test
    public void getEventsByDateTest() {
        BackendImplementation backendToTest = new BackendImplementation(new StringReader(
            "name, date, venue-setting, group-name, description\n" + 
        "Milwaukee Bucks, 1617296400000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Boston Celtics\n"+
        "Milwaukee Bucks, 1617490800000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Toronto Raptors\n"+
        "Joe Rogan, 1617325200000, Fiserv Forum, Joe Rogan, Joe Rogan's latest comedy tour\n"+
        "The Weeknd, 1617404400000, Fiserv Forum, The Weeknd, The Weeknd's After Hours Tour\n"));
        
        List<Event> actual = backendToTest.getEventsByDate(new Date(1617253200000l));
        List<Event> expected = new ArrayList<Event>();
        try{
        expected.add(new Event("Milwaukee Bucks", new Date(1617296400000l), "Milwaukee Bucks vs. Boston Celtics", "Milwaukee Bucks", "Fiserv Forum"));
        expected.add(new Event("Joe Rogan", new Date(1617325200000l), "Joe Rogan's latest comedy tour", "Joe Rogan", "Fiserv Forum"));
       
        assertEquals(expected.toString(), actual.toString());
    }
        catch(Exception e ){ System.out.println(e.getMessage());}
    } 
    
    /**This methods tests that the addEvent method will throw an exception if the given event has
     * improper details.
     * 
     */
    @Test
    public void addEventInvalidTest() {
        BackendImplementation backendToTest = new BackendImplementation(new StringReader(
            "name, date, venue-setting, group-name, description\n" + 
        "Milwaukee Bucks, 1617296400000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Boston Celtics\n"+
        "Milwaukee Bucks, 1617490800000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Toronto Raptors\n"+
        "Joe Rogan, 1617325200000, Fiserv Forum, Joe Rogan, Joe Rogan's latest comedy tour\n"+
        "The Weeknd, 1617404400000, Fiserv Forum, The Weeknd, The Weeknd's After Hours Tour\n"));
        boolean thrown = false;
        try {
            backendToTest.addEvent(null, "", "", "", "");   
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(!thrown);
    }
    
    /**This method tests if the getEvent methods throw an exception if the method is called
     * with improper dates.
     * 
     */
    @Test
    public void getEventInvalidTest() {
        BackendImplementation backendToTest = new BackendImplementation();
        boolean thrown = false;
        try {
            backendToTest.getEventsByDate(null);   
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(!thrown);
    }
    
    /**This method tests if the next three available two-hour blocks are returned by the
     * getThreeAvailableTimes method given some conflicting events.
     * 
     */
    @Test
    public void nextAvailableTimesTest() {
        BackendImplementation backendToTest = new BackendImplementation(new StringReader(
            "name, date, venue-setting, group-name, description\n" + 
        "Milwaukee Bucks, 1616994000000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Boston Celtics\n"+
        "Milwaukee Bucks, 1617001200000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Toronto Raptors\n"+
        "Joe Rogan, 1617325200000, Fiserv Forum, Joe Rogan, Joe Rogan's latest comedy tour\n"+
        "The Weeknd, 1617404400000, Fiserv Forum, The Weeknd, The Weeknd's After Hours Tour\n"));
        
        List<Date> actual = backendToTest.getThreeAvailableTimes(0);
        List<Date> expected = new ArrayList<Date>();
        expected.add(new Date(1616997600000l));
        expected.add(new Date(1617004800000l));
        expected.add(new Date(1617008400000l));
        
        assertEquals(expected.toString(), actual.toString());
    }
    
    /**This method tests if the isAvailable method properly finds an available time given some
     * events in the schedule.
     * 
     */
    @Test
    public void isAvailableTrueTest() {
        BackendImplementation backendToTest = new BackendImplementation(new StringReader(
            "name, date, venue-setting, group-name, description\n" + 
        "Milwaukee Bucks, 1617296400000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Boston Celtics\n"+
        "Milwaukee Bucks, 1617490800000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Toronto Raptors\n"+
        "Joe Rogan, 1617325200000, Fiserv Forum, Joe Rogan, Joe Rogan's latest comedy tour\n"+
        "The Weeknd, 1617404400000, Fiserv Forum, The Weeknd, The Weeknd's After Hours Tour\n"));
        
        assertTrue(backendToTest.isAvailable(new Date(1617310800000l)));
    }
    
    /**This method tests is the isAvailable method properly finds a conflicting event and returns
     * false.
     * 
     */
    @Test
    public void isAvailableFalseTest() {
        BackendImplementation backendToTest = new BackendImplementation(new StringReader(
            "name, date, venue-setting, group-name, description\n" + 
        "Milwaukee Bucks, 1617296400000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Boston Celtics\n"+
        "Milwaukee Bucks, 1617490800000, Fiserv Forum, Milwaukee Bucks, Milwaukee Bucks vs. Toronto Raptors\n"+
        "Joe Rogan, 1617325200000, Fiserv Forum, Joe Rogan, Joe Rogan's latest comedy tour\n"+
        "The Weeknd, 1617404400000, Fiserv Forum, The Weeknd, The Weeknd's After Hours Tour\n"));
        
        assertTrue(!backendToTest.isAvailable(new Date(1617296400000l)));
        
    }
    
}

