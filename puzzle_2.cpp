#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

int scenic_score(int target_row, int target_col, vector<vector<int>> grid) {
    int distance_up = 0, distance_down = 0, distance_left = 0, distance_right = 0;
    for (int row = target_row - 1; row >= 0; row--) {
        distance_up++;
        if (grid[row][target_col] >= grid[target_row][target_col]) {
            break;
        }
    }
    for (int row = target_row + 1; row < grid.size(); row++) {
        distance_down++;
        if (grid[row][target_col] >= grid[target_row][target_col]) {
            break;
        }
    }
    for (int col = target_col - 1; col >= 0; col--) {
        distance_left++;
        if (grid[target_row][col] >= grid[target_row][target_col]) {
            break;
        }
    }
    for (int col = target_col + 1; col < grid[0].size(); col++) {
        distance_right++;
        if (grid[target_row][col] >= grid[target_row][target_col]) {
            break;
        }
    }
    return distance_up * distance_down * distance_left * distance_right;
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

    for (int target_row = 0; target_row < grid.size(); target_row++) {
        for (int target_col = 0; target_col < grid[0].size(); target_col++) {
            int score = scenic_score(target_row, target_col, grid);
            if (score > result) {
                result = score;
            }
        }
    }

    cout << result << endl;

    return 0;
}