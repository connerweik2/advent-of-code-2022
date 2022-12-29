#include <iostream>
#include <fstream>
#include <vector>
#include <unordered_map>
using namespace std;

class Node {
    public:
        Node *parent;
        bool is_dir;
        int size;
        unordered_map<string, Node*> children;
        Node(Node *parent, bool is_dir, int size) {
            this->parent = parent;
            this->is_dir = is_dir;
            this->size = size;
        }
};

int set_dir_sizes(Node* root, vector<int> *dir_sizes) {
    if (!root->is_dir) {
        return root->size;
    }
    int dir_size = 0;
    for (const auto & [key, value] : root->children) {
        dir_size += set_dir_sizes(value, dir_sizes);
    }
    root->size = dir_size;
    (*dir_sizes).push_back(dir_size);
    return dir_size;
}

int main(void) {
    ifstream input_file("./input.txt");
    
    string line;
    vector<string> lines;
    vector<int> dir_sizes;

    Node *root;
    Node *current_node;

    root = new Node(NULL, true, 0);
    current_node = root;

    while (getline(input_file, line)) {
        lines.push_back(line);
    }

    input_file.close();

    int i = 1;
    while (i < lines.size()) {
        size_t space_index = lines[i].find(' ');
        size_t second_space_index = lines[i].find(' ', space_index + 1);
        string first = lines[i].substr(0, space_index);
        string second = lines[i].substr(space_index + 1, second_space_index - (space_index + 1));
        if (i == lines.size()) {
            break;
        }
        if (!second.compare("ls")) {
            i++;
            while (i < lines.size() and lines[i].find("$") == string::npos) {
                size_t space_index = lines[i].find(' ');
                size_t second_space_index = lines[i].find(' ', space_index + 1);
                string first = lines[i].substr(0, space_index);
                string second = lines[i].substr(space_index + 1, second_space_index - (space_index + 1));
                if (!first.compare("dir")) {
                    current_node->children[second] = new Node(current_node, true, 0);
                } else {
                    current_node->children[second] = new Node(current_node, false, stoi(first));
                }
                i++;
            }
        } else if (!second.compare("cd")) {
            string third = lines[i].substr(second_space_index + 1, string::npos);
            if (!third.compare("..")) {
                current_node = current_node->parent;
            } else {
                current_node = current_node->children[third];
            }
            i++;
        }
    }

    set_dir_sizes(root, &dir_sizes);

    int result = 0;
    for (auto dir_size : dir_sizes) {
        if (dir_size <= 100000) result += dir_size;
    }

    cout << result << endl;

    return 0;
}