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

		// The opponent's choice: 0 for rock, 1 for paper, 2 for scissors.
		// And the round result: 0 for loss, 1 for draw, 2 for win.
		opponentChoice := int(thisLine[0] - 'A')
		roundResult := int(thisLine[2] - 'X')

		// Update the result score based on the round result: 3 for draw, 6 for win.
		if roundResult == 1 {
			resultScore += 3
		}
		if roundResult == 2 {
			resultScore += 6
		}

		// Based on the round result, we know which we will pick:
		// if loss, pick 1 to the left of the opponent in [rock, paper, scissors],
		// wrapping around at the ends; if draw, pick the same as the
		// opponent; if win, pick 1 to the right. Update score accordingly:
		// 1 for rock, 2 for paper, 3 for scissors.
		resultScore += (opponentChoice+roundResult-1+3)%3 + 1
	}

	fmt.Println(resultScore)

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
