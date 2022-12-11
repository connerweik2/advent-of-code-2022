import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Puzzle1 {

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("./input.txt");
        Scanner in = new Scanner(inputFile);

        int totalScore = 0;

        while (in.hasNextLine()) {
            String lineTrimmed = in.nextLine().trim();
            
            int opponentChoice = (int)(lineTrimmed.charAt(0) - 'A');
            int myChoice = (int)(lineTrimmed.charAt(2) - 'X');

            totalScore += myChoice + 1;

            if (opponentChoice == myChoice) {
                totalScore += 3;
            }
            else if ((opponentChoice + 1) % 3 == myChoice) {
                totalScore += 6;
            }
        }

        System.out.println(totalScore);

        in.close();

    }

}