import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Puzzle1 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        File inputFile = new File("./input.txt");
        
        Scanner in = new Scanner(inputFile);

        while (in.hasNext()) {
            lines.add(in.nextLine().trim());
        }

        in.close();
    }
}