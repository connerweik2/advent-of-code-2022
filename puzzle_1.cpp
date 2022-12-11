#include <iostream>
#include <fstream>
#include <string.h>
using namespace std;

int main(void) {
    ifstream inputFile("./input.txt");

    int resultScore = 0;

    string thisLine;

    while (getline(inputFile, thisLine)) {
        // The opponent's and our choices:
        // 0 for rock, 1 for paper, 2 for scissors.
        int opponentChoice = thisLine[0] - 'A';
        int myChoice = thisLine[2] - 'X';
        
        // Update score based on our choice.
        resultScore += myChoice + 1;

        // Draw: Add 3.
        if (opponentChoice == myChoice) resultScore += 3;

        // Win: Add 6. In a win, our choice will be
        // one to the right of the opponent's in the list
        // [rock, paper, scissors], wrapping around at the ends.
        else if ((opponentChoice + 1) % 3 == myChoice) resultScore += 6;

    }

    inputFile.close();

    cout << resultScore << endl;

    return 0;
}