import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Puzzle1Solution {

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("./input.txt");
        Scanner in = new Scanner(inputFile);

        // The highest number of calories any Elf is carrying.
        int max = 0;

        while (in.hasNextLine()) {
            int thisElfCalories = 0;
            while (in.hasNextLine()) {
                String thisLine = in.nextLine();
                try {
                    // If this is an integer, updateThisElfCalories and update max accordingly.
                    thisElfCalories += Integer.parseInt(thisLine);
                    if (thisElfCalories > max) {
                        max = thisElfCalories;
                    }

                } catch (NumberFormatException e) {
                    // Otherwise, this is a blank line, which means we reached the end of an elf's list.
                    // Reset thisElfCalories for the next elf.
                    thisElfCalories = 0;
                }
            }
        }

        in.close();

        // Print the result.
        System.out.println(max);
    }

}