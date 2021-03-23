import java.util.Date;
import java.util.List;

public interface BackendInterface {
    public void addEvent(Date date, String name, String desc, String group, String venue);
    public List<EventInterface> getThreeEvents(int startIndex);
    public int getNumberOfEvents();
    public boolean isAvailable(Date date);
    public List<Date> getThreeAvailableTimes(int startIndex);
    public int getNumberAvailableTimes();
    public List<EventInterface> getEventsByDate(Date d);
    public List<EventInterface> getEventsByName(String name);
}