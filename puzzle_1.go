package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
)

func main() {
	file, err := os.Open("./input.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	resultScore := 0

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		thisLine := scanner.Text()

		// The opponent's choice and our choice.
		// 0 for rock, 1 for paper, 2 for scissors.
		opponentChoice := int(thisLine[0] - 'A')
		myChoice := int(thisLine[2] - 'X')

		// Update the result score based on our choice:
		// 1 for rock, 2 for paper, 3 for scissors.
		resultScore += myChoice + 1

		// For a draw, add 3.
		// For a win, add 6.
		// In a win, our choice will be
		// one to the right of the opponent's in the list
		// [rock, paper, scissors], wrapping around at the ends.
		if opponentChoice == myChoice {
			resultScore += 3
		} else if (opponentChoice+1)%3 == myChoice {
			resultScore += 6
		}
	}

	fmt.Println(resultScore)

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
