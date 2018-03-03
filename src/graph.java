import javax.sound.midi.SysexMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class graph {
    public static boolean EXIT = false;
    private static String line = new String();
    private static int zipCode;
    private static String returnValue = new String();
    private static HashMap<Integer, String> data = new HashMap<>();
    private static float N = 50000; //starting value
    private static float numLines = 42613;

    public static void main(String args[]) {
        //read in that
        int numberLines;

        while((numberLines = readInData(N)) != 0) {
            System.out.println("At N = " + (int) N + " we get " + numberLines + " collisions and a load factor of " + (numLines/N));
            N += 50000;
        }

        System.out.println("At N = " + (int) N + " we get " + numberLines + " collisions and a load factor of " + (numLines/N));
    }

    //used code/tutorial found on https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
    private static int readInData(float N) {
        int[] collisions = new int[(int) N];
        try {
//            URL url = new URL("https://cs.brynmawr.edu/Courses/cs330/spring2018/testZip.txt");
            URL url = new URL("https://cs.brynmawr.edu/Courses/cs330/spring2018/uszipcodes.csv");

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine = in.readLine();
            String[] tokens;


            while ((inputLine = in.readLine()) != null) {
                tokens = inputLine.split(",");
                collisions[(Math.abs(tokens[0].hashCode()) % (int) N)]++;
            }

            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return collisionsNumber(collisions);
    }

    private static int collisionsNumber(int[] collisions) {
        int totalCollisions = 0;
        for (int i = 0; i < collisions.length; i++) {
//            System.out.println("Row " + i + " and value of " + collisions[i]);
            if(collisions[i]>1) {
                totalCollisions += (collisions[i]-1);
            }
        }

        return totalCollisions;
    }
}
