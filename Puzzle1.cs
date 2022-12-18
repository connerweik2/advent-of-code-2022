using (StreamReader sr = File.OpenText("./input.txt"))
{
    string line;

    Stack<char>[] stacks = new Stack<char>[9];
    for (int i = 0; i < 9; i++)
    {
        stacks[i] = new Stack<char>();
    }

    char[,] initialState = new char[8, 9];

    for (int i = 0; i < 8; i++)
    {
        line = sr.ReadLine();
        for (int j = 0; j < 9; j++)
        {
            initialState[i,j] = line[j * 4 + 1];
        }
    }

    for (int i = 7; i >= 0; i--)
    {
        for (int j = 0; j < 9; j++)
        {
            if (initialState[i,j] != ' ')
            {
                stacks[j].Push(initialState[i,j]);
            }
        }
    }

    sr.ReadLine();
    sr.ReadLine();

    while ((line = sr.ReadLine()) != null)
    {
        string[] split = line.Split(" ");

        int moveCount = int.Parse(split[1]);
        int stackFrom = int.Parse(split[3]) - 1;
        int stackTo = int.Parse(split[5]) - 1;

        for (int i = 0; i < moveCount; i++)
        {
            stacks[stackTo].Push(stacks[stackFrom].Pop());
        }
    }

    for (int i = 0; i < 9; i++)
    {
        Console.Write(stacks[i].Pop());
    }
    Console.WriteLine();
}