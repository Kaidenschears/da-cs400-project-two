import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DataFormatException;

public class BackendImplementation implements BackendInterface{

    RedBlackTree<Event> events;

    public BackendImplementation() {
        events = new RedBlackTree<Event>();  
    }

    public BackendImplementation(Reader reader) {
        events = new RedBlackTree<Event>();
        EventDataReaderInterface dataReader = null;
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
        
        if (initEvents == null) return;
        
        for (int i = 0; i < initEvents.size(); i++) events.insert(initEvents.get(i));
        
    }
    public BackendImplementation(String[] args) {
        events = new RedBlackTree<Event>();
        EventDataReaderInterface dataReader = null;//new EventDataReaderInterface();
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
        
        
    }

    @Override
    public void addEvent(Date date, String name, String desc, String group, String venue) {
        events.insert(new Event(name, date, desc, group, venue));
    }

    @Override
    public List<Event> getThreeEvents(int startIndex) {
        Iterator<Event> it = events.iterator();
        List<Event> nextThree = new ArrayList<Event>();

        try {
            for (int i = 0; i < startIndex; i++) it.next();
        } catch (Exception e) {
            return null;
        }

        try {
            for (int i = 0; i < 3; i++) nextThree.add(it.next());
        } catch (Exception e) {
            return nextThree;
        }

        return nextThree;
    }

    @Override
    public int getNumberOfEvents() { return events.size(); }

    @Override
    public boolean isAvailable(Date date) {
        Iterator<Event> it = events.iterator();

        while(it.hasNext()) {
            Event temp = it.next();
            if (temp.getDate().compareTo(date) == 0) return false;
            else if (temp.getDate().compareTo(date) < 0 ) continue;
            else return true;
        }

        return true;
    }

    @Override
    public List<Date> getThreeAvailableTimes(int startIndex) {
        long begin = 1616994000000l; // corresponds to 3/29/2021 at 5:0:0 GMT
                                     // or 3/29/2021 at 0:0:0 CST
        long end = 1617598800000l;   // corresponds to 4/5/2021 at 5:0:0 GMT
                                     // or 4/5/2021 at 0:0:0 CST
        int hour = 3600000;          // length of an hour in milliseconds
        List<Date> availDate = new ArrayList<Date>();
        List<Date> nextThree = new ArrayList<Date>();

        while (begin < end) {
            Date temp = new Date(begin);
            if (isAvailable(temp)) availDate.add(temp);
            begin += hour;
        }

        try {
            for (int i = 0; i < startIndex; i++) availDate.remove(0);
        } catch (Exception e) {
            return null;
        }

        try {
            for (int i = 0; i < 3; i++) nextThree.add(availDate.get(i));
        } catch (Exception e) {
            return availDate;
        }

        return availDate;
    }

    @Override
    public int getNumberAvailableTimes() {
        long begin = 1616994000000l; // corresponds to 3/29/2021 at 5:0:0 GMT
                                     // or 3/29/2021 at 0:0:0 CST
        long end = 1617598800000l;   // corresponds to 4/5/2021 at 5:0:0 GMT
                                     // or 4/5/2021 at 0:0:0 CST
        int hour = 3600000;          // length of an hour in milliseconds
        int avail = 0;
        
        while (begin < end) {
            if (isAvailable(new Date(begin))) avail ++;
            begin += hour;
        }
        
        return avail;
    }

    @Override
    public List<Event> getEventsByDate(Date d) {
        Iterator<Event> it = events.iterator();
        List<Event> matches = new ArrayList<Event>();
        
        while (it.hasNext()) {
            Event temp = it.next();
            if (temp.getDate().compareTo(d) == 0) matches.add(temp);
            else if (temp.getDate().compareTo(d) < 0 ) continue;
            else return matches;
        }
        
        return matches;
    }

    @Override
    public List<Event> getEventsByName(String name) {
        Iterator<Event> it = events.iterator();
        List<Event> matches = new ArrayList<Event>();
        
        while (it.hasNext()) {
            Event temp = it.next();
            if (temp.getName().compareTo(name) == 0) matches.add(temp);
        }
       
        return matches;
    }

}
