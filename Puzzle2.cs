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
        int distanceUp = 0, distanceDown = 0, distanceLeft = 0, distanceRight = 0;
        for (int row = targetRow - 1; row >= 0; row--)
        {
            distanceUp++;
            if (grid[row, targetCol] >= grid[targetRow, targetCol])
            {
                break;
            }
        }
        for (int row = targetRow + 1; row < grid.GetLength(0); row++)
        {
            distanceDown++;
            if (grid[row, targetCol] >= grid[targetRow, targetCol])
            {
                break;
            }
        }
        for (int col = targetCol - 1; col >= 0; col--)
        {
            distanceLeft++;
            if (grid[targetRow, col] >= grid[targetRow, targetCol])
            {
                break;
            }
        }
        for (int col = targetCol + 1; col < grid.GetLength(1); col++)
        {
            distanceRight++;
            if (grid[targetRow, col] >= grid[targetRow, targetCol])
            {
                break;
            }
        }
        int scenicScore = distanceUp * distanceDown * distanceLeft * distanceRight;
        if (scenicScore > result)
        {
            result = scenicScore;
        }
    }
}

Console.WriteLine(result);