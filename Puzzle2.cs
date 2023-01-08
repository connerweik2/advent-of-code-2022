List<String> lines = new List<string>();

using (StreamReader sr = File.OpenText("./input.txt"))
{
    string line;
    while ((line =  sr.ReadLine()) != null)
    {
        lines.Add(line.Trim());
    }
}

List<Monkey> monkeys = new List<Monkey>();

int i = 1;

while (i < lines.Count)
{
    string[] split = System.Text.RegularExpressions.Regex.Split(lines[i], "Starting items: |, ");
    List<long> startingItems = new List<long>();
    for (int j = 1; j < split.Length; j++)
    {
        startingItems.Add(long.Parse(split[j]));
    }
    i++;

    string operation = lines[i].Replace("Operation: new = ", "");
    i++;

    split = lines[i].Split(" ");
    long divisor = long.Parse(split[split.Length - 1]);
    i++;

    split = lines[i].Split(" ");
    int throwToIfTrue = int.Parse(split[split.Length - 1]);
    i++;

    split = lines[i].Split(" ");
    int throwToIfFalse = int.Parse(split[split.Length - 1]);
    i++;

    monkeys.Add(new Monkey(startingItems, operation, divisor, throwToIfTrue, throwToIfFalse));

    i += 2;
}

long productOfDivisors = 1L;
foreach (Monkey monkey in monkeys)
{
    productOfDivisors *= monkey.divisor;
}

int numRounds = 10000;

for (int thisRound = 0; thisRound < numRounds; thisRound++)
{
    foreach (Monkey monkey in monkeys)
    {
        foreach (long item in monkey.items)
        {
            string[] split = monkey.operation.Split(" ");
            long leftOperand = split[0] == "old" ? item : long.Parse(split[0]);
            string op = split[1];
            long rightOperand = split[2] == "old" ? item : long.Parse(split[2]);

            long newWorryLevel;

            if (op == "+")
            {
                newWorryLevel = leftOperand + rightOperand;
            }
            else
            {
                newWorryLevel = leftOperand * rightOperand;
            }

            monkey.inspectCount++;

            if (newWorryLevel % monkey.divisor == 0L)
            {
                monkeys[monkey.throwToIfTrue].items.Add(newWorryLevel % productOfDivisors);
            }
            else
            {
                monkeys[monkey.throwToIfFalse].items.Add(newWorryLevel % productOfDivisors);
            }
        }

        monkey.items.Clear();
    }
}

int[] monkeyActivity = new int[monkeys.Count];
for (int j = 0; j < monkeys.Count; j++)
{
    monkeyActivity[j] = monkeys[j].inspectCount;
}

Array.Sort(monkeyActivity);

Console.WriteLine((long)monkeyActivity[monkeyActivity.Length - 2] * (long)monkeyActivity[monkeyActivity.Length - 1]);

public class Monkey
{
    public List<long> items;
    public string operation;
    public long divisor;
    public int throwToIfTrue;
    public int throwToIfFalse;
    public int inspectCount;

    public Monkey(List<long> items, string operation, long divisor, int throwToIfTrue, int throwToIfFalse)
    {
        this.items = items;
        this.operation = operation;
        this.divisor = divisor;
        this.throwToIfTrue = throwToIfTrue;
        this.throwToIfFalse = throwToIfFalse;
        this.inspectCount = 0;
    }
}