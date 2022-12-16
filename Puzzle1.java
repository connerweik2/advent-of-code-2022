import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Puzzle1 {

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("./input.txt");
        Scanner in = new Scanner(inputFile);

        int result = 0;

        while (in.hasNextLine()) {
            String lineTrimmed = in.nextLine().trim();
            String[] lineSplit = lineTrimmed.split(",");
            int firstMin, firstMax, secondMin, secondMax;
            firstMin = Integer.parseInt(lineSplit[0].substring(0, lineSplit[0].indexOf("-")));
            firstMax = Integer.parseInt(lineSplit[0].substring(lineSplit[0].indexOf("-") + 1));
            secondMin = Integer.parseInt(lineSplit[1].substring(0, lineSplit[1].indexOf("-")));
            secondMax = Integer.parseInt(lineSplit[1].substring(lineSplit[1].indexOf("-") + 1));

            if (firstMin >= secondMin && firstMax <= secondMax ||
                secondMin >= firstMin && secondMax <= firstMax) {
                result++;
            }
        }

        in.close();

        System.out.println(result);
    }
}