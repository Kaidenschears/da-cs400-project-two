import java.util.List;
import java.util.ArrayList;
import java.util.Date;
public class DatawranglerDummy {
    public List<Event> readDataSet(){
        
        String description="Basic description for Event: ";
        String groupName="Basic Group Name";
        String  venueSetting="Basic Venue Setting";
        String  name="Basic Name for an event";
        List<Date> dateList=new ArrayList<>();
        List<Event> eventList=new ArrayList<>();
        dateList.add(new Date()); //current Date & time 
        dateList.add(new Date(2021-1900,2,14,12,0));  //year:2021, month:3, day:14, Hour:12, minutes:0
        dateList.add(new Date(2021-1900,2,16,12,0));
        dateList.add(new Date(2021-1900,2,16,10,0));
        dateList.add(new Date(2021-1900,2,16,14,0));
        dateList.add(new Date(2021-1900,2,18,14,0));
        dateList.add(new Date(2021-1900,2,21,14,0));
        dateList.add(new Date(2021-1900,2,21,12,0));
        dateList.add(new Date(2021-1900,2,23,10,0));
        dateList.add(new Date(2021-1900,2,23,14,0));
        int count=1;
        for(int i=0;i<dateList.size();i++){
            
            eventList.add(new Event(dateList.get(i), name, groupName, venueSetting,description+count));
            count++;
        }
        return eventList;

    }
}
