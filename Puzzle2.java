import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle2 {
    public static int scenicScore(int targetRow, int targetCol, int[][] grid) {
        int distanceUp = 0, distanceDown = 0, distanceLeft = 0, distanceRight = 0;
        for (int row = targetRow - 1; row >= 0; row--) {
            distanceUp++;
            if (grid[row][targetCol] >= grid[targetRow][targetCol]) {
                break;
            }
        }
        for (int row = targetRow + 1; row < grid.length; row++) {
            distanceDown++;
            if (grid[row][targetCol] >= grid[targetRow][targetCol]) {
                break;
            }
        }
        for (int col = targetCol - 1; col >= 0; col--) {
            distanceLeft++;
            if (grid[targetRow][col] >= grid[targetRow][targetCol]) {
                break;
            }
        }
        for (int col = targetCol + 1; col < grid[0].length; col++) {
            distanceRight++;
            if (grid[targetRow][col] >= grid[targetRow][targetCol]) {
                break;
            }
        }
        return distanceUp * distanceDown * distanceLeft * distanceRight;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();

        File inputFile = new File("./input.txt");
        Scanner in = new Scanner(inputFile);

        while (in.hasNext()) {
            lines.add(in.next());
        }

        in.close();

        int[][] grid = new int[lines.size()][lines.get(0).length()];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = (int)(lines.get(row).charAt(col) - '0');
            }
        }

        int result = 0;

        for (int targetRow = 0; targetRow < grid.length; targetRow++) {
            for (int targetCol = 0; targetCol < grid[0].length; targetCol++) {
                int score = scenicScore(targetRow, targetCol, grid);
                if (score > result) {
                    result = score;
                }
            }
        }

        System.out.println(result);
    }
}