int resultScore = 0;

using (StreamReader sr = File.OpenText("./input.txt"))
{
    string thisLine;

    while ((thisLine = sr.ReadLine()) != null)
    {
        string thisLineTrim = thisLine.Trim();

        // The opponent's and our choice.
        // 0 for rock, 1 for paper, 2 for scissors.
        int opponentChoice = (int)(thisLineTrim[0] - 'A');
        int myChoice = (int)(thisLineTrim[2] - 'X');

        // Add 1 for rock, 2 for paper, 3 for scissors.
        resultScore += myChoice + 1;

        // Draw: add 3.
        if (opponentChoice == myChoice)
        {
            resultScore += 3;
        }
        // Win: Add 6. In a win, our choice will be
        // one to the right of the opponent's in the list
        // [rock, paper, scissors], wrapping around at the ends.
        else if ((opponentChoice + 1) % 3 == myChoice)
        {
            resultScore += 6;
        }
    }
}

Console.WriteLine(resultScore);