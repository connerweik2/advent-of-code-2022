#include <iostream>
#include <fstream>
#include <bits/stdc++.h>
using namespace std;

int main(void) {
    ifstream inputFile("./input.txt");

    int currentSum = 0;
    
    // Use a priority queue to store the top 3.
    priority_queue<int, vector<int>, greater<int>> pq;

    string thisLine;

    while (getline(inputFile, thisLine)) {
        bool isNumber = (thisLine.length() != 0);
        if (isNumber) {
            currentSum += stoi(thisLine);
        } else {
            if (pq.size() < 3) {
                pq.push(currentSum);
            } else if (currentSum > pq.top()) {
                pq.pop();
                pq.push(currentSum);
            }
            currentSum = 0;
        }
    }

    inputFile.close();

    // Update the heap accordingly for the last elf.
    if (currentSum > pq.top()) {
        pq.pop();
        pq.push(currentSum);
    }

    int resultSum = 0;
    for (int i = 0; i < 3; i++) {
        resultSum += pq.top();
        pq.pop();
    }

    cout << resultSum << endl;

    return 0;
}