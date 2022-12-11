package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"sort"
	"strconv"
)

func main() {
	file, err := os.Open("./input.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	currentSum := 0
	top3 := []int{-1, -1, -1}

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		thisLine, err := strconv.Atoi(scanner.Text())
		if err != nil {
			if currentSum > top3[0] {
				top3[0] = currentSum
				sort.Ints(top3)
			}
			currentSum = 0
		} else {
			currentSum += thisLine
		}
	}

	if currentSum > top3[0] {
		top3[0] = currentSum
		sort.Ints(top3)
	}

	resultSum := 0

	for i := 0; i < 3; i++ {
		resultSum += top3[i]
	}

	fmt.Println(resultSum)

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
