using (StreamReader sr = File.OpenText("./input.txt"))
{
    string input = sr.ReadLine();

    int[] freq = new int[256];

    for (int i = 0; i < 3; i++)
    {
        freq[input[i]]++;
    }

    for (int i = 3; i < input.Length; i++)
    {
        freq[input[i]]++;
        
        bool allUnique = true;

        for (int j = 0; j < 256; j++)
        {
            if (freq[j] > 1)
            {
                allUnique = false;
                break;
            }
        }

        if (allUnique)
        {
            Console.WriteLine(i + 1);
            break;
        }

        freq[input[i - 3]]--;
    }
}