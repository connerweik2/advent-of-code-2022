#include <iostream>
#include <fstream>
#include <string.h>
using namespace std;

int main(void) {
    ifstream inputFile("./input.txt");

    int resultScore = 0;

    string thisLine;

    while (getline(inputFile, thisLine)) {
        // The opponent's choice: 0 for rock, 1 for paper, 2 for scissors.
        // And the round result: 0 for loss, 1 for draw, 2 for win.
        int opponentChoice = thisLine[0] - 'A';
        int roundResult = thisLine[2] - 'X';
        
        // Add score based on the round result.
        if (roundResult == 1) resultScore += 3;
        else if (roundResult == 2) resultScore += 6;

        // Based on the round result, we know which we will pick:
        // if loss, pick 1 to the left of the opponent in [rock, paper, scissors],
        // wrapping around at the ends; if draw, pick the same as the
        // opponent; if win, pick 1 to the right. Update score accordingly:
        // 1 for rock, 2 for paper, 3 for scissors.
        resultScore += (opponentChoice + roundResult - 1 + 3) % 3 + 1;

    }

    inputFile.close();

    cout << resultScore << endl;

    return 0;
}