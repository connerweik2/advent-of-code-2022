int currentSum = 0;
PriorityQueue<int, int> pq = new PriorityQueue<int, int>();
pq.Enqueue(-1, -1);
pq.Enqueue(-1, -1);
pq.Enqueue(-1, -1);

using (StreamReader sr = File.OpenText("./input.txt"))
{
    string thisLine;
    while ((thisLine = sr.ReadLine()) != null)
    {

        int thisLineInt = 0;
        bool result = int.TryParse(thisLine, out thisLineInt);
        if (result)
        {
            currentSum += thisLineInt;
        }
        else
        {
            if (currentSum > pq.Peek())
            {
                pq.Dequeue();
                pq.Enqueue(currentSum, currentSum);
            }
            currentSum = 0;
        }
    }

    int resultSum = 0;

    for (int i = 0; i < 3; i++)
    {
        resultSum += pq.Dequeue();
    }

    Console.WriteLine(resultSum);
}