int result = 0;

using (StreamReader sr = File.OpenText("./input.txt"))
{
    string thisLine;

    while ((thisLine = sr.ReadLine()) != null)
    {
        HashSet<char> left = new HashSet<char>();
        HashSet<char> right = new HashSet<char>();
        for (int i = 0; i < thisLine.Length / 2; i++) {
            left.Add(thisLine[i]);
        }
        for (int i = thisLine.Length / 2; i < thisLine.Length; i++) {
            right.Add(thisLine[i]);
        }

        foreach (char c in left) {
            if (right.Contains(c)) {
                if (Char.IsLower(c)) result += c - 'a' + 1;
                else result += c - 'A' + 27;
                break;
            }
        }
    }
}

Console.WriteLine(result);