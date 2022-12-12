#include <iostream>
#include <fstream>
#include <string.h>
using namespace std;

int main(void) {
    ifstream inputFile("./input.txt");

    int result = 0;

    string thisLine;

    while (getline(inputFile, thisLine)) {
        bool first[256];
        bool second[256];
        bool third[256];

        for (int i = 0; i < 256; i++) {
            first[i] = false;
            second[i] = false;
            third[i] = false;
        }

        for (auto c : thisLine) {
            first[c] = true;
        }
        getline(inputFile, thisLine);
        for (auto c : thisLine) {
            second[c] = true;
        }
        getline(inputFile, thisLine);
        for (auto c : thisLine) {
            third[c] = true;
        }

        for (int i = 0; i < 256; i++) {
            if (first[i] && second[i] && third[i]) {
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