import java.util.Date;

public class EventDummy implements EventInterface {
    private Date date;
    public String name;
    private String description;
    private String groupName;
    private String venueSetting;

    public EventDummy(Date date, String name, String description, String groupName, String venueSetting) {
        this.date = date;
        this.name = name;
        this.description = description;
        this.groupName = groupName;
        this.venueSetting = venueSetting;
    }
    
    public void setDate(Date obj) {
        date = obj;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getVenueSetting() {
        return venueSetting;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
    public int compareTo(Event e){
	System.out.println("EventDummy.java does not implement CompareTo method");
	return 0;
    }
}
