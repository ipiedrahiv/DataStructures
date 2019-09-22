import java.io.IOException;
import java.util.ArrayList;

public class Main {

    /**
     * @param args path to .txt file with data in args[0].
     * @throws IOException, if the file is not found or can not be read.
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Greedy algorithm");
        GreedySchedule greer = new GreedySchedule(args[0]);
        greer.greedySolution();

        System.out.println("Brute force algorithm");
        BruteSchedule brutus = new BruteSchedule(args[0]);
        brutus.bruteForceSolution();
    }
}
