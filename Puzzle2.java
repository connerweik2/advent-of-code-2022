import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.awt.Point;

public class Puzzle2 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();

        File inputFile = new File("input.txt");
        Scanner in = new Scanner(inputFile);

        while (in.hasNext()) {
            lines.add(in.nextLine());
        }

        in.close();

        Set<Point> visited = new HashSet<>();

        ArrayList<Point> rope = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            rope.add(new Point(0, 0));
        }
        
        visited.add(new Point(0, 0));

        for (int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).split(" ");
            char direction = split[0].charAt(0);
            int distance = Integer.parseInt(split[1]);
            for (int j = 0; j < distance; j++) {
                processMove(rope, direction, visited);
            }
        }
        
        System.out.println(visited.size());
    }

    public static void processMove(ArrayList<Point> rope, char direction, Set<Point> visited) {
        if (direction == 'U') {
            rope.get(0).y--;
        } else if (direction == 'D') {
            rope.get(0).y++;
        } else if (direction == 'L') {
            rope.get(0).x--;
        } else if (direction == 'R') {
            rope.get(0).x++;
        }
        for (int i = 1; i < rope.size(); i++) {
            if (Math.abs(rope.get(i - 1).x - rope.get(i).x) <= 1 && Math.abs(rope.get(i - 1).y - rope.get(i).y) <= 1) {
                return;
            }
            if (rope.get(i - 1).x > rope.get(i).x) {
                rope.get(i).x++;
            } else if (rope.get(i - 1).x < rope.get(i).x) {
                rope.get(i).x--;
            }
            if (rope.get(i - 1).y > rope.get(i).y) {
                rope.get(i).y++;
            } else if (rope.get(i - 1).y < rope.get(i).y) {
                rope.get(i).y--;
            }
        }
        visited.add(new Point(rope.get(rope.size() - 1).x, rope.get(rope.size() - 1).y));
    }
}