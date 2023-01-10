List<String> lines = new List<string>();

using (StreamReader sr = File.OpenText("./input.txt"))
{
    string line;
    while ((line =  sr.ReadLine()) != null)
    {
        lines.Add(line.Trim());
    }
}

int numRows = lines.Count;
int numCols = lines[0].Length;

int[,] grid = new int[numRows, numCols];

bool[,] visited = new bool[numRows, numCols];

int sRow = -1, sCol = -1;
int eRow = -1, eCol = -1;

Queue<Tuple<int, int>> queue = new Queue<Tuple<int, int>>();

for (int row = 0; row < numRows; row++)
{
    for (int col = 0; col < numCols; col++)
    {
        char c = lines[row][col];
        if (c == 'S')
        {
            sRow = row;
            sCol = col;
            grid[row, col] = (int)('a');
        }
        else if (c == 'E')
        {
            eRow = row;
            eCol = col;
            grid[row, col] = (int)('z');
        }
        else if (c == 'a')
        {
            visited[row, col] = true;
            queue.Enqueue(Tuple.Create(row, col));
            grid[row, col] = (int)(c);
        }
        else
        {
            grid[row, col] = (int)(c);
        }
    }
}

int result = 1;

int[,] dir = new int[,] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

visited[sRow, sCol] = true;
queue.Enqueue(Tuple.Create(sRow, sCol));

while (true)
{
    int count = queue.Count;
    for (int i = 0; i < count; i++)
    {
        Tuple<int, int> point = queue.Dequeue();
        int row = point.Item1;
        int col = point.Item2;
        for (int j = 0; j < dir.GetLength(0); j++)
        {
            int newRow = row + dir[j,0];
            int newCol = col + dir[j,1];
            if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols &&
            !visited[newRow, newCol] && grid[newRow, newCol] <= grid[row, col] + 1)
            {
                if (newRow == eRow && newCol == eCol)
                {
                    Console.WriteLine(result);
                    return;
                }
                
                visited[newRow, newCol] = true;
                queue.Enqueue(Tuple.Create(newRow, newCol));
            }
        }
    }

    result++;
}