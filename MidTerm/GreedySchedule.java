import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GreedySchedule {

    int n; //Total number of activities
    ArrayList<Activity> act = new ArrayList<Activity>(); //Activity storage

    public GreedySchedule() throws IOException{
        readFile();
        heuristic();
    }

    /**
     * This method reads the information provided by a .txt document with the following format
     * ID DHHMM DHHMM DESCRIPTION
     * Creates an Activity object for each read line, adds them to act, and increases the number of activities by one.
     */
    public void readFile() throws IOException{
        String info;
        FileReader f = new FileReader("/Users/ipiedrahiv/Desktop/src/data.txt");
        BufferedReader b = new BufferedReader(f);
        while((info = b.readLine())!=null) {
            String[] parts = info.split(" ");

            Activity a = new Activity (Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]);

            act.add(a);

            n++;
        }
        b.close();
    }

    /**
     * Sorts act by end time of each element, starting with the earliest end time and finishing with the latest end time.
     */
    public void sortEnd() {
        for(int i = 0 ; i < (n-1) ; i++) {
            for(int j = 0 ; j < (n-i-1) ; j++) {
                if((act.get(j)).end > (act.get(j+1)).end) {
                    Activity temp1 = act.get(j);
                    Activity temp2 = act.get(j+1);
                    act.set(j, temp2);
                    act.set(j+1, temp1);
                }
            }
        }
    }

    /**
     * This heuristic is an adaptation of the one found in https://www.geeksforgeeks.org/activity-selection-problem-greedy-algo-1/
     * Once the activities have been sorted by end time, it will select the first one as part of the solution set, then for each remaining activity it will compare the
     * end time of the last item of the solution set (s) with the start time of the current activity (a), if the start time of a is greater or equal to the end time
     * of s, then a will be added to the solution set.
     *
     * The proof of optimality for this heuristic can be found at https://stackoverflow.com/questions/50863727/proof-of-optimality-in-activity-selection
     */
    public void heuristic() {
        sortEnd();
        int i = 0;
        int j = 1;

        System.out.println("ID and description of answer set");
        System.out.println(act.get(i).ID + " " +act.get(i).description);

        for(j = 1 ; j < n ; j++) {
            if(act.get(j).start >= act.get(i).end) {
                System.out.println(act.get(j).ID + " " +act.get(j).description);
                i = j;
            }
        }
    }

}
