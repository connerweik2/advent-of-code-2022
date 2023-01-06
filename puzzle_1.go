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

	scanner := bufio.NewScanner(file)

	var lines []string

	for scanner.Scan() {
		lines = append(lines, strings.TrimSpace(scanner.Text()))
	}

	result := 0
	x := 1
	i := 0
	cycle := 1
	inMiddleOfAddx := false

	for i < len(lines) {
		split := strings.Split(lines[i], " ")

		if cycle%40 == 20 {
			result += cycle * x
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

	fmt.Println(result)

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
