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

  @Override

  /**
   * Reads a FileReader into a list of movie objects @return(s) List of Event objects
   * 
   * @throws IOException,DataFormatException
   */
  public List<EventInterface> readDataSet(Reader inputReader) throws IOException, DataFormatException {
    if (inputReader == null) {
      throw new IOException("Reader object passed to ReadDataSet is null");
    }

    List<EventInterface> events = new ArrayList<Event>();
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
   * Uses the columns list created through the parseEvent method and create a new movie
   * object @return(s) an instance of Event
   * 
   * @throws DataFormatException if a movie doesn't have the right number of attributes, if passed a
   *                             movie that was made outside of the range 1800-currentYear, or if
   *                             avgVote is outside of the range 0.0-10.0.
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
      name = columns.get(0);

      venue = columns.get(2);

      groupName = columns.get(3);

      description = columns.get(4);

      eventDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(columns.get(1)); 


    } catch (Exception e) {
      throw new DataFormatException(e.getMessage());
    }
    return new Event(name, eventDate, venue, groupName, description);// put arguments here);
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


