import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Puzzle1 {
    public static boolean debug = true;

    public static ArrayList<Object> constructListFromString(String s) {
        int i = 1;
        ArrayList<Object> currentList = new ArrayList<>();
        Stack<ArrayList<Object>> parentStack = new Stack<>();

        while (i < s.length() - 1) {
            if (s.charAt(i) == '[') {
                parentStack.push(currentList);
                ArrayList<Object> childList = new ArrayList<>();
                currentList.add(childList);
                currentList = childList;
                i++;
            } else if (s.charAt(i) == ',') {
                i++;
            } else if (s.charAt(i) == ']') {
                currentList = parentStack.pop();
                i++;
            } else {
                String currentNumberString = "";
                while (Character.isDigit(s.charAt(i))) {
                    currentNumberString += s.charAt(i);
                    i++;
                }
                currentList.add(Integer.parseInt(currentNumberString));
            }
        }

        return currentList;
    }

    public static int compare(ArrayList<Object> left, ArrayList<Object> right) {
        for (int i = 0; i < Math.min(left.size(), right.size()); i++) {
            if (left.get(i) instanceof Integer && right.get(i) instanceof Integer) {
                int leftIntValue = ((Integer)left.get(i)).intValue();
                int rightIntValue = ((Integer)right.get(i)).intValue();
                if (leftIntValue < rightIntValue) {
                    return -1;
                } else if (rightIntValue < leftIntValue) {
                    return 1;
                }
            } else if (left.get(i) instanceof ArrayList && right.get(i) instanceof ArrayList) {
                int compareResult = compare((ArrayList<Object>)left.get(i), (ArrayList<Object>)right.get(i));
                if (compareResult != 0) return compareResult;
            } else if (left.get(i) instanceof ArrayList) {
                ArrayList<Object> r = new ArrayList<>();
                r.add(right.get(i));
                int compareResult = compare((ArrayList<Object>)left.get(i), r);
                if (compareResult != 0) return compareResult;
            } else {
                ArrayList<Object> l = new ArrayList<>();
                l.add(left.get(i));
                int compareResult = compare(l, (ArrayList<Object>)right.get(i));
                if (compareResult != 0) return compareResult;
            }
        }
        if (left.size() < right.size()) {
            return -1;
        } else if (right.size() < left.size()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        File inputFile = new File("./input.txt");
        
        Scanner in = new Scanner(inputFile);

        while (in.hasNext()) {
            lines.add(in.nextLine().trim());
        }

        in.close();

        int result = 0;

        int i = 0;

        int pairIndex = 1;

        while (i < lines.size()) {
            String firstLine  = lines.get(i);
            i += 1;
            String secondLine = lines.get(i);
            i += 2;

            ArrayList<Object> firstPacket = constructListFromString(firstLine);
            ArrayList<Object> secondPacket = constructListFromString(secondLine);

            if (compare(firstPacket, secondPacket) == -1) {
                result += pairIndex;
            }

            pairIndex++;
        }

        System.out.println(result);
    }
}