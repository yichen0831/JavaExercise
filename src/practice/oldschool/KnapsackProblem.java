package practice.oldschool;

import java.util.ArrayList;

/**
 * Created by yichen on 10/15/15.
 *
 * KnapsackProblem
 */
public class KnapsackProblem {

    private int numbers = 5;
    private final String[] fruits = {"李子", "蘋果", "橘子", "草莓", "甜瓜"};
    private final int[] weights = {4, 5, 2, 1, 6};
    private final int[] prices = {4500, 5700, 2250, 1100, 6700};

    private final int maxWeight = 8;

    private int[] bestValues;
    private int[] bestItems;

    private ArrayList<Integer> result;

    public KnapsackProblem() {
        bestValues = new int[maxWeight+1];
        bestItems = new int[maxWeight+1];

        for (int i = 0; i < numbers; i++) {
            for (int w = weights[i]; w <= maxWeight; w++) {
                int preWeight = w - weights[i];
                int newValue = bestValues[preWeight] + prices[i];
                if (newValue > bestValues[w]) {
                    bestValues[w] = newValue;
                    bestItems[w] = i;
                }

            }
        }

        int weightLeft = maxWeight;
        result = new ArrayList<>();
        while (weightLeft > 0) {
            result.add(bestItems[weightLeft]);
            weightLeft -= weights[bestItems[weightLeft]];
        }

    }

    private void printResult() {
        System.out.println("Resut: ");
        int totalValue = 0;
        for (Integer i : result) {
            totalValue += prices[i];
            System.out.println(fruits[i] + " $" + prices[i] + ", Weight:" + weights[i]);
        }
        System.out.println("Total value: $" + totalValue);
    }

    private void printBestTable() {
        System.out.println("Best table: ");
        for (int i = 0; i < maxWeight+1; i++) {
            System.out.print(String.format("%5d ", i));
            System.out.print(String.format("%5d ", bestValues[i]));
            System.out.print(String.format("%5d ", bestItems[i]));
            System.out.println(fruits[bestItems[i]]);
        }
    }

    public static void main(String[] args) {
        KnapsackProblem kp = new KnapsackProblem();
        kp.printBestTable();
        kp.printResult();

    }
}
