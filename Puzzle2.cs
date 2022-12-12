int result = 0;

using (StreamReader sr = File.OpenText("./input.txt"))
{
    string thisLine;

    while ((thisLine = sr.ReadLine()) != null)
    {
        HashSet<char> first = new HashSet<char>();
        HashSet<char> second = new HashSet<char>();
        HashSet<char> third = new HashSet<char>();

        for (int i = 0; i < thisLine.Length; i++) {
            first.Add(thisLine[i]);
        }
        thisLine = sr.ReadLine();
        for (int i = 0; i < thisLine.Length; i++) {
            second.Add(thisLine[i]);
        }
        thisLine = sr.ReadLine();
        for (int i = 0; i < thisLine.Length; i++) {
            third.Add(thisLine[i]);
        }


        foreach (char c in first) {
            if (second.Contains(c) && third.Contains(c)) {
                if (Char.IsLower(c)) result += c - 'a' + 1;
                else result += c - 'A' + 27;
                break;
            }
        }
    }
}

Console.WriteLine(result);