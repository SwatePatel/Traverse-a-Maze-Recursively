//Student: Swate Patel
//Section: 008

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    static boolean solveMaze(int row, int column, char maze[][]) {
        if (row < 0 || column < 0) {
            return false;
        }
        if (row >= maze.length || column >= maze[row].length) {
            return false;
        }
        if (maze[row][column] == 'X' || maze[row][column] == '.' || maze[row][column] == '+') {
            return false;
        }

        if (maze[row][column] == '-') {
            return true;
        }
        maze[row][column] = '+';
        boolean solved = false;
        if (solveMaze(row + 1, column, maze) || solveMaze(row, column + 1, maze) || solveMaze(row - 1, column, maze) || solveMaze(row, column - 1, maze)) {
            solved = true;
        }
        if (!solved) {
            maze[row][column] = '.';
        }
        return solved;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        String filename = "maze.dat"; //gets name of file
        scan = new Scanner(new File(filename));

        int rows = scan.nextInt();
        int columns = scan.nextInt();
        scan.nextLine();
        char[][] maze = new char[rows][columns];

        int numberRow = 0;
        int numberColumn = 0;

        for (int rowCount = 0; rowCount < rows; rowCount++) {
            String line = scan.nextLine();
            for (int columnCount = 0; columnCount < columns; columnCount++) {
                maze[rowCount][columnCount] = line.charAt(columnCount);
                if (maze[rowCount][columnCount] == '+') {
                    numberRow = rowCount;
                    numberColumn = columnCount;
                }
            }
        }
        maze[numberRow][numberColumn] = 'y';
        if (solveMaze(numberRow, numberColumn, maze)) {
            
            System.out.println("Maze is solved!");
            for (char[] row : maze) {
                System.out.println(new String(row));
            }
        } else {
            System.out.println("The Maze is unsolvable.");
        }
    }
}
