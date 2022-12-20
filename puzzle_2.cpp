#include <iostream>
#include <fstream>
#include <stack>
using namespace std;

int main(void) {
    ifstream input_file("./input.txt");
    
    string line;
    getline(input_file, line);
    input_file.close();

    int freq[256];
    for (int i = 0; i < 256; i++) {
        freq[i] = 0;
    }

    for (int i = 0; i < 13; i++) {
        freq[line[i]]++;
    }

    for (int i = 13; i < line.length(); i++) {
        freq[line[i]]++;

        bool all_unique = true;

        for (int j = 0; j < 256; j++) {
            if (freq[j] > 1) {
                all_unique = false;
                break;
            }
        }

        if (all_unique) {
            cout << i + 1 << endl;
            break;
        }

        freq[line[i - 13]]--;
    }

    return 0;
}