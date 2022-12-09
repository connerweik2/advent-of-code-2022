import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Puzzle2Solution {

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("./input.txt");
        Scanner in = new Scanner(inputFile);

        // Use a minheap to keep the 3 top elements and easily replace the least of those.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int thisElfCalories = 0;

        while (in.hasNextLine()) {
            while (in.hasNextLine()) {
                try {
                    thisElfCalories += Integer.parseInt(in.nextLine());                    
                } catch (NumberFormatException e) {
                    // If this elf is carrying more calories than the least of the
                    // priority queue, remove that and add this.
                    if (pq.size() < 3) {
                        pq.offer(thisElfCalories);
                    } else if (thisElfCalories > pq.peek()) {
                        pq.poll();
                        pq.offer(thisElfCalories);
                    }

                    thisElfCalories = 0;
                }
            }
        }
        
        in.close();

        // Update the priority queue for the last elf.
        if (thisElfCalories > pq.peek()) {
            pq.poll();
            pq.offer(thisElfCalories);
        }

        
        // Add up the three max calorie counts.
        int resultSum = 0;
        while (pq.size() > 0) resultSum += pq.poll();

        // Print the result.
        System.out.println(resultSum);
    }

}
