#include <iostream>
#include <fstream>
#include <string.h>
using namespace std;

int main(void) {
    ifstream inputFile("./input.txt");

    int currentSum = 0;
    int max = 0;

    string thisLine;

    while (getline(inputFile, thisLine)) {
        bool isNumber = (thisLine.length() != 0);
        if (isNumber) {
            currentSum += stoi(thisLine);
        } else {
            if (currentSum > max) {
                max = currentSum;
            }
            currentSum = 0;
        }
    }

    inputFile.close();

    // Update max accordingly for the last elf.
    if (currentSum > max) {
        max = currentSum;
    }

    cout << max << endl;

    return 0;
}