import java.util.Date;

public interface EventInterface extends Comparable<Event>{
    
    public void setDate(Date odj);
    public Date getDate();
    public String getDescription();
    public String getGroupName();
    public String getVenueSetting();
    public String getName();
    public String toString();
    
}
