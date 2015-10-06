package practice.oldschool;

/**
 * Created by yichen on 10/5/15.
 */
public class EightQueens {

    private final int SIZE = 8;

    private final int QUEENS = 8;

    private int[] horizontal;
    private int[] vertical;
    private int[] diagonal;
    private int[] antidiagonal;

    private Queen[] queens;

    private int totalAnswers = 0;

    class Queen {
        public int x;
        public int y;
    }


    public EightQueens() {
        horizontal = new int[SIZE];
        vertical = new int[SIZE];
        diagonal = new int[SIZE * 2];
        antidiagonal = new int[SIZE * 2];
        queens = new Queen[QUEENS];

        for (int i = 0; i < queens.length; i++) {
            queens[i] = new Queen();
        }
    }

    public void run() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                boolean foundSolution = check(i, j);
                if (foundSolution) {
                    printQueens();
                    totalAnswers++;
                }
            }
        }
    }

    public void reset() {
        for (Queen queen : queens) {
            queen.x = 0;
            queen.y = 0;
        }

        for (int i = 0; i < horizontal.length; i++) {
            horizontal[i] = 0;
        }

        for (int i = 0; i < vertical.length; i++) {
            vertical[i] = 0;
        }

        for (int i = 0; i < diagonal.length; i++) {
            diagonal[i] = 0;
        }

        for (int i = 0; i < antidiagonal.length; i++) {
            antidiagonal[i] =0;
        }
    }

    public boolean check(int startX, int startY) {
        reset();

        queens[0].x = startX;
        queens[0].y = startY;

        horizontal[startX] = 1;
        vertical[startY] = 1;
        diagonal[startX-startY+SIZE] = 1;
        antidiagonal[startX+startY] = 1;

        int currentQueen = 1;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (horizontal[i] != 0) {
                    break;
                }

                if (vertical[j] != 0) {
                    continue;
                }

//                if (horizontal[i] == 0 && vertical[j] == 0 && diagonal[i-j+SIZE-1] == 0 && antidiagonal[j-i+SIZE-1] == 0) {
                if (diagonal[i-j+SIZE] == 0 && antidiagonal[i+j] == 0) {

                    queens[currentQueen].x = i;
                    queens[currentQueen].y = j;

                    horizontal[i] = 1;
                    vertical[j] = 1;
                    diagonal[i-j+SIZE-1] = 1;
                    antidiagonal[i+j] = 1;

                    currentQueen++;
                }
                if (currentQueen >= QUEENS)
                    break;
            }
            if (currentQueen >= QUEENS)
                break;
        }

        return currentQueen == QUEENS;
    }


    public void printQueens() {
        System.out.println("========");

        for (Queen queen : queens) {
            System.out.println("(" + queen.x + ", " + queen.y + ")");
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                boolean foundQueen = false;
                for (Queen queen : queens) {
                    if (i == queen.x && j == queen.y) {
                        System.out.print("Q");
                        foundQueen = true;
                        break;
                    }
                }

                if (!foundQueen) {
                    System.out.print("-");
                }
            }
            System.out.println();
        }

    }


    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.run();
        System.out.println("Total answers: " + eightQueens.totalAnswers);
    }
}
