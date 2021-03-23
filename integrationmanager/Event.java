import java.util.Date;
import java.util.zip.DataFormatException;
public class Event implements EventInterface, Comparable<Event>{

    private String name;
    private Date date;
    private String desc;
    private String group;
    private String venue;
    
    public Event(String name, Date date, String venue, String group, String desc) throws DataFormatException{
        this.name = name;
        this.date = date;
        this.desc = desc;
        this.group = group;
        this.venue = venue;
        if(name==""||date==null||venue==""||group==""||desc==""){
            throw new DataFormatException("Invailid Event attributes");
        }
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
    
    @Override
    public String toString() {
    return name + " with " + group + ", " + date;
    }

}
