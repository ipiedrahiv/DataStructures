import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BruteSchedule {

    private String location;
    private int n; //Total number of activities
    private ArrayList<Activity> act = new ArrayList<Activity>(); //Activity storage
    private ArrayList<ArrayList<Activity>> combinations = new ArrayList<ArrayList<Activity>>();

    public BruteSchedule( ArrayList<Activity> act) {
        this.act = act;
    }

    public void bruteForceSolution() throws IOException{
        ArrayList<Activity> answer = bestSchedule();
        for(int i = 0 ; i < answer.size() ; i++) {
            System.out.println(answer.get(i).toString());
        }
    }

    /**
     * This method created the power set of the set of all the activities. The algorithm implemented here is an adaptation of William
     * Fiest's, which can be found at https://github.com/williamfiset/Algorithms/blob/master/com/williamfiset/algorithms/other/PowerSet.java
     *
     * This method will use recursion to generate a tree considering both the case in which an element is used and the one in which it is
     * not used. The boolean array used keeps track of this.
     *
     * @param index Current index being evaluated in the recursion, starts at 0
     * @param used Aux boolean array of the length = number of activities
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
     * This method uses the powerSetRecursive method to generate all the possible schedules and then compares the sizes of all the valid
     * schedules to select the longest one, which would be the optimal one.
     */
    public ArrayList<Activity> bestSchedule() {
        boolean[] used = new boolean[this.act.size()];
        powerSetRecursiveList(0, used);
        ArrayList<Activity> possible_answer = new ArrayList<>();
        for(int current_schedule=0 ; current_schedule<combinations.size() ; current_schedule++) {
            if(validSchedule(combinations.get(current_schedule))) {
                if(combinations.get(current_schedule).size() > possible_answer.size()) {
                    possible_answer = combinations.get(current_schedule);
                }
            }
        }
        return possible_answer;
    }

    /**
     * This method verifies if a given schedule is valid or not. It first sorts the schedule by end time, and then iterates it to ensure
     * that no activity in it begins before the previous one has ended. If at any point two activities collide, the schedule is deemed
     * not-valid.
     *
     * If the program reaches the end, the schedule is considered valid.
     */

    public boolean validSchedule(ArrayList<Activity> schedule) {
        schedule.sort(new EndSorter());
        for(int current_activity = 0 ; current_activity < schedule.size() ; current_activity++) {
            for(int next_activity = current_activity+1 ; next_activity < schedule.size() ; next_activity++) {
                if(schedule.get(next_activity).start < schedule.get(current_activity).end) {
                    return false;
                }
            }
        }
        return true;
    }
}
