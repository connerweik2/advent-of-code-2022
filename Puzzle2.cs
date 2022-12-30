using (StreamReader sr = File.OpenText("./input.txt"))
{
    string thisLine;

    List<string> lines = new List<string>();

    while ((thisLine = sr.ReadLine()) != null)
    {
        lines.Add(thisLine);
    }

    Node root = new Node(null, true, 0);
    Node currentNode = root;

    int i =  1;
    while (i < lines.Count)
    {
        string[] split = lines[i].Split(" ");
        i++;
        if (i == lines.Count)
        {
            break;
        }
        if (split[1] == "ls")
        {
            while (i < lines.Count && !lines[i].Contains("$"))
            {
                split = lines[i].Split(" ");
                if (split[0] == "dir")
                {
                    currentNode.children[split[1]] = new Node(currentNode, true, 0);
                }
                else
                {
                    currentNode.children[split[1]] = new Node(currentNode, false, int.Parse(split[0]));
                }
                i++;
            }
        }
        else if (split[1] == "cd")
        {
            if (split[2] == "..")
            {
                currentNode = currentNode.parent;
            }
            else
            {
                currentNode = currentNode.children[split[2]];
            }
        }
    }

    List<int> dirSizes = new List<int>();

    SetDirSizesClass.SetDirSizes(root, dirSizes);

    int total_space = 70000000;
    int unused_space_requirement = 30000000;
    int threshold = Math.Max(0, unused_space_requirement - (total_space - root.size));

    Console.WriteLine((from dirSize in dirSizes where dirSize >= threshold select dirSize).Min());
}

class Node
{
    public Node parent;
    public bool isDir;
    public int size;
    public Dictionary<String, Node> children;

    public Node(Node parent, bool isDir, int size)
    {
        this.parent = parent;
        this.isDir = isDir;
        this.size = size;
        this.children = new Dictionary<String, Node>();
    }
}

class SetDirSizesClass
{
    public static int SetDirSizes(Node root, List<int> dirSizes)
    {
        if (!root.isDir)
        {
            return root.size;
        }
        int dirSize = 0;
        foreach (Node child in root.children.Values)
        {
            dirSize += SetDirSizes(child, dirSizes);
        }
        root.size = dirSize;
        dirSizes.Add(dirSize);
        return dirSize;
    }
}