import java.util.Date;
import java.util.List;

public interface BackendInterface {

    public void addEvent(Date date, String name, String desc,
        String group, String venue); 
    public List<Event> getThreeEvents(int startIndex);
    public int getNumberOfEvents();
    public boolean isAvailable(Date date);
    public List<Date> getThreeAvailableTimes(int startIndex);
    public int getNumberAvailableTimes();
    public List<Event> getEventsByDate(Date d);
    public List<Event> getEventsByName(String name);   
    
}

