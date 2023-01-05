List<String> lines = new List<string>();

using (StreamReader sr = File.OpenText("./input.txt"))
{
    string line;
    while ((line =  sr.ReadLine()) != null)
    {
        lines.Add(line.Trim());
    }

    int x = 1;
    int i = 0;
    int cycle = 1;
    bool inMiddleOfAddx = false;

    while (i < lines.Count)
    {
        string[] split = lines[i].Split(" ");

        if (Math.Abs(x - ((cycle -1) % 40)) <= 1)
        {
            Console.Write("#");
        }
        else
        {
            Console.Write(".");
        }

        if (cycle % 40 == 0)
        {
            Console.WriteLine();
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
}