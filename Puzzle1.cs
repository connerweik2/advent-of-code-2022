List<String> lines = new List<string>();

using (StreamReader sr = File.OpenText("./input.txt"))
{
    string line;
    while ((line =  sr.ReadLine()) != null)
    {
        lines.Add(line.Trim());
    }

    int result = 0;
    int x = 1;
    int i = 0;
    int cycle = 1;
    bool inMiddleOfAddx = false;

    while (i < lines.Count)
    {
        string[] split = lines[i].Split(" ");

        if (cycle % 40 == 20)
        {
            result += cycle * x;
        }

        if (split[0] == "noop")
        {
            i++;
        }
        else if (inMiddleOfAddx)
        {
            x += int.Parse(split[1]);
            inMiddleOfAddx = false;
            i++;
        }
        else if (split[0] == "addx")
        {
            inMiddleOfAddx = true;
        }

        cycle++;
    }

    Console.WriteLine(result);
}