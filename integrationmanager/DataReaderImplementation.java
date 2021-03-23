import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

public class DataReaderImplementation implements EventDataReaderInterface {

  

  /**
   * Reads a FileReader into a list of Event objects @return(s) List of Event objects
   * 
   * @throws IOException,DataFormatException
   */
  @Override
  public List<Event> readDataSet(Reader inputReader) throws IOException, DataFormatException {
    if (inputReader == null) {
      throw new IOException("Reader object passed to ReadDataSet is null");
    }

    List<Event> events = new ArrayList<Event>();
    String line = "";
    BufferedReader br = null;
    br = new BufferedReader(inputReader);
    br.readLine();// skip first line of csv file
    line = br.readLine();
    while (line != null) {
      char[] charStream = line.toCharArray();
      Event newEvent = createEvent(parseEvent(charStream));
      events.add(newEvent);
      line = br.readLine();

    }


    return events;
  }

  /**
   * Uses the columns list created through the parseEvent method and create a new Event
   * object @return(s) an instance of Event
   * 
   * @throws DataFormatException 
   */
  public static Event createEvent(List<String> columns) throws DataFormatException {

    String name, venue, groupName, description;
    Date eventDate;
    LocalDate currentDate = java.time.LocalDate.now();

    try {
      if (columns.size() != 5) {
        throw new DataFormatException(
            "The number of columns for this event object is not the expected size of 5.");
      }
      name = columns.get(0).trim();

      venue = columns.get(2).trim();

      groupName = columns.get(3).trim();

      description = columns.get(4).trim();
      SimpleDateFormat df=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
      // int length= columns.get(1).trim().length();
      // if(columns.get(1).trim().charAt(length-1)== 'L'){
      //   System.out.println("chk1");
      //   long date=Long.parseLong(columns.get(1).trim());
      //   System.out.println("chk2");
      //   eventDate=new Date(date);
      //   System.out.println("chk3");
      // }
      // else
       try{
      eventDate = df.parse(columns.get(1).trim());}
      catch(Exception e){
        long date=Long.parseLong(columns.get(1).trim());
        eventDate=new Date(date);
      }

  

    } catch (Exception e) {
      throw new DataFormatException(e.getMessage());
    }
    return new Event(name, eventDate, venue, groupName, description);
    // name, Date, venue, groupName, description
  }

  /**
   * Parses one line of CSV by iterating over each character in the line and adding it to a string
   * builder until a comma is found, then the string is added to the columns array. When iterating
   * over the description commas & "" are ignored. When iterating over a list of strings (like the
   * genre attribute) the commas are used to commit a new item to a temporary variable stringList
   * which is added to the columns array as a list of string items when there are no items left in
   * the list.
   * 
   * @param charStream is a single line of the CSV file in a char Array @return(s) the columns array
   *                   that has all of the movie attributes in indices 1-12
   */
  public static List<String> parseEvent(char[] charStream) {
    List<String> columns = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    int lastIndex = charStream.length - 1;
    StringBuilder b = new StringBuilder(); // each StringBuilder is used to keep track of a single
                                           // item(string) within the line of the CSV


    int count = 0; // keeps track of current index
    for (char c : charStream) {
      // case1: we have hit a comma
      switch (c) {
        case (','):
          columns.add(b.toString());

          b = new StringBuilder();

          count++;
          break;

        default:
          b.append(c);
          if (count == lastIndex) { // checks if current index is the last index and if so the last
                                    // item in the line is added to the columns array
            columns.add(b.toString());
          }
          count++;
          break;
      }
    }


    return columns;
  }

}


