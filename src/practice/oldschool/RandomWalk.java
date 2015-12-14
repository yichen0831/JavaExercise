package practice.oldschool;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by yichen on 10/5/15.
 */
public class RandomWalk {
    final int MAXSTEPS = 50_000;
    int[][] tile;
    int totalSteps = 0;
    int tilesLeft;

    int[] dirX = {0, 1, 1, 1, 0, -1, -1, -1};
    int[] dirY = {1, 1, 0, -1,-1, -1, 0, 1};


    private void run() {
        Scanner scanner = new Scanner(System.in);

        int m, n;

        int currentX, currentY;

        System.out.print("Enter m: ");
        m = scanner.nextInt();
        if (m > 40) {
            m = 40;
        }

        System.out.print("Enter n: ");
        n = scanner.nextInt();
        if (n > 20) {
            n = 20;
        }

        System.out.print("Enter startX: ");
        currentX = scanner.nextInt();
        if(currentX >= m) {
            currentX = m-1;
        }

        System.out.print("Enter startY: ");
        currentY = scanner.nextInt();
        if(currentY >= n) {
            currentY = n-1;
        }

        scanner.close();
        
        tile = new int[m][n];
        tilesLeft = m * n;


        Random random = new Random();

        tile[currentX][currentY] += 1;
        tilesLeft -=1;

        int nextDir;
        while(tilesLeft > 0 && totalSteps < MAXSTEPS) {
            nextDir = random.nextInt(8);

            if (currentX + dirX[nextDir] < 0 || currentX + dirX[nextDir] >= m) {
                continue;
            }
            if (currentY + dirY[nextDir] < 0 || currentY + dirY[nextDir] >=n) {
                continue;
            }

            currentX += dirX[nextDir];
            currentY += dirY[nextDir];

            totalSteps += 1;
            if (tile[currentX][currentY] == 0) {
                tilesLeft -= 1;
            }

            tile[currentX][currentY] += 1;
        }
    }

    public void printResult() {
        for (int i = 0; i < tile.length; i++) {
            for (int j = 0; j < tile[i].length; j++) {
                System.out.printf("%4d ", tile[i][j]);
            }
            System.out.println();
        }

        System.out.println("Total steps: " + totalSteps);
    }

    public static void main(String[] args) {
        RandomWalk randomWalk = new RandomWalk();
        randomWalk.run();
        randomWalk.printResult();
    }


}
