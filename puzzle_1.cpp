#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main(void) {
    ifstream input_file("./input.txt");

    string line;

    vector<string> lines;

    while (getline(input_file, line)) {
        lines.push_back(line);
    }

    input_file.close();
}