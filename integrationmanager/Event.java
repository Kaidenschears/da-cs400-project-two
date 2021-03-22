import java.util.Date;
public class Event implements EventInterface {

    private String name;
    private Date date;
    private String desc;
    private String group;
    private String venue;
    
    public Event(String name, Date date, String desc, String group, String venue) {
        this.name = name;
        this.date = date;
        this.desc = desc;
        this.group = group;
        this.venue = venue;
    }

    @Override
    public void setDate(Date date) { this.date = date; }

    @Override
    public Date getDate() { return this.date; }

    @Override
    public String getDescription() { return this.desc; }

    @Override
    public String getGroupName() { return this.group; }

    @Override
    public String getVenueSetting() { return this.venue; }

    @Override
    public String getName() { return this.name; }

    @Override
    public int compareTo(Event arg0) { return this.date.compareTo(arg0.date); }
    
    public String toString() {
        // "EVENT NAME @ EE MM DD HH:MM:SS ZZZ YYYY"
        return "" + this.name + " @ " + this.date.toString();
    }
}
