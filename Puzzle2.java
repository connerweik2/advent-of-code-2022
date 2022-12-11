import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Puzzle2 {

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("./input.txt");
        Scanner in = new Scanner(inputFile);

        int totalScore = 0;

        while (in.hasNextLine()) {
            String lineTrimmed = in.nextLine().trim();
            
            int opponentChoice = (int)(lineTrimmed.charAt(0) - 'A');
            int roundResult = (int)(lineTrimmed.charAt(2) - 'X');

            if (roundResult == 1) totalScore += 3;
            if (roundResult == 2) totalScore += 6;
            totalScore += (opponentChoice + roundResult - 1 + 3) % 3 + 1;
        }

        System.out.println(totalScore);

        in.close();

    }

}