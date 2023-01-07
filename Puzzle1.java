import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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

        ArrayList<Monkey> monkeys = new ArrayList<>();

        int i = 1;

        while (i < lines.size()) {
            String[] split = lines.get(i).split("Starting items: |, ");
            ArrayList<Integer> startingItems = new ArrayList<>();
            for (int j = 1; j < split.length; j++) {
                startingItems.add(Integer.parseInt(split[j]));
            }
            i++;

            String operation = lines.get(i).replace("Operation: new = ", "");
            i++;

            split = lines.get(i).split(" ");
            int divisor = Integer.parseInt(split[split.length - 1]);
            i++;

            split = lines.get(i).split(" ");
            int throwToIfTrue = Integer.parseInt(split[split.length - 1]);
            i++;

            split = lines.get(i).split(" ");
            int throwToIfFalse = Integer.parseInt(split[split.length - 1]);
            i++;

            monkeys.add(new Monkey(startingItems, operation, divisor, throwToIfTrue, throwToIfFalse));

            i += 2;
        }

        int numRounds = 20;

        for (int thisRound = 0; thisRound < numRounds; thisRound++) {
            for (Monkey monkey : monkeys) {
                for (int item : monkey.items) {
                    String[] split = monkey.operation.split(" ");
                    int leftOperand = (split[0].equals("old") ? item : Integer.parseInt(split[0]));
                    char operator = split[1].charAt(0);
                    int rightOperand = (split[2].equals("old") ? item : Integer.parseInt(split[2]));

                    int newWorryLevel;

                    if (operator == '+') {
                        newWorryLevel = leftOperand + rightOperand;
                    }
                    else {
                        newWorryLevel = leftOperand * rightOperand;
                    }
                    newWorryLevel /= 3;

                    monkey.inspectCount++;

                    if (newWorryLevel % monkey.divisor == 0) {
                        monkeys.get(monkey.throwToIfTrue).items.add(newWorryLevel);
                    } else {
                        monkeys.get(monkey.throwToIfFalse).items.add(newWorryLevel);
                    }
                }
                monkey.items.clear();
            }
        }

        int[] monkeyActivity = new int[monkeys.size()];
        for (int j = 0; j < monkeys.size(); j++) {
            monkeyActivity[j] = monkeys.get(j).inspectCount;
        }

        Arrays.sort(monkeyActivity);

        System.out.println(monkeyActivity[monkeyActivity.length - 2] * monkeyActivity[monkeyActivity.length - 1]);
    }
}

class Monkey {
    ArrayList<Integer> items;
    String operation;
    int divisor;
    int throwToIfTrue;
    int throwToIfFalse;
    int inspectCount;

    public Monkey(ArrayList<Integer> items, String operation, int divisor, int throwToIfTrue, int throwToIfFalse) {
        this.items = items;
        this.operation = operation;
        this.divisor = divisor;
        this.throwToIfTrue = throwToIfTrue;
        this.throwToIfFalse = throwToIfFalse;
        this.inspectCount = 0;
    }
}