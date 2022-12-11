// int max = 0;
// int currentSum = 0;
// using (StreamReader sr = File.OpenText("./input.txt"))
// {
//     string thisLine;
//     while ((thisLine = sr.ReadLine()) != null)
//     {

//         Console.WriteLine(thisLine);
//         int thisLineInt = 0;
//         bool result = int.TryParse(thisLine, out thisLineInt);
//         if (result)
//         {
//             currentSum += thisLineInt;
//         }
//         else
//         {
//             if (currentSum > max)
//             {
//                 max = currentSum;
//             }
//             currentSum = 0;
//         }
//     }

//     Console.WriteLine(currentSum);
// }