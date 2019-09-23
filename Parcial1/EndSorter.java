import java.util.Comparator;

public class EndSorter implements Comparator<Activity>
{
    /**
     *This method is used in the .sort() method in order to compare the activities by end time
     */
    @Override
    public int compare(Activity a1, Activity a2) {
        return (a1.end).compareTo(a2.end);
    }
}
