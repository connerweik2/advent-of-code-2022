import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Puzzle1 {

    private static int result;

    static class Node {
        Node parent;
        boolean isDir;
        int size;
        Map<String, Node> children;

        public Node(Node parent, boolean isDir, int size) {
            this.parent = parent;
            this.isDir = isDir;
            this.size = size;
            this.children = new HashMap<>();
        }
    }

    public static int go(Node root) {
        int size = 0;
        for (String key : root.children.keySet()) {
            Node child = root.children.get(key);
            size += child.isDir ? go(child) : child.size;
        }
        if (size <= 100000) {
            result += size;
        }

        return size;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("./input.txt");
        Scanner in = new Scanner(inputFile);

        result = 0;

        Node root = new Node(null, false, 0);
        Node currentNode = root;

        in.next();
        in.next();
        in.next();

        String next = in.next();

        while (in.hasNext()) {
            if (next.equals("$")) {
                next = in.next();
            }

            if (next.equals("ls")) {
                next  = in.next();
                if (next.equals("$")) {
                    continue;
                }
                do {
                    String first = next;
                    String second = in.next();

                    if (first.equals("dir")) {
                        currentNode.children.put(second, new Node(currentNode, true, -1));
                    } else {
                        currentNode.children.put(second, new Node(currentNode, false, Integer.parseInt(first)));
                    }

                    if (in.hasNext()) next  = in.next();
                } while (in.hasNext() && !next.equals("$"));
            } else {
                String dirToCdTo = in.next();
                if (dirToCdTo.equals("..")) {
                    currentNode = currentNode.parent;
                } else {
                    currentNode = currentNode.children.get(dirToCdTo);
                }

                if (in.hasNext()) next  = in.next();
            }
        }

        in.close();

        go(root);

        System.out.println(result);
    }
}