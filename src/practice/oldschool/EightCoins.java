package practice.oldschool;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by yichen on 10/6/15.
 */
public class EightCoins {

    final int NUM = 8;

    int[] coins;

    public EightCoins() {
        coins = new int[NUM];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = 10;
        }
    }

    public void setFakeCoinWeight(int weight) {
        Random random = new Random();
        coins[random.nextInt(NUM)] = weight;

    }

    public void findFakeCoin() {
        int n = compareCoins(0, 1, 2, 3, 4, 5);
        if (n != 0) {
            n = compareCoins(0, 1, 3, 4);
            if (n != 0) {
                n = compareCoins(0, 3);
                if (n != 0) {
                    // fake coin is either 0 or 3
                    finalCompare(0, 3, 1);

                }
                else {
                    // fake coin is either 1 or 4
                    finalCompare(1, 4, 0);

                }

            }
            else {
                // fake coin is either 2 or 5
                finalCompare(2, 5, 0);

            }
        }
        else {
            // fake coin is either 6 or 7
            finalCompare(6, 7, 0);

        }
    }

    public void finalCompare(int a, int b, int c) {
        if (compareCoins(a, b) > 0) {
            if (compareCoins(a, c) != 0) {
                System.out.println("Fake coin " + a + " is heavier");
            }
            else {
                System.out.println("Fake coin " + b + " is lighter");
            }
        }
        else {
            if (compareCoins(a, c) != 0) {
                System.out.println("Fake coin " + a + " is lighter");
            }
            else {
                System.out.println("Fake coin " + b + " is heavier");
            }
        }
    }

    public int compareCoins(int a, int b) {
        return coins[a] - coins[b];
    }

    public int compareCoins(int a, int b, int c, int d) {
        return (coins[a] + coins[b] - coins[c] - coins[d]);
    }

    public int compareCoins(int a, int b, int c, int d, int e, int f) {
        return (coins[a] + coins[b] + coins[c] - coins[d] - coins[e] - coins[f]);
    }

    public void printAllCoins() {
        for (int i = 0; i < coins.length; i++) {
            System.out.print(String.format("%02d ", coins[i]));
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the weight of the fake coin: ");
        int weight = scanner.nextInt();
        scanner.close();

        EightCoins eightCoins = new EightCoins();
        eightCoins.setFakeCoinWeight(weight);
        eightCoins.findFakeCoin();
        eightCoins.printAllCoins();

    }
}
