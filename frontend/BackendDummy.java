import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

public class BackendDummy implements BackendInterface {
    private List<EventInterface> events;
    private List<Date> availableTimes;

    public BackendDummy() {
        events =  new ArrayList<EventInterface>();
        for (int i = 1; i  <= 5; i++) {
            events.add(new EventDummy(new Date("3/" + i + "/2021"), "Event " + i, "Event " + i + " Description", "Event " + i + " Group", "Event " + i + " Venue"));
        }
        availableTimes = new ArrayList<Date>();
        for (int i = 6; i < 32; i++) {
            availableTimes.add(new Date("3/" + i + "/2021"));
        }
    }
    
    public void addEvent(Date date, String name, String desc, String group, String venue) {
        events.add(new EventDummy(date, name, desc, group, venue));
    }
    
    public List<EventInterface> getThreeEvents(int startIndex) {
        List<EventInterface> result = new ArrayList<EventInterface>();
        for (int i = startIndex; i < events.size() && i < startIndex+3; i++) {
            result.add(events.get(i));
        }
        return result;
    }

    public int getNumberOfEvents() {
        return events.size();
    }
    
    public boolean isAvailable(Date date) {
        return false;
    }
    
    public List<Date> getThreeAvailableTimes(int startIndex) {
        List<Date> result = new ArrayList<Date>();
        for (int i = startIndex; i < availableTimes.size() && i < startIndex+3; i++) {
            result.add(availableTimes.get(i));
        }
        return result;
    }
    
    public int getNumberAvailableTimes() {
        return availableTimes.size();
    }
    
    public List<EventInterface> getEventsByDate(Date d) {
        return events.stream().filter(e -> e.getDate().equals(d)).collect(Collectors.toList());
    }

    public List<EventInterface> getEventsByName(String name) {
        return events.stream().filter(e -> e.getName().equals(name)).collect(Collectors.toList());
    }
}