import java.util.ArrayList;
import java.util.Date;

public class BackendDumby{
    private int numEvents=10; //size after dummy data wrangler file is loaded
    public void addEvent(Date date, String name, String descr, String group, String venue){
        String result="Sucessfully added: \n" +new Event(date, name, group, venue, descr).toString();
        numEvents++;
        Ststem.out.println(result);
    }
    public List<Event> getThreeEvents(int startIndex){
        String description="Basic description for Event: ";
        String groupName="Get Three Events";
        String  venueSetting="Basic Venue Setting";
        String  name="Basic Name for an event";
        List<Date> dateList=new ArrayList<>();
        List<Event> resultsList=new ArrayList<>();
        dateList.add(new Date(2021-1900,2,25,12,0)); //current Date & time 
        dateList.add(new Date(2021-1900,2,13,12,0));  //year:2021, month:3, day:13, Hour:12, minutes:0
        dateList.add(new Date(2021-1900,2,14,12,0));
        dateList.add(new Date(2021-1900,2,15,10,0));
        dateList.add(new Date(2021-1900,2,15,14,0));
        dateList.add(new Date(2021-1900,2,19,14,0));
        dateList.add(new Date(2021-1900,2,20,14,0));
        dateList.add(new Date(2021-1900,2,20,12,0));
        dateList.add(new Date(2021-1900,2,22,10,0));
        dateList.add(new Date(2021-1900,2,22,14,0));
        int count=1;
        for(int i=0;i<dateList.size();i++){
            
            eventList.add(new Event(dateList.get(i), name, groupName, venueSetting,description+count));
            count++;
        }
        if(startIndex<0){
            throw new IndexOutOfBoundsException();
        }
        else if(startIndex>10 && startIndex<20){
            resultsList.add(eventList.get(0));
            resultsList.add(eventList.get(1));
            resultsList.add(eventList.get(2));

        }
        else if(startIndex>20){
            resultsList.add(eventList.get(7));
            resultsList.add(eventList.get(8));
            resultsList.add(eventList.get(9));

        }
        return resultsList;
    }
    public int getNumberOfEvents(){
        return numEvents;
    }
    public boolean isAvailable(Date date){
        if(date.before(new Date())){
            System.out.println("This Date is not available because it has already pasted.");
        }

        System.out.println("Backend dummy class doesn't implement isAvailable method");
        return null;
    }
    public List<Date> getThreeAvailableTimes(int startIndex){
        System.out.println("Backend dummy class doesn't implement getThreeAvailableTimes method");
    }
    public int getNumberAvailableTimes(){
        return (14*3)-numEvents;  //venue schedules events up to two weeks in advance with a max of 3 events per day 
    }
    public List<Event> getEventsByDate(Date d){
        int numResults=Math.random()*3+1;
        String description="Basic description for Event: ";
        String groupName="Events by Date";
        String  venueSetting="Basic Venue Setting";
        String  name="Basic Name for an event";
        List<Event> resultsList=new ArrayList<>();

        for(int i=0;i<numResults;i++){
            resultsList.add(new Event(d, name, groupName, venueSetting, description+(i+1)));
        }
        return resultsList;
    }
    public List<Event> getEventsByName(String name){
        nt numResults=Math.random()*3+1;
        String description="Basic description for Event: ";
        String groupName="Events by Date";
        String  venueSetting="Basic Venue Setting";
        List<Event> resultsList=new ArrayList<>();

        for(int i=0;i<numResults;i++){
            resultsList.add(new Event(d, name, groupName, venueSetting, description+(i+1)));
        }
        return resultsList;
    }


}
