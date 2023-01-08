#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <cstring>

using namespace std;

class Monkey {
    public:
    vector<long long int> items;
    string operation;
    long long int divisor;
    size_t throw_to_if_true;
    size_t throw_to_if_false;
    int inspect_count;

    Monkey(vector<long long int> items, string operation, long long int divisor, size_t throw_to_if_true, size_t throw_to_if_false) {
        this->items = items;
        this->operation = operation;
        this->divisor = divisor;
        this->throw_to_if_true = throw_to_if_true;
        this->throw_to_if_false = throw_to_if_false;
        this->inspect_count = 0;
    }
};

vector<string> split(string s) {
    vector<string> result;
    size_t substring_start_index = 0;
    size_t substring_end_index = 0;
    while (substring_start_index < s.length() && isspace(s[substring_start_index])) {
        substring_start_index++;
    }
    while (true) {
        substring_end_index = s.find(" ", substring_start_index + 1);
        if (substring_end_index == string::npos) {
            result.push_back(s.substr(substring_start_index));
            return result;
        }
        result.push_back(s.substr(substring_start_index, substring_end_index - substring_start_index));
        substring_start_index = substring_end_index + 1;
        while (substring_start_index < s.length() && isspace(s[substring_start_index])) {
            substring_start_index++;
        }
        if (substring_start_index >= s.length()) {
            return result;
        }
    }
}

int main(void) {
    ifstream input_file("./input.txt");

    string line;

    vector<string> lines;

    while (getline(input_file, line)) {
        lines.push_back(line);
    }

    input_file.close();

    int i = 1;

    vector<Monkey*> monkeys;

    while (i < lines.size()) {
        vector<long long int> items;
        string line = lines[i];
        line.erase(remove(line.begin(), line.end(), ','), line.end());
        vector<string> line_split = split(line);
        for (int j = 2; j < line_split.size(); j++) {
            items.push_back(stoll(line_split[j]));
        }
        i ++;

        string operation = lines[i].substr(strlen("  Operation: new = "));
        i++;

        line_split = split(lines[i]);
        long long int divisor = stoll(line_split[line_split.size() - 1]);
        i++;

        line_split = split(lines[i]);
        size_t throw_to_if_true = stoi(line_split[line_split.size() - 1]);
        i++;

        line_split = split(lines[i]);
        size_t throw_to_if_false = stoi(line_split[line_split.size() - 1]);
        i++;

        monkeys.push_back(new Monkey(items, operation, divisor, throw_to_if_true, throw_to_if_false));

        i += 2;
    }

    long long int product_of_divisors = 1ll;
    for (auto monkey : monkeys) {
        product_of_divisors *= monkey->divisor;
    }

    int num_rounds = 10000;

    for (int this_round = 0; this_round < num_rounds; this_round++) {
        for (auto monkey : monkeys) {
            for (auto item : monkey->items) {
                vector<string> operation_split = split(monkey->operation);
                long long int left_operand = (operation_split[0] == "old") ? item : stoll(operation_split[0]);
                char op = operation_split[1][0];
                long long int right_operand = (operation_split[2] == "old") ? item : stoll(operation_split[2]);

                long long int new_worry_level;

                if (op == '+') {
                    new_worry_level = left_operand + right_operand;
                } else {
                    new_worry_level = left_operand * right_operand;
                }

                monkey->inspect_count++;
                
                if (new_worry_level % monkey->divisor == 0ll) {
                    monkeys[monkey->throw_to_if_true]->items.push_back(new_worry_level % product_of_divisors);
                } else {
                    monkeys[monkey->throw_to_if_false]->items.push_back(new_worry_level % product_of_divisors);
                }
            }

            monkey->items.clear();
        }
    }

    vector<int> monkey_activity;
    for (auto m : monkeys) {
        monkey_activity.push_back(m->inspect_count);
    }

    sort(monkey_activity.begin(), monkey_activity.end());
    
    cout << (long long int)monkey_activity[monkey_activity.size() - 2] * (long long int)monkey_activity[monkey_activity.size() - 1] << endl;
}