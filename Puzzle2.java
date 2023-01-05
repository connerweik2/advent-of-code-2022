import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Puzzle2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        File inputFile = new File("./input.txt");
        
        Scanner in = new Scanner(inputFile);

        while (in.hasNext()) {
            lines.add(in.nextLine().trim());
        }

        in.close();

        int x  = 1;
        int i = 0;
        int cycle = 1;
        boolean inMiddleOfAddx = false;

        while (i < lines.size()) {
            String[] split = lines.get(i).split(" ");

            if (Math.abs(x - ((cycle - 1) % 40)) <= 1) {
                System.out.print("#");
            } else {
                System.out.print(".");
            }

            if (cycle % 40 == 0) {
                System.out.println();
            }

            if (split[0].equals("noop")) {
                i++;
            } else if (inMiddleOfAddx) {
                x += Integer.parseInt(split[1]);
                inMiddleOfAddx = false;
                i++;
            } else if (split[0].equals("addx")) {
                inMiddleOfAddx = true;
            }

            cycle++;
        }
    }
}