package practice.oldschool;

/**
 * Created by yichen on 10/5/15.
 */
public class KnightTour {

    final int SIZE = 8;

    int[][] board;

    int[] dirX = {1, 1, -1, -1, 2, 2, -2, -2};
    int[] dirY = {2, -2, 2, -2, 1, -1, 1, -1};

    int tilesLeft;

    int currentX;
    int currentY;

    private void run() {
        board = new int[SIZE][SIZE];

        tilesLeft = SIZE * SIZE;

        currentX = 5;
        currentY = 6;

        int index = 1;

        int nextDir = -1;
        int nextPossible = 999;

        while(tilesLeft > 0) {
            board[currentX][currentY] = index;
            tilesLeft--;
            index++;

            int tmpDir = -1;
            for (int i = 0; i < dirX.length; i++) {
                int x = currentX + dirX[i];
                int y = currentY + dirY[i];
                if ( x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
                    continue;
                }

                if (board[x][y] == 0) {
                    tmpDir = i;
                }

                int possible = possibleSteps(x, y);

                if (nextPossible > possible && possible > 0) {
                    nextPossible = possible;
                    nextDir = i;
                }
            }

            if (nextDir == -1) {
                nextDir = tmpDir;
            }

            if (nextDir != -1) {
                currentX += dirX[nextDir];
                currentY += dirY[nextDir];

                nextPossible = 999;
                nextDir = -1;

            }
            else {
                break;
            }

        }
    }

    private int possibleSteps(int x, int y) {
//        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
//            return 0;
//        }

        if (board[x][y] != 0) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < dirX.length; i++) {
            if (x + dirX[i] < 0 || x + dirX[i] >= SIZE) {
                continue;
            }
            if (y + dirY[i] < 0 || y + dirY[i] >= SIZE) {
                continue;
            }

            if (board[x+dirX[i]][y+dirY[i]] == 0) {
                result++;
            }
        }
        return result;
    }

    private void printResult() {
        if (tilesLeft != 0) {
            System.out.println("No validate result.");
        }

        System.out.println("Tiles left: " + tilesLeft);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                System.out.printf("%2d ", board[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        KnightTour knightTour = new KnightTour();
        knightTour.run();
        knightTour.printResult();

    }

}
