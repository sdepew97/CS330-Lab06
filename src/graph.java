import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class graph {
    private static float N = 50000; //starting value
    private static float numLines = 42613;

    public static void main(String args[]) {
        //read in that
        int numberLines;

        while ((numberLines = readInData(N)) != 0) {
            System.out.println("At N = " + (int) N + " we get " + numberLines + " collisions and a load factor of " + (numLines / N));
            N += 50000;
        }

        System.out.println("At N = " + (int) N + " we get " + numberLines + " collisions and a load factor of " + (numLines / N));
    }

    //used similar code to tutorial found on https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html to understand reading from URL's
    private static int readInData(float N) {
        int[] collisions = new int[(int) N];
        try {
            URL url = new URL("https://cs.brynmawr.edu/Courses/cs330/spring2018/uszipcodes.csv");

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine = in.readLine(); //read in and remove the first line
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

        return collisionsNumber(collisions, (int) N);
    }

    private static int collisionsNumber(int[] collisions, int N) {
        int maxCollisions = 0;
        int entriesWithCollisions = 0;
        int totalCollisions = 0;
        for (int i = 0; i < collisions.length; i++) {
            if (collisions[i] > 1) {
                totalCollisions += (collisions[i] - 1);
                entriesWithCollisions++;
                if(collisions[i]-1 > maxCollisions) {
                    maxCollisions = collisions[i]-1;
                }
            }
        }
        System.out.println("Number of entries with collisions " + entriesWithCollisions + " " +
                "and max collisions in one spot, " + maxCollisions + " overall for N = " + N);
        return totalCollisions;
    }
}
