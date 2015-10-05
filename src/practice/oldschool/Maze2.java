package practice.oldschool;

/**
 * Created by yichen on 10/5/15.
 */
public class Maze2 {

    static boolean foundExit = false;

    static int[][] maze = {
            {2, 2, 0, 0, 0,},
            {0, 0, 0, 2, 0,},
            {2, 0, 2, 0, 0,},
            {2, 0, 0, 0, 2,},
            {0, 0, 2, 0, 0,},
    };


    private static boolean visit_maze(int[][] map, int x0, int y0, int x1, int y1) {
        if(x0 < 0 || x0 > map.length-1) {
            return false;
        }

        if(y0 < 0 || y0 > map[0].length-1) {
            return false;
        }

        if (map[x0][y0] == 0) {
            map[x0][y0] = 1;

            if (map[x1][y1] == 1) {
                foundExit = true;
                printMaze();
            }

            else {
                visit_maze(map, x0+1, y0, x1, y1);
                visit_maze(map, x0-1, y0, x1, y1);
                visit_maze(map, x0, y0+1, x1, y1);
                visit_maze(map, x0, y0-1, x1, y1);
            }
            map[x0][y0] = 0;
        }

        return map[x1][y1] == 1;
    }


    private static void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                switch (maze[i][j]) {
                    case 0:
                        System.out.print(" ");
                        break;
                    case 1:
                        System.out.print("+");
                        break;
                    case 2:
                        System.out.print("M");
                }
            }
            System.out.println();
        }

        System.out.println("=================================");
    }

    public static void main(String[] args) {

        printMaze();

        visit_maze(maze, 4, 0, 0, 4);

        if(!foundExit) {
            System.out.println("Cannot find exit.");
        }


    }
}
