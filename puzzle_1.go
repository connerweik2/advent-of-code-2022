package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
)

func main() {
	file, err := os.Open("./input.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	currentSum := 0
	max := 0

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		thisLine, err := strconv.Atoi(scanner.Text())
		if err != nil {
			if currentSum > max {
				max = currentSum
			}
			currentSum = 0
		} else {
			currentSum += thisLine
		}
	}

	if currentSum > max {
		max = currentSum
	}

	fmt.Println(max)

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
