import java.util.Date;
public class EventDummy {
    private Date date;
    private String name;
    private String description;
    private String groupName;
    private String venueSetting;
    
    public Event(Date dateObj, String name, String groupName, String venueSetting, String description){
        this.date=dateObj;
        this.name=name;
        this.groupName=groupName;
        this.venueSetting=venueSetting;
        this.description=description;
    }
    public Date setDate(Date obj){
        this.Date=obj;
    }
    public Date getDate(){
        return date;
    }
    public String getDescription(){
        return description;
    }
    public String getGroupName(){
        return groupName;
    }
    public String getVenue(){
        return venueSetting;
    }
    public String getName(){
        return name;
    }
    public String toString(){
        return "Event Name: "+this.name+ "\nEvent Date: "+this.date + "\nEvent Description: "+ this.description +"\nEvent Venue: "
        +this.venueSetting+ "\nGroup Name: " + this.groupName;
    }
}
