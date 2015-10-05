package practice.oldschool;

/**
 * Created by yichen on 10/4/15.
 */
public class Maze1 {

    static int[][] maze = {
            {2, 2, 2, 2, 0,},
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

        if(x0 == x1 && y0 == y1) {
            map[x0][y0] = 1;
            return true;
        }

        if (map[x0][y0] == 2 || map[x0][y0] == 1) {
            return false;
        }

        map[x0][y0] = 1;

        if(visit_maze(map, x0+1, y0, x1, y1) == false) {
            if(visit_maze(map, x0-1, y0, x1, y1) == false) {
                if(visit_maze(map, x0, y0+1, x1, y1) == false) {
                    if(visit_maze(map, x0, y0-1, x1, y1) == false) {
                        map[x0][y0] = 0;
                    }
                }
            }
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
    }


    public static void main(String[] args) {

        printMaze();

        boolean result = visit_maze(maze, 4, 0, 0, 4);

        System.out.println("===========================");
        if(result) {
            printMaze();
        }
        else {
            System.out.println("Cannot find the exit.");
        }
    }
}
