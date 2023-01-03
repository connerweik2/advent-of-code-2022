#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

bool visible(int target_row, int target_col, vector<vector<int>> grid) {
    bool visible_up = true, visible_down = true, visible_left = true, visible_right = true;
    for (int row = 0; row < target_row; row++) {
        if (grid[row][target_col] >= grid[target_row][target_col]) {
            visible_up = false;
            break;
        }
    }
    for (int row = target_row + 1; row < grid.size(); row++) {
        if (grid[row][target_col] >= grid[target_row][target_col]) {
            visible_down = false;
            break;
        }
    }
    for (int col = 0; col < target_col; col++) {
        if (grid[target_row][col] >= grid[target_row][target_col]) {
            visible_left = false;
            break;
        }
    }
    for (int col = target_col + 1; col < grid[0].size(); col++) {
        if (grid[target_row][col] >= grid[target_row][target_col]) {
            visible_right = false;
            break;
        }
    }
    return visible_up || visible_down || visible_left || visible_right;
}

int main(void) {
    ifstream input_file("./input.txt");
    
    string line;
    vector<string> lines;

    while (getline(input_file, line)) {
        lines.push_back(line);
    }

    input_file.close();

    vector<vector<int>> grid;
    
    for (int row = 0; row < lines.size(); row++) {
        vector<int> this_row;
        for (int col = 0; col < lines[0].size(); col++) {
            this_row.push_back((int)(lines[row][col] - '0'));
        }
        grid.push_back(this_row);
    }

    int result = 0;

    for (int row = 0; row < grid.size(); row++) {
        for (int col = 0; col < grid[0].size(); col++) {
            if (visible(row, col, grid)) {
                result++;
            }
        }
    }

    cout << result << endl;

    return 0;
}