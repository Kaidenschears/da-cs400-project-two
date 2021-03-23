import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventObject implements Event {
  private Date date;
  private String description;
  private String groupName;
  private String venue;
  private String name;

  public EventObject(String name, Date date, String venue, String groupName, String description) {
    // TODO Auto-generated constructor stub
    this.name = name;
    this.venue = venue;
    this.groupName = groupName;
    this.description = description;
    this.date = date;
    
    }

  @Override
  public void setDate(Date newDate) {
    // TODO Auto-generated method stub
    this.date = newDate;
    
  }

  @Override
  public Date getDate() {
    
    return this.date;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public String getGroupName() {
    return this.groupName;
  }

  @Override
  public String getVenueSetting() {
    return venue;
  }

  @Override
  public String getName() {
    return name;
  }

  public String toString() {
    return name + " with " + groupName + ", " + date;
  }

  
  public int compareTo(EventObject obj) {
    return this.getDate().compareTo(obj.getDate());
  }
}
