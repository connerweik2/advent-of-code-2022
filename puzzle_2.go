package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func main() {
	file, err := os.Open("./input.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	var lines []string

	for scanner.Scan() {
		lines = append(lines, strings.TrimSpace(scanner.Text()))
	}

	x := 1
	i := 0
	cycle := 1
	inMiddleOfAddx := false

	for i < len(lines) {
		split := strings.Split(lines[i], " ")

		if abs(x-((cycle-1)%40)) <= 1 {
			fmt.Print("#")
		} else {
			fmt.Print(".")
		}

		if cycle%40 == 0 {
			fmt.Println()
		}

		if split[0] == "noop" {
			i++
		} else if inMiddleOfAddx {
			addxValue, _ := strconv.Atoi(split[1])
			x += addxValue
			inMiddleOfAddx = false
			i++
		} else if split[0] == "addx" {
			inMiddleOfAddx = true
		}

		cycle++
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
