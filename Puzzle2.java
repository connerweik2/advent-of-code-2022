import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Puzzle2 {
    static class Node {
        Node parent;
        boolean isFile;
        int size;
        Map<String, Node> children;

        public Node(Node parent, boolean isFile, int size) {
            this.parent = parent;
            this.isFile = isFile;
            this.size = size;
            this.children = new HashMap<>();
        }
    }

    public static int go(Node root) {
        int size = 0;
        for (String key : root.children.keySet()) {
            Node child = root.children.get(key);
            size += child.isFile ? child.size : go(child);
        }

        root.size = size;

        return size;
    }

    public static int go2(Node root, int threshold) {
        if (root.isFile) {
            return root.size >= threshold ? root.size : Integer.MAX_VALUE;
        }
        int retval = root.size >= threshold ? root.size : Integer.MAX_VALUE;
        for (Node child : root.children.values()) {
            retval = Math.min(retval, go2(child, threshold));
        }

        return retval;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("./input.txt");
        Scanner in = new Scanner(inputFile);

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
                        currentNode.children.put(second, new Node(currentNode, false, -1));
                    } else {
                        currentNode.children.put(second, new Node(currentNode, true, Integer.parseInt(first)));
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

        int spaceTotal = 70000000;
        int spaceTaken = root.size;
        int spaceRequiredForUpdate = 30000000;
        int threshold = Math.max(0, spaceRequiredForUpdate - (spaceTotal - spaceTaken));

        System.out.println(go2(root, threshold));
    }
}