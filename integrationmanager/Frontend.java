// --== CS400 File Header Information ==--
// Name: Ji Lau
// Email: jlau24@wisc.edu
// Team: DA: Red
// Role: Front End Developer
// TA: Dan Kiel
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/** The frontend entry point for Team DA Red's Venue Scheduler. It requires a backend interface as part of the constructor.
 * @author Ji Lau
 */
class Frontend {
    private BackendInterface backend;
    private Scanner input;

    public Frontend(BackendInterface backend) {
        this.backend = backend;
        input = new Scanner(System.in);
    }

    /**
     * Coordinates the modes of the Venue Scheduler according to user input, starting first at the Main Mode
     */
    public void run() {
        List<EventInterface> events = backend.getThreeEvents(0);
        int eventIndex = 0;
        while (true) {
            System.out.println();
            System.out.println("==============Team DA: Venue Scheduler==============");
            System.out.println("====================================================");
            
            if (events.size() > 0) {
                System.out.println("Earliest scheduled events (" + backend.getNumberOfEvents() + " total):");
                for (int i = 0; i < events.size(); i++) {
                    EventInterface event = events.get(i);
                    System.out.println(i+eventIndex+1 + ": " + event.getDate() + " - " +  event.getName());
                }
            } else {
                System.out.println(("There are no events scheduled."));
            }
            System.out.println("====================================================");

            System.out.println("Enter command: ");
            if (events.size() > 0) {
                System.out.println("Type '<index>' to scroll to a certain index.");
            }
            System.out.println("Type 'p' to enter Planning Mode.");
            System.out.println("Type 's' to enter Searching Mode.");
            System.out.println("Type 'x' to exit the Venue Scheduler.");
            
            String command = input.nextLine().trim().toLowerCase();

            if (command.matches("^\\d+$")) {
                int index = Integer.parseInt(command);
                if (index >= 0 && index <= backend.getNumberOfEvents()) {
                    eventIndex = index-1;
                    events = backend.getThreeEvents(eventIndex);
                }
            }
            else if (command.equals("p")) {
                runPlanningMode();
                events = backend.getThreeEvents(0);
            }
            else if (command.equals("s")) {
                runSearchingMode();
                events = backend.getThreeEvents(0);
            }
            else if (command.equals("x")) {
                break;
            }
            else {
                System.out.println("Invalid command entered. Please try again.");
            }
        }
    }

    /**
     * Runs the Planning Mode, which can be used to pick new dates to create new events
     */
    public void runPlanningMode() {
        List<Date> availableTimes = backend.getThreeAvailableTimes(0);
        int timeIndex = 0;
        while (true) {
            System.out.println();
            System.out.println("===================PLANNING MODE====================");
            System.out.println("====================================================");

            if (availableTimes.size() > 0) {
                System.out.println("Available Times (" + backend.getNumberAvailableTimes() + " total): ");
                for (int i = 0; i < availableTimes.size(); i++) {
                    Date event = availableTimes.get(i);
                    System.out.println(i+timeIndex+1 + ": " + event.toString());
                }
            } else {
                System.out.println("There are no available times.");
            }
            System.out.println("====================================================");

            System.out.println("Type '<index>' to scroll to a certain index.");
            System.out.println("Type 'p <index>' to pick an available time by its index.");
            System.out.println("Type 'x' to return to the Main Mode.");
            String command = input.nextLine().trim().toLowerCase();
            if (command.matches("^\\d+$")) {
                int index = Integer.parseInt(command);
                if (index >= 0 && index <= backend.getNumberAvailableTimes()) {
                    timeIndex = index-1;
                    availableTimes = backend.getThreeAvailableTimes(timeIndex);
                }
            }
            else if (command.matches("^p \\d+\\s*$")) {
                String[] parts = command.split(" ");
                int index = Integer.parseInt(parts[1])-1;
                List<Date> times = backend.getThreeAvailableTimes(index);
                if (times.size() > 0) {
                    promptUserForEventInfo(times.get(0));
                    availableTimes = backend.getThreeAvailableTimes(0);
                }
            }
            else if (command.equals("x")) {
                break;
            } else {
                System.out.println("You entered an invalid command.");
            }
            System.out.println();
        }
    }

