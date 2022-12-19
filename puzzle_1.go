package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

func main() {
	file, err := os.Open("./input.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	var stacks [9][]uint8

	var initialState [8][9]uint8

	scanner := bufio.NewScanner(file)
	for i := 0; i < 8; i++ {
		scanner.Scan()
		line := scanner.Text()
		for j := 0; j < 9; j++ {
			initialState[i][j] = line[j*4+1]
		}
	}

	for i := 7; i >= 0; i-- {
		for j := 0; j < 9; j++ {
			if initialState[i][j] != ' ' {
				stacks[j] = append(stacks[j], initialState[i][j])
			}
		}
	}

	scanner.Scan()
	scanner.Scan()

	for scanner.Scan() {
		split := strings.Split(scanner.Text(), " ")

		numMoves, _ := strconv.Atoi(split[1])
		stackFrom, _ := strconv.Atoi(split[3])
		stackFrom -= 1
		stackTo, _ := strconv.Atoi(split[5])
		stackTo -= 1

		for i := 0; i < numMoves; i++ {
			stacks[stackTo] = append(stacks[stackTo], stacks[stackFrom][len(stacks[stackFrom])-1])
			stacks[stackFrom] = stacks[stackFrom][:len(stacks[stackFrom])-1]
		}
	}

	var bytesResult []byte
	for i := 0; i < 9; i++ {
		bytesResult = append(bytesResult, stacks[i][len(stacks[i])-1])
	}

	fmt.Println(string(bytesResult))

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
