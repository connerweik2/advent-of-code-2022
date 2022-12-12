#include <iostream>
#include <fstream>
#include <string.h>
using namespace std;

int main(void) {
    ifstream inputFile("./input.txt");

    int result = 0;

    string thisLine;

    while (getline(inputFile, thisLine)) {
        bool left[256];
        bool right[256];

        for (int i = 0; i < 256; i++) {
            left[i] = false;
            right[i] = false;
        }

        for (int i = 0; i < thisLine.length() / 2; i++) {
            left[thisLine[i]] = true;
        }
        for (int i = thisLine.length() / 2; i < thisLine.length(); i++) {
            right[thisLine[i]] = true;
        }

        for (int i = 0; i < 256; i++) {
            if (left[i] && right[i]) {
                if (i >= 'a' && i <= 'z') {
                    result += i - 'a' + 1;
                    break;
                }
                else {
                    result += i - 'A' + 27;
                    break;
                }
            }
        }
    }

    inputFile.close();

    cout << result << endl;

    return 0;
}