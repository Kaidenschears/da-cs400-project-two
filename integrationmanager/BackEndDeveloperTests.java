/*
 * Test addEvent on an empty tree
Test addEvent on a tree with some other items in it
Test getEventsByName on a tree with the correct item(s)
Test getEventsByDate on a tree with the correct item(s)
Test if addEvent and getEventsByName could catch exceptions with invalid inputs
Test instantiating back end with 3 events and test that those events are present in the getThreeEvents method
Test getThreeAvailibleTimes outputting next three time slots
Test isAvailable with and without conflicting events

 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class BackEndDeveloperTests {

    /**This method tests whether or not a backend instantiated with some events has them present when the
     * getThreeEvents method is called
     * 
     */
    @Test
    public void instantiateBackendTest() {
        BackendImplementation backendToTest = new BackendImplementation(); /*new BackendImplementation(StringReader("
        name, date, description, group-name, venue-setting/n" + 
        "/"Milwaukee Bucks/", new Date(1617296400000L), /"Milwaukee Bucks vs. Boston Celtics/", /"Milwaukee Bucks/", /"Fiserv Forum/""+
        "/"Joe Rogan/", new Date(1617325200000L), /"Joe Rogan's latest comedy tour/", /"Joe Rogan/", /"Fiserv Forum/""+
        "/"The Weeknd/", new Date(1617404400000L), /"The Weeknd's After Hours Tour/", /"The Weeknd/", /"Fiserv Forum/"")) */
        
        Event event1 = new Event("Milwaukee Bucks", new Date(1617296400000L), "Milwaukee Bucks vs. Boston Celtics", "Milwaukee Bucks", "Fiserv Forum");
        Event event2 = new Event("Joe Rogan", new Date(1617325200000L), "Joe Rogan's latest comedy tour", "Joe Rogan", "Fiserv Forum");
        Event event3 = new Event("The Weeknd", new Date(1617404400000L), "The Weeknd's After Hours Tour", "The Weeknd", "Fiserv Forum");
        
        List<Event> expected = new ArrayList<Event>();
        expected.add(event1);
        expected.add(event2);
        expected.add(event3);     
        
        assertEquals(backendToTest.getThreeEvents(0), expected);        
    }
    
    /**This method tests whether or not an event is properly added to the backend if it is
     * the first event adde (and thus the root)
     * 
     */
    @Test
    public void addEventsTestEmpty() {
        BackendImplementation backendToTest = new BackendImplementation();
        
        backendToTest.addEvent(new Date(1617210000000L), "Tame Impala", "Tame Impala with Perfume Genius", "Tame Impala", "Fiserv Forum");
        
        List<Event> expected = new ArrayList<Event>();
        expected.add(new Event("Tame Impala", new Date(1617210000000L),  "Tame Impala with Perfume Genius", "Tame Impala", "Fiserv Forum"));
        
        assertEquals(backendToTest.getThreeEvents(0), expected);
    }
    
    /**This method test whether or not the backend, that already has some events in it, will
     * properly place a new event in the right spot according to date.
     * 
     */
    @Test
    public void addEventsTestFull() {
        BackendImplementation backendToTest = new BackendImplementation(); /*new BackendImplementation(StringReader("
        name, date, description, group-name, venue-setting/n" + 
        "/"Milwaukee Bucks/", new Date(1617296400000L), /"Milwaukee Bucks vs. Boston Celtics/", /"Milwaukee Bucks/", /"Fiserv Forum/""+
        "/"Joe Rogan/", new Date(1617325200000L), /"Joe Rogan's latest comedy tour/", /"Joe Rogan/", /"Fiserv Forum/""+
        "/"The Weeknd/", new Date(1617404400000L), /"The Weeknd's After Hours Tour/", /"The Weeknd/", /"Fiserv Forum/"")) */
        
        backendToTest.addEvent(new Date(1617382800000L), "Tame Impala", "Tame Impala with Perfume Genius", "Tame Impala", "Fiserv Forum");
        
        Event event1 = new Event("Milwaukee Bucks", new Date(1617296400000L), "Milwaukee Bucks vs. Boston Celtics", "Milwaukee Bucks", "Fiserv Forum");
        Event event2 = new Event("Joe Rogan", new Date(1617325200000L), "Joe Rogan's latest comedy tour", "Joe Rogan", "Fiserv Forum");
        Event event3 = new Event("Tame Impala", new Date(1617382800000L), "Tame Impala with Perfume Genius", "Tame Impala", "Fiserv Forum");
        
        List<Event> expected = new ArrayList<Event>();
        expected.add(event1);
        expected.add(event2);
        expected.add(event3);     
        
        assertEquals(backendToTest.getThreeEvents(0), expected);        
    }
    
    /**This method tests whether the proper events are returned when the getEventsByName
     * method is called on a name with multiple matching events.
     * 
     */
    @Test
    public void getEventsByNameTest() {
        BackendImplementation backendToTest = new BackendImplementation(); /*new BackendImplementation(StringReader("
        name, date, description, group-name, venue-setting/n" + 
        "/"Milwaukee Bucks/", new Date(1617296400000L), /"Milwaukee Bucks vs. Boston Celtics/", /"Milwaukee Bucks/", /"Fiserv Forum/""+
        "/"Milwaukee Bucks/", new Date(1617490800000L), /"Milwaukee Bucks vs. Toronto Raptors/", /"Milwaukee Bucks/", /"Fiserv Forum/""+
        "/"Joe Rogan/", new Date(1617325200000L), /"Joe Rogan's latest comedy tour/", /"Joe Rogan/", /"Fiserv Forum/""+
        "/"The Weeknd/", new Date(1617404400000L), /"The Weeknd's After Hours Tour/", /"The Weeknd/", /"Fiserv Forum/"")) */
        
        List<Event> actual = backendToTest.getEventsByName("Milwaukee Bucks");
        List<Event> expected = new ArrayList<Event>();
        expected.add(new Event("Milwaukee Bucks", new Date(1617296400000L), "Milwaukee Bucks vs. Boston Celtics", "Milwaukee Bucks", "Fiserv Forum"));
        expected.add(new Event("Milwaukee Bucks", new Date(1617490800000L), "Milwaukee Bucks vs. Boston Celtics", "Milwaukee Bucks", "Fiserv Forum"));
        
        assertEquals(expected, actual);
    }
    
    /**This method tests whether the getEventsByDate method will return the right events that are
     * within 24 hours of the provided date.
     * 
     */
    @Test
    public void getEventsByDateTest() {
        BackendImplementation backendToTest = new BackendImplementation(); /*new BackendImplementation(StringReader("
        name, date, description, group-name, venue-setting/n" + 
        "/"Milwaukee Bucks/", new Date(1617296400000L), /"Milwaukee Bucks vs. Boston Celtics/", /"Milwaukee Bucks/", /"Fiserv Forum/""+
        "/"Milwaukee Bucks/", new Date(1617490800000L), /"Milwaukee Bucks vs. Toronto Raptors/", /"Milwaukee Bucks/", /"Fiserv Forum/""+
        "/"Joe Rogan/", new Date(1617325200000L), /"Joe Rogan's latest comedy tour/", /"Joe Rogan/", /"Fiserv Forum/""+
        "/"The Weeknd/", new Date(1617404400000L), /"The Weeknd's After Hours Tour/", /"The Weeknd/", /"Fiserv Forum/"")) */
        
        List<Event> actual = backendToTest.getEventsByDate(new Date(1617253200000L));
        List<Event> expected = new ArrayList<Event>();
        expected.add(new Event("Milwaukee Bucks", new Date(1617296400000L), "Milwaukee Bucks vs. Boston Celtics", "Milwaukee Bucks", "Fiserv Forum"));
        expected.add(new Event("Joe Rogan", new Date(1617325200000L), "Joe Rogan's latest comedy tour", "Joe Rogan", "Fiserv Forum"));
        
        assertEquals(expected, actual);
    }
    
    /**This methods tests that the addEvent method will throw an exception if the given event has
     * improper details.
     * 
     */
    @Test
    public void addEventInvalidTest() {
        BackendImplementation backendToTest = new BackendImplementation(); /*new BackendImplementation(StringReader("
        name, date, description, group-name, venue-setting/n" + 
        "/"Milwaukee Bucks/", new Date(1617296400000L), /"Milwaukee Bucks vs. Boston Celtics/", /"Milwaukee Bucks/", /"Fiserv Forum/""+
        "/"Milwaukee Bucks/", new Date(1617490800000L), /"Milwaukee Bucks vs. Toronto Raptors/", /"Milwaukee Bucks/", /"Fiserv Forum/""+
        "/"Joe Rogan/", new Date(1617325200000L), /"Joe Rogan's latest comedy tour/", /"Joe Rogan/", /"Fiserv Forum/""+
        "/"The Weeknd/", new Date(1617404400000L), /"The Weeknd's After Hours Tour/", /"The Weeknd/", /"Fiserv Forum/"")) */
        boolean thrown = false;
        try {
            backendToTest.addEvent(null, "", "", "", "");   
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    
    /**This method tests if the getEvent methods throw an exception if the method is called
     * with improper dates.
     * 
     */
    @Test
    public void getEventInvalidTest() {
        BackendImplementation backendToTest = new BackendImplementation(); /*new BackendImplementation(StringReader("
        name, date, description, group-name, venue-setting/n" + 
        "/"Milwaukee Bucks/", new Date(1617296400000L), /"Milwaukee Bucks vs. Boston Celtics/", /"Milwaukee Bucks/", /"Fiserv Forum/""+
        "/"Milwaukee Bucks/", new Date(1617490800000L), /"Milwaukee Bucks vs. Toronto Raptors/", /"Milwaukee Bucks/", /"Fiserv Forum/""+
        "/"Joe Rogan/", new Date(1617325200000L), /"Joe Rogan's latest comedy tour/", /"Joe Rogan/", /"Fiserv Forum/""+
        "/"The Weeknd/", new Date(1617404400000L), /"The Weeknd's After Hours Tour/", /"The Weeknd/", /"Fiserv Forum/"")) */
        boolean thrown = false;
        try {
            backendToTest.getEventsByDate(null);   
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    
    /**This method tests if the next three available two-hour blocks are returned by the
     * getThreeAvailableTimes method given some conflicting events.
     * 
     */
    @Test
    public void nextAvailableTimesTest() {
        BackendImplementation backendToTest = new BackendImplementation(); /*new BackendImplementation(StringReader("
        name, date, description, group-name, venue-setting/n" + 
        "/"Milwaukee Bucks/", new Date(1617296400000L), /"Milwaukee Bucks vs. Boston Celtics/", /"Milwaukee Bucks/", /"Fiserv Forum/""+
        "/"Joe Rogan/", new Date(1617303600000L), /"Joe Rogan's latest comedy tour/", /"Joe Rogan/", /"Fiserv Forum/""+
        "/"The Weeknd/", new Date(1617325200000L), /"The Weeknd's After Hours Tour/", /"The Weeknd/", /"Fiserv Forum/"")) */
        
        List<Date> actual = backendToTest.getThreeAvailableTimes(0);
        List<Date> expected = new ArrayList<Date>();
        expected.add(new Date(1617310800000L));
        expected.add(new Date(1617318000000L));
        expected.add(new Date(1617332400000L));
        
        assertEquals(expected, actual);
    }
    
    /**This method tests if the isAvailable method properly finds an available time given some
     * events in the schedule.
     * 
     */
    @Test
    public void isAvailableTrueTest() {
        BackendImplementation backendToTest = new BackendImplementation(); /*new BackendImplementation(StringReader("
        name, date, description, group-name, venue-setting/n" + 
        "/"Milwaukee Bucks/", new Date(1617296400000L), /"Milwaukee Bucks vs. Boston Celtics/", /"Milwaukee Bucks/", /"Fiserv Forum/""+
        "/"Joe Rogan/", new Date(1617325200000L), /"Joe Rogan's latest comedy tour/", /"Joe Rogan/", /"Fiserv Forum/""+
        "/"The Weeknd/", new Date(1617404400000L), /"The Weeknd's After Hours Tour/", /"The Weeknd/", /"Fiserv Forum/"")) */
        
        assertTrue(backendToTest.isAvailable(new Date(1617310800000L)));
    }
    
    /**This method tests is the isAvailable method properly finds a conflicting event and returns
     * false.
     * 
     */
    @Test
    public void isAvailableFalseTest() {
        BackendImplementation backendToTest = new BackendImplementation(); /*new BackendImplementation(StringReader("
        name, date, description, group-name, venue-setting/n" + 
        "/"Milwaukee Bucks/", new Date(1617296400000L), /"Milwaukee Bucks vs. Boston Celtics/", /"Milwaukee Bucks/", /"Fiserv Forum/""+
        "/"Joe Rogan/", new Date(1617325200000L), /"Joe Rogan's latest comedy tour/", /"Joe Rogan/", /"Fiserv Forum/""+
        "/"The Weeknd/", new Date(1617404400000L), /"The Weeknd's After Hours Tour/", /"The Weeknd/", /"Fiserv Forum/"")) */
        
        assertTrue(!backendToTest.isAvailable(new Date(1617296400000L)));
        
    }
    
}

