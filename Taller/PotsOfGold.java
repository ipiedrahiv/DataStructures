/**
 *  The class PotsOfGold implements an answer to the programming exercise proposed in class.
 *
 * @author Isabel Piedrahita
 * @version 1.0
 * @since 4/11/2019
 */

public class PotsOfGold {

    public PotsOfGold() {};

    /**
     * This problem takes a naive minimax approach to solving the proposed problem, it has exponential time complexity, but has the
     * upper hand when it comes to auxiliary space complexity, which in this case is O(1);
     *
     * @param pots Array representing the pots of gold.
     * @param i Current from position.
     * @param j Current end position.
     * @return max number of coins that can be obtained by A.
     */
    public int optimizeCoinsExponential(int[] pots, int i, int j) {

        //System.out.println(j);
        //Base case, if there is only one pot it is the only possible choice.
        if(i == j) {
            return pots[i];
        }

        //If there are only two pots, the A should chose the one with the most coins.
        if(i + 1 == j) {
            return Math.max(pots[i], pots[j]);
        }

        int front_most_choice = pots[i] + Math.min(optimizeCoinsExponential(pots, i+2, j), optimizeCoinsExponential(pots, i+1, j-1));

        int back_most_choice = pots[j] + Math.min(optimizeCoinsExponential(pots, i+1, j-1), optimizeCoinsExponential(pots, i, j-2));

        return Math.max(front_most_choice, back_most_choice);

    }

    /**
     * This method does the same as the above one, but is has a smaller time complexity at the expense of auxiliary space complexity. It takes advantage of the fact that
     * this particular problem can be divided in sub-problems that can overlap, so it will not solve a sub-problem twice, it will simply store its value.
     *
     * @param pots Array representing pots of gold.
     * @param i Front position in the game.
     * @param j Back position in the game-
     * @param overlap Matrix that stores the solved problems.
     * @return max number of coins that can be obtained by A.
     */
    public int optimizeCoinsDynamic(int[] pots, int i, int j, int[][] overlap) {

        //Base case, if there is only one pot it is the only possible choice.
        if(i == j) {
            return pots[i];
        }

        //If there are only two pots, the A should chose the one with the most coins.
        if(i + 1 == j) {
            return Math.max(pots[i], pots[j]);
        }

        //If the sub-problem has not been solved before, solve it and store in in overlap
        if(overlap[i][j] == 0) {

            int front_most_choice = pots[i] + Math.min(optimizeCoinsDynamic(pots, i+2, j, overlap), optimizeCoinsDynamic(pots, i+1, j-1, overlap));

            int back_most_choice = pots[j] + Math.min(optimizeCoinsDynamic(pots, i+1, j-1, overlap), optimizeCoinsDynamic(pots, i, j-2, overlap));

            overlap[i][j] = Math.max(front_most_choice, back_most_choice);

        }

        return overlap[i][j];

    }

}
