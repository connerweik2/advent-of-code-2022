import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Puzzle2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        File inputFile = new File("./input.txt");
        
        Scanner in = new Scanner(inputFile);

        while (in.hasNext()) {
            lines.add(in.nextLine().trim());
        }

        in.close();

        int numRows = lines.size();
        int numCols = lines.get(0).length();

        int[][] grid = new int[numRows][numCols];

        boolean[][] visited = new boolean[numRows][numCols];

        int sRow = -1, sCol = -1;
        int eRow = -1, eCol = -1;

        Queue<int[]> queue = new LinkedList<>();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                char c = lines.get(row).charAt(col);
                if (c == 'S') {
                    sRow = row;
                    sCol = col;
                    grid[row][col] = (int)('a');
                } else if (c == 'E') {
                    eRow = row;
                    eCol = col;
                    grid[row][col] = (int)('z');
                } else if (c == 'a') {
                    queue.offer(new int[] {row, col});
                    visited[row][col] = true;
                    grid[row][col] = (int)('a');
                } else {
                    grid[row][col] = (int)(c);
                }
            }
        }

        int result = 1;

        int[][] dir = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        visited[sRow][sCol] = true;
        queue.offer(new int[] {sRow, sCol});

        while (true) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] polled = queue.poll();
                int row = polled[0];
                int col = polled[1];
                for (int j = 0; j < dir.length; j++) {
                    int newRow = row + dir[j][0];
                    int newCol = col + dir[j][1];
                    if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols &&
                    !visited[newRow][newCol] && grid[newRow][newCol] <= grid[row][col] + 1) {
                        if (newRow == eRow && newCol == eCol) {
                            System.out.println(result);
                            return;
                        }
                        visited[newRow][newCol] = true;
                        queue.offer(new int[] {newRow, newCol});
                    }
                }
            }
            result++;
        }
    }
}