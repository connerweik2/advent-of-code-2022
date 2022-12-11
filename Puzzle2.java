import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Puzzle1 {

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("./input.txt");
        Scanner in = new Scanner(inputFile);

        int result = 0;

        while (in.hasNextLine()) {
            // Use a frequency map for characters.
            Map<Character, Integer> map  = new HashMap<>();
            // Done in groups of 3.
            String lineTrimmed = in.nextLine().trim();
            Set<Character> second = new HashSet<Character>();
            for (int i = 0; i < lineTrimmed.length() / 2; i++) {
                first.add(lineTrimmed.charAt(i));
            }
            for (int i = lineTrimmed.length() /  2; i < lineTrimmed.length(); i++) {
                second.add(lineTrimmed.charAt(i));
            }

            // Check if each character in the first set is in both sets.
            // Add the priority of the character that is in both sets to result.
            for (Character c : first) {
                if (second.contains(c)) {
                    if (Character.isLowerCase(c)) result += (int)(c - 'a' + 1);
                    else result += (int)(c - 'A' + 27);
                }
            }
        }

        System.out.println(result);

        in.close();

    }

}