public class Control{
    private int[][] maze = { { 0, 0, 0, 0, 0, 0, 1, 1, 1, 0 }, { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 1, 1, 0, 1, 0, 0, 0 }, { 1, 1, 0, 1, 0, 1, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } };

    public String calculate() {
        String result = "";
        int[][] tempMaze = new int[10][10];
        for (int i = 1; i < maze.length - 1; i++) {
            for (int j = 1; j < maze[0].length - 1; j++) {
                int counter = 0;
                counter -= maze[i][j];

                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        counter += maze[k + i][l + j];
                    }
                }

                if (maze[i][j] == 1) {
                    if (counter < 2) {
                        tempMaze[i][j] = 0;
                    } else if (counter == 2 || counter == 3) {
                        tempMaze[i][j] = 1;
                    } else if (counter > 3) {
                        tempMaze[i][j] = 0;
                    }
                } else if (maze[i][j] == 0) {
                    if (counter == 3) {
                        tempMaze[i][j] = 1;
                    }
                } else {
                    tempMaze[i][j] = maze[i][j];
                }
            }
        }

        for (int i = 0; i < tempMaze.length; i++) {
            for (int j = 0; j < tempMaze.length; j++) {
                if (tempMaze[i][j] == 0) {
                    result += "\u25FB";
                } else {
                    result += "\u2B1B";
                }

                maze[i][j] = tempMaze[i][j];
            }
            result += "\n";
        }

        result += "-----------------------------";
        result += "\n";

        return result;
    }
}