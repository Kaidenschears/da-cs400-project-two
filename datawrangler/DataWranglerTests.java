import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DataWranglerTests {

    EventDataReaderInterface tester;
    
    public static void main(String args[]) {
      DataWranglerTests test = new DataWranglerTests();
      System.out.println(test.testEmptyCSV());
      System.out.println(test.testOneEvent());
      System.out.println(test.testThreeEvents());
      System.out.println(test.testToString());
      System.out.println(test.testEvent());
      
    }


/**
 *This test attempts to read from an empty string.
 *The implementation should catch any errors.
 *The test passs if and only if no objects are created.
 */
    public boolean testEmptyCSV(){
        tester = new DataReaderImplementation();
        List<Event> eventsList;

        try{
            eventsList = tester.readDataSet(new StringReader(""));
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        if(eventsList.size() == 0)
            return true;
        else
            return false;
    }



    /**
     * This test will read in one event and make sure the event
     * is created successfully. The test is passed if and only
     * if one object is created. Fails in all other cases.
     */
    public boolean testOneEvent(){
        tester = new DataReaderImplementation();
        List<Event> eventsList;

        try{
            eventsList = tester.readDataSet(new StringReader(
                            "Name,Date,Venue,groupName,Description\n"
                           +"Music Fest,17-Oct-2021 14:00:00,Liquid,Drake,Cool singer"
            ));
        } catch (Exception e){
                e.printStackTrace();
                return false;
        }
        if(eventsList.size() == 1){
            return true;
        }
        else
            return false;
}
    /**
     * This test will read in 3 events and make sure all the
     * events are created successfully. The test is passed if
     * and only if all 3 objects are created. Fails in all other
     * cases.
     */
    public boolean testThreeEvents(){
        tester = new DataReaderImplementation();
        List<Event> eventsList;

        try{
            eventsList = tester.readDataSet(new StringReader(
                            "Group_Name,Date,Venue,Description\n"
                           +"Pop Festivel,17-Mar-2021 14:00:00,Liquid,Drake,Cool singer\n"
                           +"Comedy Night,20-Mar-2021 14:00:00,Liquid,Tom Segura,Comedian\n"
                           +"EDM Fest,18-Mar-2012 14:00:00,Liquid,Louis the Child,Sick Music\n"
            ));
        } catch (Exception e){
                e.printStackTrace();
                return false;
        }
        if(eventsList.size() == 3){
            return true;
        }
        else
            return false;
}

    /**
     * This test will check the constructor and get methods of the
     * event interface. This will make sure that data can be stored retrieved
     * and set correctly. This test passes if all test cases pass and fails
     * in all other cases.
     *
     * UNCOMMENT THIS METHOD AFTER EVENT INTERFACE IS IMPLEMENTED
     */
    public boolean testEvent(){
        try{
            Date dateCheck = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2021-Mar-19 14:00:00");
            EventObject event = new EventObject("Science Exploration", "2021-Mar-19 14:00:00", "Liquid", "Bill Nye", "Make Science Fun");
            if(!event.getGroupName().equals("Bill Nye")){
              System.out.println("check3");
              return false;
            }
            
            else if(event.getDate().compareTo(dateCheck) != 0){
              System.out.println("check2");
              return false;
            }
            
            else if(!event.getVenueSetting().equals("Liquid")){
              System.out.println("check1");
              return false;
            }
        
            else if(!event.getDescription().equals("Make Science Fun")) {
              System.out.println("check");
              return false;
            }
            
            Date newDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse("2021-Mar-20 14:00:00");
            event.setDate(newDate);

            if(event.getDate().compareTo(newDate) != 0){
              System.out.println("check5");
              return false;
            }
            else
                return true;

        } catch (Exception e) {
                System.out.println("check");
                return false;
    }
    }

/**
 * This method will test the toString method for the event object
 * It returns true if and only if the return String matches what is
 * expected.
 */
    public boolean testToString(){
        EventObject event = new EventObject("Science Exploration", "2021-Mar-19 14:00:00", "Liquid", "Bill Nye", "Make Science Fun");
        String check = event.toString();
        if(check.equals("Science Exploration with Bill Nye, Sun Sep 10 14:00:00 CST 24"))
            return true;
        else
            return false;
    }
    
}
