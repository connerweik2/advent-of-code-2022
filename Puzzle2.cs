int result = 0;

using (StreamReader sr = File.OpenText("./input.txt"))
{
    string thisLine;

    while ((thisLine = sr.ReadLine()) != null)
    {
        string[] split = thisLine.Trim().Split(",");
        int firstMin = int.Parse(split[0].Substring(0, split[0].IndexOf("-")));
        int firstMax = int.Parse(split[0].Substring(split[0].IndexOf("-") + 1));
        int secondMin = int.Parse(split[1].Substring(0, split[1].IndexOf("-")));
        int secondMax = int.Parse(split[1].Substring(split[1].IndexOf("-") + 1));

        if (firstMin <= secondMax && firstMax >= secondMin)
        {
            result++;
        }
    }
}

Console.WriteLine(result);