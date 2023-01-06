List<String> lines = new List<string>();

using (StreamReader sr = File.OpenText("./input.txt"))
{
    string line;
    while ((line =  sr.ReadLine()) != null)
    {
        lines.Add(line.Trim());
    }
}