package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"regexp"
	"strconv"
)

func main() {
	file, err := os.Open("./input.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	result := 0
	re := regexp.MustCompile(",|-")

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := (scanner.Text())
		split := re.Split(line, -1)
		firstMin, _ := strconv.Atoi(split[0])
		firstMax, _ := strconv.Atoi(split[1])
		secondMin, _ := strconv.Atoi(split[2])
		secondMax, _ := strconv.Atoi(split[3])

		if firstMin >= secondMin && firstMax <= secondMax ||
			secondMin >= firstMin && secondMax <= firstMax {
			result++
		}
	}

	fmt.Println(result)

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
