import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Puzzle2 {

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("./input.txt");
        Scanner in = new Scanner(inputFile);

        int result = 0;

        while (in.hasNextLine()) {
            // Use three sets, one for each Elf in the group.
            Set<Character> first = new HashSet<>();
            Set<Character> second = new HashSet<>();
            Set<Character> third = new HashSet<>();

            // Add every character from each string to the respective set.
            String line = in.nextLine().strip();
            for (int i = 0; i < line.length(); i++) {
                first.add(line.charAt(i));
            }
            line = in.nextLine().strip();
            for (int i = 0; i < line.length(); i++) {
                second.add(line.charAt(i));
            }
            line = in.nextLine().strip();
            for (int i = 0; i < line.length(); i++) {
                third.add(line.charAt(i));
            }
            
            // Check if each character in the first set is in all three sets.
            // Add the priority of the character that is in all three sets to result.
            for (Character c : first) {
                if (second.contains(c) && third.contains(c)) {
                    if (Character.isLowerCase(c)) result += (int)(c - 'a' + 1);
                    else result += (int)(c - 'A' + 27);
                }
            }
        }

        in.close();

        System.out.println(result);
    }

}