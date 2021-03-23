// --== CS400 File Header Information ==--
// Name: Bennett Schmanski
// Email: bschmanski@wisc.edu
// Team: DA: Red
// Role: Back End Developer
// TA: Dan Kiel
// Lecturer: Gary Dahl
// Notes to Grader: 

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Backend for the VenueScheduler project. Instantiates the data reader given a reader, a file name
 * in the form of a string, or nothing. Utilizes a Red/Black tree to store the data for the venue.
 * Provides the frontend with all the data it needs and handles new additions from the 
 * frontend.
 *
 */
public class BackendImplementation implements BackendInterface{

    // the red black tree holding all the event objects
    RedBlackTree<Event> events;

    /**
     * This constructor takes no arguments and just instantiates an empty tree.
     */
    public BackendImplementation() {
        events = new RedBlackTree<Event>();  
    }

    /** Constructor that takes a reader and parses the events from the reader. Instantiates
     * the datareader to parse the events.
     * @param reader is a given reader for a list of events
     */
    public BackendImplementation(Reader reader) {
        events = new RedBlackTree<Event>();
        DataReaderImplementation dataReader = new DataReaderImplementation();
        // could throw an exception if the file is not found, or if it is not formated correctly
        List<Event> initEvents = null;
        try {
            initEvents = dataReader.readDataSet(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
        
        // if reader has no initial events, done. Otherwise, add all the events into the tree
        if (initEvents == null) return;
        
        for (int i = 0; i < initEvents.size(); i++) events.insert(initEvents.get(i));
        
    }
    
    /**Constructor that takes the command line arguments and parses the file anme from the
     * first arguement. Instantiates the datareader given the file name and adds the events
     * to the red/black tree.
     * 
     * @param args is the command line arguments passed from the frontend.
     */
    public BackendImplementation(String[] args) {
        events = new RedBlackTree<Event>();
        DataReaderImplementation dataReader = new DataReaderImplementation();
        // could throw an exception if the file is not found, or if it is not formated correctly
        List<Event> initEvents = null;
        try {
            initEvents = dataReader.readDataSet(new FileReader(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
        
        // if reader has no initial events, done. Otherwise, add all the events into the tree
        if (initEvents == null) return;
        
        for (int i = 0; i < initEvents.size(); i++) events.insert(initEvents.get(i));
        
    }

    /* 
     * Adds a new event into the red black tree given data from the front end.
     * 
     * @param date the new event's date
     * @param name the new event's name
     * @param desc the new event's description
     * @param group the new event's group name
     * @param venue the new event's venue
     */
    @Override
    public void addEvent(Date date, String name, String venue, String group, String desc) {
        try{
        events.insert(new Event(name, date, venue, group, desc));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /*
     * From a given int index, this method returns a list of the three most upcoming events.
     * Returns the list of three event objects that are coming up.
     * 
     * @param startIndex the starting index for which to pull three events
     * 
     * @return a list of the next three events from the given index
     */
    @Override
    public List<Event> getThreeEvents(int startIndex) {
        Iterator<Event> it = events.iterator();
        List<Event> nextThree = new ArrayList<Event>();

        // checks if the index is outside the number of scheduled events
        try {
            for (int i = 0; i < startIndex; i++) it.next();
        } catch (Exception e) {
            return null;
        }

        // adds the next three events and stops if an out of bounds exception is thrown
        try {
            for (int i = 0; i < 3; i++) nextThree.add(it.next());
        } catch (Exception e) {
            return nextThree;
        }

        return nextThree;
    }

    /*
     * Returns the total number events in the Red/Black tree
     * 
     * @return the number of events in the tree
     */
    @Override
    public int getNumberOfEvents() { return events.size(); }

    /*
     * Returns true if the given date is available in the tree, false if otherwise.
     * 
     * @ return if the given time has no conflicts or not
     */
    @Override
    public boolean isAvailable(Date date) {
        Iterator<Event> it = events.iterator();

        // traverses the iterator in-order traversal to see if there is an event with the given time
        while(it.hasNext()) {
            Event temp = it.next();
            if (temp.getDate().compareTo(date) == 0) return false;
            else if (temp.getDate().compareTo(date) < 0 ) continue;
            else return true;
        }

        return true;
    }

    /*
     * Returns a list of dates that are available to schedule an event from a starting
     * index. Three dates will be provided.
     * 
     * @param startIndex is the starting index from which to provide the next three available times
     * 
     * @return a list of the next three available dates from the given index
     */
    @Override
    public List<Date> getThreeAvailableTimes(int startIndex) {
        long begin = 1616994000000l; // corresponds to 3/29/2021 at 5:0:0 GMT
                                     // or 3/29/2021 at 0:0:0 CST
        long end = 1617598800000l;   // corresponds to 4/5/2021 at 5:0:0 GMT
                                     // or 4/5/2021 at 0:0:0 CST
        int hour = 3600000;          // length of an hour in milliseconds
        List<Date> availDate = new ArrayList<Date>();
        List<Date> nextThree = new ArrayList<Date>();

        // traverses the iterator in-order traversal to find a list of hour time
        // slots that are empty over the week 3/29 thru 4/4
        while (begin < end) {
            Date temp = new Date(begin);
            if (isAvailable(temp)) availDate.add(temp);
            begin += hour;
        }

        // removes the first n dates depending on the index, catches if the index is too large
        try {
            for (int i = 0; i < startIndex; i++) availDate.remove(0);
        } catch (Exception e) {
            return null;
        }

        // adds the first three (or less) dates from the new list to the returned list.
        // less if there are less than 3 events in the list
        try {
            for (int i = 0; i < 3; i++) nextThree.add(availDate.get(i));
        } catch (Exception e) {
            return availDate;
        }

        return availDate;
    }

    /* Provides the number of available times over the next week.
     * 
     * @return an interger of the number of available times over the next week.
     */
    @Override
    public int getNumberAvailableTimes() {
        long begin = 1616994000000l; // corresponds to 3/29/2021 at 5:0:0 GMT
                                     // or 3/29/2021 at 0:0:0 CST
        long end = 1617598800000l;   // corresponds to 4/5/2021 at 5:0:0 GMT
                                     // or 4/5/2021 at 0:0:0 CST
        int hour = 3600000;          // length of an hour in milliseconds
        int avail = 0;
        
        // traverses the available times and checks for conflicts at each hour
        while (begin < end) {
            if (isAvailable(new Date(begin))) avail ++;
            begin += hour;
        }
        
        return avail;
    }

    /* Provides a list of events scheduled at a given time. Should be only one, but
     * provides in a list format in case events overlap.
     * 
     * @param date the date to search for events on
     * 
     * @return a list of events that take place on the given date
     */
    @Override
    public List<Event> getEventsByDate(Date d){
         Iterator<Event> it = events.iterator();
        List<Event> matches = new ArrayList<Event>();
        try {
            //improper date throws an error
            if(d==null) throw new DataFormatException("Date was not properly initialized");

            // traverses the iterator in-order traversal searching for matches
            while (it.hasNext()) {
                Event temp = it.next();
                // if there is a match, add it to the return list
                if (temp.getDate().compareTo(d) == 0) matches.add(temp);
                // if the given event has a date smaller than the one searching for, continue
                else if (temp.getDate().compareTo(d) < 0 ) continue;
                // if the given event is passed the date, end the search since it's in-order
                else return matches;
            }
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return matches;
    }

    /* Provides a list of events with a given name (case sensitive). This could be more
     * than one since the date is the identifying object in the tree.
     * 
     * @param name the name of the events searching for in the tree
     * 
     * @return a list of events with the matching name
     */
    @Override
    public List<Event> getEventsByName(String name) {
        Iterator<Event> it = events.iterator();
        List<Event> matches = new ArrayList<Event>();
        
        // traverses the iterator in-order traversal searching for matching names
        while (it.hasNext()) {
            Event temp = it.next();
            if (temp.getName().compareTo(name) == 0) matches.add(temp);
        }
       
        return matches;
    }

}
