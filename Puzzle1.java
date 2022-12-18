import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Puzzle1 {

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("./input.txt");
        Scanner in = new Scanner(inputFile);

        Stack<Character>[] stacks = new Stack[9];
        for (int i = 0; i < 9; i++) {
            stacks[i] = new Stack<>();
        }
        char[][] initialStacks = new char[8][9];
        for (int i = 0; i < 8; i++) {
            String line = in.nextLine();
            for (int j = 0; j < 9; j++) {
                initialStacks[i][j] = line.charAt(j * 4 + 1);
            }
        }

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 9; j++) {
                if (initialStacks[i][j] != ' ') {
                    stacks[j].push(initialStacks[i][j]);
                }
            }
        }

        in.nextLine();
        in.nextLine();

        while (in.hasNextLine()) {
            String[] split = in.nextLine().split(" ");
            int moveCount = Integer.parseInt(split[1]);
            int stackFrom = Integer.parseInt(split[3]) - 1;
            int stackTo = Integer.parseInt(split[5]) - 1;
            for (int i = 0; i < moveCount; i++) {
                stacks[stackTo].push(stacks[stackFrom].pop());
            }
        }

        
        in.close();
        
        for (int i = 0; i < 9; i++) {
            System.out.print(stacks[i].pop());
        }
        System.out.println();
    }
}