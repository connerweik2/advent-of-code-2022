#include <iostream>
#include <fstream>
#include <vector>
#include <string>

using namespace std;

int main(void) {
    ifstream input_file("./input.txt");

    string line;

    vector<string> lines;

    while (getline(input_file, line)) {
        lines.push_back(line);
    }

    input_file.close();

    int result = 0;
    int x  = 1;
    int i = 0;
    int cycle = 1;
    bool in_middle_of_addx = false;

    while (i < lines.size()) {
        int space_index = lines[i].find(" ");

        if (abs(x - ((cycle - 1) % 40)) <= 1) {
            cout << "#";
        } else {
            cout << ".";
        }

        if (cycle % 40 == 0) {
            cout << endl;
        }

        if (lines[i].substr(0, space_index) == "noop") {
            i++;
        } else if (in_middle_of_addx) {
            x += stoi(lines[i].substr(space_index + 1));
            in_middle_of_addx = false;
            i++;
        } else if (lines[i].substr(0, space_index) == "addx") {
            in_middle_of_addx = true;
        }

        cycle++;
    }
}