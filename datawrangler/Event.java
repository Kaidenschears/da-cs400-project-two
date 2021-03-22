import java.util.Date;

public interface Event {
    public void setDate(Date obj);
    public Date getDate();
    public String getDescription();
    public String getGroupName();
    public String getVenueSetting();
    public String getName();
    public String toString();
}
