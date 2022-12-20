import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Puzzle2 {

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("./input.txt");
        Scanner in = new Scanner(inputFile);

        char[] input = in.next().toCharArray();

        int[] freq = new int[256];

        for (int i = 0; i < 13; i++) {
            freq[input[i]]++;
        }
        for (int i = 13; i < input.length; i++) {
            freq[input[i]]++;
            boolean allUnique = true;
            for (int j = 0; j < 256; j++) {
                if (freq[j] > 1) allUnique = false;
            }
            if (allUnique) {
                System.out.println(i+1);
                break;
            }
            freq[input[i-13]]--;
        }
        
        in.close();
    }
}