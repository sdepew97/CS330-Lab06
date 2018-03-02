import javax.sound.midi.SysexMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class Lab06 {
    public static boolean EXIT = false;
    private static String line = new String();
    private static int zipCode;
    private static String returnValue = new String();
    private static HashMap<Integer, String> data = new HashMap<>();

    public static void main(String args[]) {
        //read in that
        int numberLines = readInData();
        if (numberLines == -1) {
            System.out.println("I am sorry, but there was an error reading in the data.");
        } else {
            System.out.println("There are " + numberLines + " in the database.");
        }

        //allow the user to query the newly read in information
        while (!EXIT) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("If you would like to stop please enter 'exit'. Otherwise, please input a zip code: ");
            line = scanner.nextLine().trim();

            if (line.toUpperCase().equals("EXIT")) {
                System.out.println("Thank you! Have a good day.");
                EXIT = true;
            } else {
                zipCode = Integer.parseInt(line);
                if (data.containsKey(zipCode)) {
                    System.out.println("The zip code " + line + " belongs to " + data.get(zipCode) + ".");
                } else {
                    System.out.println("The zip code " + line + " is not in the database.");
                }
            }
        }
    }

    //used code/tutorial found on https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
    private static int readInData() {
        int numLines = -1; //default return value
        try {
            URL url = new URL("https://cs.brynmawr.edu/Courses/cs330/spring2018/testZip.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine = in.readLine();
            String[] tokens = inputLine.split(",");

            numLines = Integer.parseInt(tokens[0]);

            while ((inputLine = in.readLine()) != null) {
                tokens = inputLine.split(",");
                String stateAndTown = tokens[1] + ", " + tokens[2];
                System.out.println(stateAndTown);
                data.put(Integer.parseInt(tokens[0]), stateAndTown);
                System.out.println(data.get(Integer.parseInt(tokens[0])));
            }

            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return numLines;
    }
}
