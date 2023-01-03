import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.awt.Point;

public class Puzzle1 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();

        File inputFile = new File("input.txt");
        Scanner in = new Scanner(inputFile);

        while (in.hasNext()) {
            lines.add(in.nextLine());
        }

        in.close();

        Set<Point> visited = new HashSet<>();

        Point head = new Point(0, 0);
        Point tail = new Point(0, 0);
        
        visited.add(new Point(0, 0));

        for (int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).split(" ");
            char direction = split[0].charAt(0);
            int distance = Integer.parseInt(split[1]);
            for (int j = 0; j < distance; j++) {
                if (direction == 'U') {
                    head.y--;
                } else if (direction == 'D') {
                    head.y++;
                } else if (direction == 'L') {
                    head.x--;
                } else if (direction == 'R') {
                    head.x++;
                }
                updateTail(head, tail, visited);
            }
        }
        
        System.out.println(visited.size());
    }

    public static void updateTail(Point head, Point tail, Set<Point> visited) {
        if (Math.abs(head.x - tail.x) <= 1 && Math.abs(head.y - tail.y) <= 1) {
            return;
        }
        if (head.x > tail.x) {
            tail.x++;
        } else if (head.x < tail.x) {
            tail.x--;
        }
        if (head.y > tail.y) {
            tail.y++;
        } else if (head.y < tail.y) {
            tail.y--;
        }
        visited.add(new Point(tail.x, tail.y));
    }
}