    /**
     * Prompts the user for event information when creating an event; is called after the user selects a date in Planning Mode
     * @param date the date to create the event for
     */
    public void promptUserForEventInfo(Date date) {
        System.out.println();
        System.out.println("===========PLANNING MODE: EVENT CREATION============");
        System.out.println("====================================================");
        System.out.println("You are currently entering data for the new event.");
        System.out.println("You may type '!x' at any moment to cancel and return to the date selection screen.");

        String[] prompts = {
            "Event Name: ",
            "Event Description: ",
            "Group Name: ",
            "Venue: "
        };
        List<String> answers = new ArrayList<String>();

        for (int i = 0; i < prompts.length; i++) {
            System.out.print(prompts[i]);
            String answer = input.nextLine();
            if (answer.trim().toLowerCase().equals("!x")) {
                break;
            }
            answers.add(answer);
        }

        if (answers.size() == prompts.length) {
            System.out.println("====================================================");
            System.out.println("You are about to create an event with the following details:");
            System.out.println("Event Date: " + date.toString());
            for (int i = 0; i < answers.size(); i++) {
                System.out.println(prompts[i] + answers.get(i));
            }
            System.out.println("Do you wish to continue [Y/N]?: ");

            String answer = input.nextLine().trim().toLowerCase();
            if (answer.equals("y")) {
                backend.addEvent(date, answers.get(0), answers.get(1), answers.get(2), answers.get(3));
            }
        }
    }

    /**
     * Run the Searching Mode of the Venue Scheduler. Users can search for events by either name or date depending on the toggle.
     */
    public void runSearchingMode() {
        List<EventInterface> searchResults = new ArrayList<EventInterface>();
        boolean searchByDate = true;
        while (true) {
            System.out.println();
            if (searchByDate) {
                System.out.println("=========SEARCHING MODE: DATE (MM/dd/yyyy)==========");
            } else {
                System.out.println("================SEARCHING MODE: NAME================");
            }
            System.out.println("====================================================");
            if (searchResults.size() > 0) {
                for (int i = 0; i < searchResults.size(); i++) {
                    EventInterface event = searchResults.get(i);
                    System.out.println((i+1) + ": " + event.getDate() + " - " + event.getName());
                }
            }
            else {
                System.out.println("No search results.");
            }
            System.out.println("====================================================");

            if (searchByDate) {
                System.out.println("Type a date in the format specified in the title (e.g: 01/01/2021) to search by date.");
                System.out.println("Type '!n' to toggle searching by name.");
            }
            else {
                System.out.println("Type the name of the event to search by name.");
                System.out.println("Type '!d' to toggle searching by date.");
            }
            System.out.println("Type '!info <index>' to get more information about the event at the specified index.");
            System.out.println("Type '!x' to return to the main mode.");

            String command = input.nextLine().trim();
            String commandLowerCase = command.toLowerCase();
            if (commandLowerCase.equals("!d")) {
                searchByDate = true;
                searchResults.clear();
            }
            else if (commandLowerCase.equals("!n")) {
                searchByDate = false;
                searchResults.clear();
            }
            else if (commandLowerCase.matches("^!info \\d+\\s*$")) {
                String[] parts = commandLowerCase.split(" ");
                int index = Integer.parseInt(parts[1])-1;
                if (index >= 0 && index < searchResults.size()) {
                    displayEventInfo(searchResults.get(index));
                }
            }
            else if (commandLowerCase.equals("!x")) {
                break;
            }
            else if (searchByDate && command.matches("^\\d{2}/\\d{2}/\\d{4}\\s*$")) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date date = null;
                try {
                    date = formatter.parse(command);
                }
                catch (ParseException ex) {
                    System.out.println("An unexpected parse exception occurred.");
                }
                searchResults = backend.getEventsByDate(date);
            }
            else if (!searchByDate) {
                searchResults = backend.getEventsByName(command);
            } else {
                System.out.println("Invalid search input.");
            }
        }
    }

    /**
     * An extension of Searching Mode called when a user wishes to see more about an event by typing the expansion input string described in the mode.
     * @param event
     */
    public void displayEventInfo(EventInterface event) {
        System.out.println();
        System.out.println("===========SEARCHING MODE: EVENT EXPANSION==========");
        System.out.println("====================================================");
        System.out.println("Event Name: " + event.getName());
        System.out.println("Event Date: " + event.getDate());
        System.out.println("Event Description: " + event.getDescription());
        System.out.println("Group Name: " + event.getGroupName());
        System.out.println("Venue: " + event.getVenueSetting());
        System.out.println("====================================================");
        System.out.println("Enter anything to continue.");
        input.nextLine();
    }

    /**
     * Instantiate a new frontend and backend, and run the frontend
     * @param args the arguments, if any
     */
    public static void main(String[] args) {
        Frontend frontend = new Frontend(new BackendImplementation());
        frontend.run();
    }
}