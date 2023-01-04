using System.Drawing;

List<string> lines = new List<string>();

using (StreamReader sr = File.OpenText("./input.txt"))
{
    string line;
    while ((line = sr.ReadLine()) != null)
    {
        lines.Add(line.Trim());
    }
}

HashSet<Point> visited = new HashSet<Point>();

List<Point> rope = new List<Point>();
for (int i = 0; i < 10; i++)
{
    rope.Add(new Point(0, 0));
}

visited.Add(new Point(0, 0));

foreach (string line in lines)
{
    string[] split = line.Split(" ");
    char direction = split[0][0];
    int distance = int.Parse(split[1]);
    
    for (int i = 0; i < distance; i++)
    {
        ProcessMove(rope, visited, direction);
    }
}

Console.WriteLine(visited.Count);

static void ProcessMove(List<Point> rope, HashSet<Point> visited, char direction)
{
    if (direction == 'U')
    {
        rope[0] = new Point(rope[0].X, rope[0].Y - 1);
    }
    else if (direction == 'D')
    {
        rope[0] = new Point(rope[0].X, rope[0].Y + 1);
    }
    else if (direction == 'L')
    {
        rope[0] = new Point(rope[0].X - 1, rope[0].Y);
    }
    else if (direction == 'R')
    {
        rope[0] = new Point(rope[0].X + 1, rope[0].Y);
    }
    for (int i = 1; i < rope.Count; i++)
    {
        if (Math.Abs(rope[i - 1].X - rope[i].X) <= 1 && Math.Abs(rope[i - 1].Y - rope[i].Y) <= 1)
        {
            return;
        }
        if (rope[i - 1].X > rope[i].X)
        {
            rope[i] = new Point(rope[i].X + 1, rope[i].Y);
        }
        else if (rope[i - 1].X < rope[i].X)
        {
            rope[i] = new Point(rope[i].X - 1, rope[i].Y);
        }
        if (rope[i - 1].Y > rope[i].Y)
        {
            rope[i] = new Point(rope[i].X, rope[i].Y + 1);
        }
        else if (rope[i - 1].Y < rope[i].Y)
        {
            rope[i] = new Point(rope[i].X, rope[i].Y - 1);
        }
    }
    visited.Add(rope[rope.Count - 1]);
}