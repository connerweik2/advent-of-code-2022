#include <iostream>
#include <fstream>
#include <vector>
#include <tuple>
#include <queue>

using namespace std;

int main(void) {
    ifstream input_file("./input.txt");

    string line;

    vector<string> lines;

    while (getline(input_file, line)) {
        lines.push_back(line);
    }

    input_file.close();

    int num_rows = lines.size();
    int num_cols = lines[0].length();

    int grid[num_rows][num_cols];
    bool visited[num_rows][num_cols];

    for (int row = 0; row < num_rows; row++) {
        for (int col = 0; col < num_cols; col++) {
            visited[row][col] = false;
        }
    }

    int s_row = -1, s_col = -1;
    int e_row = -1, e_col = -1;

    queue<tuple<int, int>> q;

    for (int row = 0; row < num_rows; row++) {
        for (int col = 0; col < num_cols; col++) {
            char c = lines[row][col];
            
            if (c == 'S') {
                s_row = row;
                s_col = col;
                visited[s_row][s_col] = true;
                q.push(make_tuple(s_row, s_col));
                grid[row][col] = (int)('a');
            } else if (c == 'E') {
                e_row = row;
                e_col = col;
                grid[row][col] = (int)('z');
            } else {
                grid[row][col] = (int)(c);
            }
        }
    }

    int result = 1;

    int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    while (!(q.size() == 0)) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            tuple<int, int> point = q.front();
            q.pop();
            int row = get<0>(point);
            int col = get<1>(point);
            for (int j = 0; j < 4; j++) {
                int new_row = row + dir[j][0];
                int new_col = col +dir[j][1];
                if (new_row >= 0 && new_row < num_rows && new_col >= 0 && new_col < num_cols &&
                !visited[new_row][new_col] && grid[new_row][new_col] <= grid[row][col] + 1) {
                    if (new_row == e_row && new_col == e_col) {
                        cout << result << endl;
                        return 0;
                    }

                visited[new_row][new_col] = true;
                q.push(make_tuple(new_row, new_col));
                }
            }
        }

        result++;
    }
}