#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_set>
using namespace std;

void process_move(vector<pair<int, int>> *rope, unordered_set<string> *visited, char direction) {
    if (direction == 'U') {
        (*rope)[0].second--;
    } else if (direction == 'D') {
        (*rope)[0].second++;
    } else if (direction == 'L') {
        (*rope)[0].first--;
    } else {
        (*rope)[0].first++;
    }

    for (int i = 1; i < (*rope).size(); i++) {
        if (abs((*rope)[i-1].first - (*rope)[i].first) <= 1 && abs((*rope)[i-1].second - (*rope)[i].second) <= 1) {
            return;
        }

        if ((*rope)[i-1].first > (*rope)[i].first) {
            (*rope)[i].first++;
        } else if ((*rope)[i-1].first < (*rope)[i].first) {
            (*rope)[i].first--;
        }
        if ((*rope)[i-1].second > (*rope)[i].second) {
            (*rope)[i].second++;
        } else if ((*rope)[i-1].second < (*rope)[i].second) {
            (*rope)[i].second--;
        }
    }

    (*visited).insert(to_string((*rope)[(*rope).size()-1].first) + "," + to_string((*rope)[(*rope).size()-1].second));
}

int main(void) {
    ifstream input_file("./input.txt");
    
    string line;
    vector<string> lines;

    while (getline(input_file, line)) {
        lines.push_back(line);
    }

    input_file.close();

    unordered_set<string> visited;

    vector<pair<int, int>> rope;
    for (int i = 0; i < 2; i++) {
        rope.push_back(pair(0, 0));
    }

    visited.insert("0,0");

    for (auto line : lines) {
        char direction = line[0];
        int distance = stoi(line.substr(line.find(" ") + 1));

        for (int i = 0; i < distance; i++) {
            process_move(&rope, &visited, direction);
        }
    }

    cout << visited.size() << endl;

    return 0;
}