import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle1 {
    public static boolean visible(int targetRow, int targetCol, int[][] grid) {
        boolean visibleUp = true, visibleDown = true, visibleLeft = true, visibleRight = true;
        for (int row = 0; row < targetRow; row++) {
            if (grid[row][targetCol] >= grid[targetRow][targetCol]) {
                visibleUp = false;
                break;
            }
        }
        for (int row = targetRow + 1; row < grid.length; row++) {
            if (grid[row][targetCol] >= grid[targetRow][targetCol]) {
                visibleDown = false;
                break;
            }
        }
        for (int col = 0; col < targetCol; col++) {
            if (grid[targetRow][col] >= grid[targetRow][targetCol]) {
                visibleLeft = false;
                break;
            }
        }
        for (int col = targetCol + 1; col < grid[0].length; col++) {
            if (grid[targetRow][col] >= grid[targetRow][targetCol]) {
                visibleRight = false;
                break;
            }
        }
        return visibleUp || visibleDown || visibleLeft || visibleRight;
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
                if (visible(targetRow, targetCol, grid)) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}