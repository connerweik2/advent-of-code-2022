#include <iostream>
#include <fstream>
#include <string.h>
using namespace std;

int main(void) {
    ifstream inputFile("./input.txt");

    int result = 0;

    string line;

    while (getline(inputFile, line)) {
        bool left[256];
        bool right[256];

        size_t comma_index = line.find(",");
        string first = line.substr(0, comma_index);
        string second = line.substr(comma_index + 1, line.length() - (comma_index + 1));
        int firstHyphenIndex = first.find("-");
        int secondHyphenIndex = second.find("-");
        int firstMin = stoi(first.substr(0, firstHyphenIndex));
        int firstMax = stoi(first.substr(firstHyphenIndex + 1, first.length() - (firstHyphenIndex + 1)));
        int secondMin = stoi(second.substr(0, secondHyphenIndex));
        int secondMax = stoi(second.substr(secondHyphenIndex + 1, second.length() - (secondHyphenIndex + 1)));

        if (firstMin >= secondMin && firstMax <= secondMax ||
            secondMin >= firstMin && secondMax <= firstMax) {
            result++;
        }
    }

    inputFile.close();

    cout << result << endl;

    return 0;
}