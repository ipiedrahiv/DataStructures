import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BruteSchedule {

    private String location;
    private int n; //Total number of activities
    public ArrayList<Activity> act = new ArrayList<Activity>(); //Activity storage
    public ArrayList<ArrayList<Activity>> combinations = new ArrayList<ArrayList<Activity>>();

    public BruteSchedule(String location) {
        this.location = location;
    }

    public void bruteForceSolution() throws IOException{
        readFile();
        ArrayList<Activity> answer = bestSchedule();
        for(int i = 0 ; i < answer.size() ; i++) {
            System.out.println(answer.get(i).toString());
        }
    }

    /**
     * This method reads the information provided by a .txt document with the following format
     * ID DHHMM DHHMM DESCRIPTION
     * Creates an Activity object for each read line, adds them to act, and increases the number of activities by one.
     *
     * @exception IOException, if the file is not found or can not be read.
     */
    public void readFile() throws IOException{
        String info;
        FileReader f = new FileReader(location);
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
     *This method created the power set of the set of all the activities.
     */
    private void powerSetRecursiveList(int index, boolean[] used) {
        if(index == act.size()) {
            ArrayList<Activity> combination = new ArrayList<>();

            for(int i = 0 ; i < act.size() ; i++) {
                if(used[i]) {
                    combination.add(act.get(i));
                }
            }

            combinations.add(combination);
        }else {
            used[index] = true;
            powerSetRecursiveList(index + 1, used);

            used[index] = false;
            powerSetRecursiveList(index + 1, used);
        }
    }

    /**
     *
     */
    public ArrayList<Activity> bestSchedule() {
        boolean[] used = new boolean[this.act.size()];
        powerSetRecursiveList(0, used);
        ArrayList<Activity> ans = new ArrayList<>();
        for(int i=0 ; i<combinations.size() ; i++) {
            if(validSchedule(combinations.get(i))) {
                if(combinations.get(i).size() > ans.size()) {
                    ans = combinations.get(i);
                   // System.out.println("-"+ans.size()+"-");
                }
            }
        }
        return ans;
    }

   public boolean validSchedule(ArrayList<Activity> schedule) {
        sortEnd(schedule, schedule.size());
        for(int i = 0 ; i < schedule.size() ; i++) {
            for(int j = i+1 ; j < schedule.size() ; j++) {
                //System.out.println("aaaaaa");
                if(schedule.get(j).start < schedule.get(i).end) {
                    return false;
                }
            }
        }
        return true;
   }

    /**
     * Sorts act by end time of each element, starting with the earliest end time and finishing with the latest end time.
     */
    private void sortEnd(ArrayList<Activity> schedule, int n) {
        for(int i = 0 ; i < (n-1) ; i++) {
            for(int j = 0 ; j < (n-i-1) ; j++) {
                if((schedule.get(j)).end > (schedule.get(j+1)).end) {
                    Activity temp1 = schedule.get(j);
                    Activity temp2 = schedule.get(j+1);
                    schedule.set(j, temp2);
                    schedule.set(j+1, temp1);
                }
            }
        }
    }

}
