import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GreedySchedule {

    private String location;
    private int n; //Total number of activities
    private ArrayList<Activity> act = new ArrayList<Activity>(); //Activity storage

    GreedySchedule(ArrayList<Activity> act){
        this.act = act;
        this.n = act.size();
    }

    public void greedySolution() throws IOException{
        //Sort act by end time
        act.sort(new EndSorter());
        //Use the heuristic and print the answer ArrayList
        for(Activity a  : heuristic()) {
            System.out.println(a.toString());
        }
    }

    /**
     * This heuristic is an adaptation of the one found in https://www.geeksforgeeks.org/c-program-for-activity-selection-problem-greedy-algo-1/
     * Once the activities have been sorted by end time, it will select the first one as part of the solution set, then for each remaining activity it will compare the
     * end time of the last item of the solution set (s) with the start time of the current activity (a), if the start time of a is greater or equal to the end time
     * of s, then a will be added to the solution set.
     *
     * The items in the list need to be sorted according to the order in which they end, from first to last. This is achieves by using
     * the sort method from the java ArrayList library.
     *
     * The proof of optimality for this heuristic can be found at https://en.wikipedia.org/wiki/Activity_selection_problem
     */
    private ArrayList<Activity> heuristic() {
        ArrayList<Activity> answer = new ArrayList<>();

        int last_selected_activity = 0;

        answer.add(act.get(last_selected_activity));
        //System.out.println("ID and description of answer set");
        //System.out.println(act.get(last_selected_activity).ID + " " +act.get(last_selected_activity).description);

        for(int current_activity = 1 ; current_activity < n ; current_activity++) {
            if(act.get(current_activity).getStart() >= act.get(last_selected_activity).getEnd()) {
                answer.add(act.get(current_activity));
                last_selected_activity = current_activity;
            }
        }
        return answer;
    }

}
