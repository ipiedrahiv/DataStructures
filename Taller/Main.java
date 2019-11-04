public class Main {

    public static void main(String[] args) {

        PotsOfGold pots = new PotsOfGold();

        int[] coins = {4, 6, 2, 3, 7, 3, 9, 6, 4, 6, 2, 4, 8, 99, 8, 4, 2, 35};

        System.out.println(pots.optimizeCoinsExponential(coins, 0, coins.length - 1));

        int[][] solved_sub_problems = new int[coins.length][coins.length];

        System.out.println(pots.optimizeCoinsDynamic(coins, 0, coins.length - 1, solved_sub_problems));

    }

}
