import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    /**
     * @param args path to .txt file with data in args[0].
     * @throws IOException, if the file is not found or can not be read.
     */
    public static void main(String[] args) throws IOException {
        //Read the data file and create an ArrayList<Activity> variable
        ArrayList<Activity> a = readFile(args[0]);

        //Use greedy algorithm
        System.out.println("--Greedy algorithm--");
        GreedySchedule greer = new GreedySchedule(a);
        greer.greedySolution();

        //Use brute force algorithm
        System.out.println("--Brute force algorithm--");
        BruteSchedule brutus = new BruteSchedule(a);
        brutus.bruteForceSolution();
    }

    /**
     * This method reads the information provided by a .txt document with the following format
     * ID DHHMM DHHMM DESCRIPTION
     * Creates an Activity object for each read line, adds them to act, and increases the number of activities by one.
     *
     * @exception IOException, if the file is not found or can not be read.
     */
    private static ArrayList<Activity> readFile(String location) throws IOException{
        ArrayList<Activity> activity_list = new ArrayList<>();
        String info;
        FileReader f = new FileReader(location);
        BufferedReader b = new BufferedReader(f);
        while((info = b.readLine())!=null) {
            String[] parts = info.split(" ");

            Activity a = new Activity (Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]);

            activity_list.add(a);

            //n++;
        }

        b.close();
        return activity_list;
    }
}
