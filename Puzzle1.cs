List<string> lines = new List<string>();

using (StreamReader sr = File.OpenText("./input.txt"))
{
    string line;
    while ((line = sr.ReadLine()) != null)
    {
        lines.Add(line.Trim());
    }
}

int[,] grid = new int[lines.Count, lines[0].Length];

for (int row = 0; row < grid.GetLength(0); row++)
{
    for (int col = 0; col < grid.GetLength(1); col++)
    {
        grid[row,col] = (int)(lines[row][col] - '0');
    }
}

int result = 0;

for (int targetRow = 0; targetRow < grid.GetLength(0); targetRow++)
{
    for (int targetCol = 0; targetCol < grid.GetLength(1); targetCol++)
    {
        bool visibleUp = true, visibleDown = true, visibleLeft = true, visibleRight = true;
        for (int row = 0; row < targetRow; row++)
        {
            if (grid[row, targetCol] >= grid[targetRow, targetCol])
            {
                visibleUp = false;
                break;
            }
        }
        for (int row = targetRow + 1; row < grid.GetLength(0); row++)
        {
            if (grid[row, targetCol] >= grid[targetRow, targetCol])
            {
                visibleDown = false;
                break;
            }
        }
        for (int col = 0; col < targetCol; col++)
        {
            if (grid[targetRow, col] >= grid[targetRow, targetCol])
            {
                visibleLeft = false;
                break;
            }
        }
        for (int col = targetCol + 1; col < grid.GetLength(1); col++)
        {
            if (grid[targetRow, col] >= grid[targetRow, targetCol])
            {
                visibleRight = false;
                break;
            }
        }
        if (visibleUp || visibleDown || visibleLeft || visibleRight)
        {
            result++;
        }
    }
}

Console.WriteLine(result);