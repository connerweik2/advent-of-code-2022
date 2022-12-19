#include <iostream>
#include <fstream>
#include <stack>
using namespace std;

int main(void) {
    ifstream input_file("./input.txt");
    
    string line;

    stack<char> stacks[9];

    char initial_state[8][9];

    for (int i = 0; i < 8; i++) {
        getline(input_file, line);
        for (int j = 0; j < 9; j++) {
            initial_state[i][j] = line[j * 4 + 1];
        }
    }

    for (int i = 7; i >= 0; i--) {
        for (int j = 0; j < 9; j++) {
            if (initial_state[i][j] != ' ') {
                stacks[j].push(initial_state[i][j]);
            }
        }
    }

    getline(input_file, line);
    getline(input_file, line);
    
    while (getline(input_file, line)) {
        int last_find_index = line.find(" ");

        int num_moves = stoi(line.substr(last_find_index + 1, line.find(" ", last_find_index + 1) - last_find_index));

        last_find_index = line.find(' ', last_find_index + 1);
        last_find_index = line.find(' ', last_find_index + 1);

        int stack_from = stoi(line.substr(last_find_index + 1, line.find(" ", last_find_index + 1) - last_find_index)) - 1;

        last_find_index = line.find(' ', last_find_index + 1);
        last_find_index = line.find(' ', last_find_index + 1);

        int stack_to = stoi(line.substr(last_find_index + 1, line.find(" ", last_find_index + 1) - last_find_index)) - 1;

        stack<char> temp_stack;

        for (int i = 0; i < num_moves; i++) {
            temp_stack.push(stacks[stack_from].top());
            stacks[stack_from].pop();
        }
        for (int i = 0; i < num_moves; i++) {
            stacks[stack_to].push(temp_stack.top());
            temp_stack.pop();
        }
    }

    for (int i = 0; i < 9; i++) {
        cout << stacks[i].top();
    }
    cout << endl;

    input_file.close();

    return 0;
}