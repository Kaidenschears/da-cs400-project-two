// --== CS400 File Header Information ==--
// Name: Ji Lau
// Email: jlau24@wisc.edu
// Team: DA: Red
// Role: Front End Developer
// TA: Dan Kiel
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.not;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

/** The test suite for the frontend implementation
 * @author Ji Lau
 */
public class FrontEndDeveloperTests {
    private BackendInterface backend;

    @BeforeEach
    public void setUp() {
        backend = new BackendDummy();
    }
    
    /**
     * Tests to see if the application will quit if the user inputs 'x.' This test will timeout after 15 seconds - if a timeout occurs, the application did not exit and
     *      the test fails.
     */
    @Test
    @Timeout(value = 15, unit = TimeUnit.SECONDS)
    public void testXToQuitApplicationFromMainMode() {
        PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
        String input = "x";
        InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStreamSimulator);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Frontend frontend = new Frontend(backend);
        frontend.run();
        System.setOut(standardOut);
        System.setIn(standardIn);
    }
    
    /**
     * Tests to see that the application will switch to planning mode by typing 'p'.
     */
    @Test
    public void testPSwitchToPlanningMode() {
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        String input = "p" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
        InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStreamSimulator);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Frontend frontend = new Frontend(backend);
        frontend.run();

        System.setOut(standardOut);
        System.setIn(standardIn);
        
        String appOutput = outputStreamCaptor.toString(); 
        assertThat(appOutput, CoreMatchers.containsString("===================PLANNING MODE===================="));
        assertThat(appOutput, CoreMatchers.containsString("Type '<index>' to scroll to a certain index."));
        assertThat(appOutput, CoreMatchers.containsString("Type 'p <index>' to pick an available time by its index."));
        assertThat(appOutput, CoreMatchers.containsString("Type 'x' to return to the Main Mode."));
    }

    /**
     * Tests to see that that after selecting a date that there are prompts for event name, description, group name, and venue.
     */
    @Test
    public void testPlanningModePromptsUserForEventInfo() {
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        String input = "";
        input += "p" + System.lineSeparator(); 
        input += "p 1" + System.lineSeparator(); 
        input += "Mock Name" + System.lineSeparator();
        input += "Mock Description" + System.lineSeparator();
        input += "Mock Group Name" + System.lineSeparator();
        input += "Mock Venue" + System.lineSeparator();
        input += "Y" + System.lineSeparator();
        input += "x" + System.lineSeparator();
        input += "x";
        InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStreamSimulator);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Frontend frontend = new Frontend(backend);
        frontend.run();

        System.setOut(standardOut);
        System.setIn(standardIn);
        
        String appOutput = outputStreamCaptor.toString(); 
        assertThat(appOutput, CoreMatchers.containsString("===================PLANNING MODE===================="));
        assertThat(appOutput, CoreMatchers.containsString("You may type '!x' at any moment to cancel and return to the date selection screen."));
        assertThat(appOutput, CoreMatchers.containsString("Event Name:"));
        assertThat(appOutput, CoreMatchers.containsString("Event Description:"));
        assertThat(appOutput, CoreMatchers.containsString("Group Name:"));
        assertThat(appOutput, CoreMatchers.containsString("Venue:"));
        assertThat(appOutput, CoreMatchers.containsString("You are about to create an event with the following details:"));
        assertThat(appOutput, CoreMatchers.containsString("Event Name: Mock Name"));
        assertThat(appOutput, CoreMatchers.containsString("Event Description: Mock Description"));
        assertThat(appOutput, CoreMatchers.containsString("Group Name: Mock Group Name"));
        assertThat(appOutput, CoreMatchers.containsString("Venue: Mock Venue"));
        assertThat(appOutput, CoreMatchers.containsString("Do you wish to continue [Y/N]?:"));
    }

    /**
     * Tests to see that the user will return back to date selection in planning mode if they type '!x'. Additionally, if any of the input prompts other than
     *      event name (Event Description, etc.) appear, the test will fail.
     */
    @Test
    public void testPlanningModeExitEventInput() {
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;
        String input = "";
        input += "p" + System.lineSeparator(); 
        input += "p 1" + System.lineSeparator(); 
        input += "!x" + System.lineSeparator(); 
        input += "x" + System.lineSeparator();
        input += "x";
        InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStreamSimulator);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Frontend frontend = new Frontend(backend);
        frontend.run();

        System.setOut(standardOut);
        System.setIn(standardIn);
        
        String appOutput = outputStreamCaptor.toString(); 
        assertThat(appOutput, CoreMatchers.containsString("===================PLANNING MODE===================="));
        assertThat(appOutput, CoreMatchers.containsString("You may type '!x' at any moment to cancel and return to the date selection screen."));
        assertThat(appOutput, CoreMatchers.containsString("Event Name:"));
        assertThat(appOutput, not(CoreMatchers.containsString("Event Description:")));
        assertThat(appOutput, not(CoreMatchers.containsString("Group Name:")));
        assertThat(appOutput, not(CoreMatchers.containsString("Venue:")));
        assertThat(appOutput, not(CoreMatchers.containsString("You are about to create an event with the following details:")));
        assertThat(appOutput, not(CoreMatchers.containsString("Do you wish to continue [Y/N]?:")));
    }

    /**
     * Tests to see that the application will switch to searching mode when the user types 's'.
     */
    @Test
    public void testSSwitchToSearchingMode() {
        PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
        String input = "";
        input += "s" + System.lineSeparator();
        input += "!x" + System.lineSeparator();
        input += "x";
        InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStreamSimulator);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Frontend frontend = new Frontend(backend);
        frontend.run();

        System.setOut(standardOut);
        System.setIn(standardIn);
        
        String appOutput = outputStreamCaptor.toString(); 
        assertThat(appOutput, CoreMatchers.containsString("=========SEARCHING MODE: DATE (MM/dd/yyyy)=========="));
        assertThat(appOutput, CoreMatchers.containsString("Type '!n' to toggle searching by name."));
        assertThat(appOutput, CoreMatchers.containsString("Type '!info <index>' to get more information about the event at the specified index."));
        assertThat(appOutput, CoreMatchers.containsString("Type '!x' to return to the main mode."));
    }

    /**
     * Tests to see that the application properly toggles between searching by name and searching by date while in searching mode when the user types '!n' and '!d'.
     */
    @Test
    public void testSearchingModeToggleSearchByName() {
        PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
        String input = "";
        input += "s" + System.lineSeparator(); 
        input += "!n" + System.lineSeparator();
        input += "!d" + System.lineSeparator();
        input += "!x" + System.lineSeparator();
        input += "x";
        InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStreamSimulator);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Frontend frontend = new Frontend(backend);
        frontend.run();

        System.setOut(standardOut);
        System.setIn(standardIn);
        
        String appOutput = outputStreamCaptor.toString(); 
        assertThat(appOutput, CoreMatchers.containsString("=========SEARCHING MODE: DATE (MM/dd/yyyy)=========="));
        assertThat(appOutput, CoreMatchers.containsString("================SEARCHING MODE: NAME================"));
    }
}

