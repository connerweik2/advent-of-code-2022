import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Puzzle1 {

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("./input.txt");
        Scanner in = new Scanner(inputFile);

        int totalScore = 0;

        while (in.hasNextLine()) {
            char myChoice = in.next().charAt(0);
            char opponentChoice = in.next().charAt(0);

            // 1 for Rock, 2 for Paper, 3 for Scissors
            totalScore += (int)(myChoice - 'A' + 1);

            if (myChoice == opponentChoice) {
                totalScore += 3;
            }
            else if (myChoice == 'A' && opponentChoice == 'Z' ||
                     myChoice == 'B' && opponentChoice == 'X' ||
                     myChoice == 'C' && opponentChoice == 'Y'
                    ) {
                totalScore += 6;
            }
        }

        System.out.println(totalScore);

        in.close();

    }

}