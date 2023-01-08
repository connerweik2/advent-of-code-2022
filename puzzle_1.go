package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"sort"
	"strconv"
	"strings"
)

type monkey struct {
	items          []int64
	operation      string
	divisor        int64
	throwToIfTrue  int
	throwToIfFalse int
	inspectCount   int
}

func newMonkey(items []int64, operation string, divisor int64,
	throwToIfTrue int, throwToIfFalse int) *monkey {
	return &monkey{items: items, operation: operation, divisor: divisor,
		throwToIfTrue: throwToIfTrue, throwToIfFalse: throwToIfFalse,
		inspectCount: 0}
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

	var monkeys []*monkey

	for i := 1; i < len(lines); i += 2 {
		var items []int64
		split := strings.Split(lines[i][len("Starting items: "):], ", ")
		for j := 0; j < len(split); j++ {
			num, _ := strconv.ParseInt(split[j], 10, 64)
			items = append(items, num)
		}
		i++

		operation := lines[i][len("Operation: new = "):]
		i++

		split = strings.Split(lines[i], " ")
		divisor, _ := strconv.ParseInt(split[len(split)-1], 10, 64)
		i++

		split = strings.Split(lines[i], " ")
		throwToIfTrue, _ := strconv.Atoi(split[len(split)-1])
		i++

		split = strings.Split(lines[i], " ")
		throwToIfFalse, _ := strconv.Atoi(split[len(split)-1])
		i++

		monkeys = append(monkeys, newMonkey(items, operation, divisor, throwToIfTrue, throwToIfFalse))
	}

	for thisRound := 0; thisRound < 20; thisRound++ {
		for _, monkey := range monkeys {
			for _, item := range monkey.items {
				split := strings.Split(monkey.operation, " ")
				var leftOperand int64
				if split[0] == "old" {
					leftOperand = item
				} else {
					leftOperand, _ = strconv.ParseInt(split[0], 10, 64)
				}
				operator := split[1][0]
				var rightOperand int64
				if split[2] == "old" {
					rightOperand = item
				} else {
					rightOperand, _ = strconv.ParseInt(split[2], 10, 64)
				}

				var newWorryLevel int64

				if operator == '+' {
					newWorryLevel = leftOperand + rightOperand
				} else {
					newWorryLevel = leftOperand * rightOperand
				}

				newWorryLevel /= 3

				monkey.inspectCount++

				if newWorryLevel%monkey.divisor == int64(0) {
					monkeys[monkey.throwToIfTrue].items =
						append(monkeys[monkey.throwToIfTrue].items, newWorryLevel)
				} else {
					monkeys[monkey.throwToIfFalse].items =
						append(monkeys[monkey.throwToIfFalse].items, newWorryLevel)
				}
			}

			monkey.items = nil
		}
	}

	var monkeyActivity []int
	for _, monkey := range monkeys {
		monkeyActivity = append(monkeyActivity, monkey.inspectCount)
	}

	sort.Ints(monkeyActivity)

	fmt.Println(int64(monkeyActivity[len(monkeyActivity)-2]) *
		int64(monkeyActivity[len(monkeyActivity)-1]))

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
