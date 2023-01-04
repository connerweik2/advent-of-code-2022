package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

type point struct {
	x int
	y int
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}

func newPoint(x int, y int) point {
	returnPoint := point{x: x, y: y}
	return returnPoint
}

func processMove(visited *map[point]bool, rope *[]point, direction byte) {
	if direction == 'U' {
		(*rope)[0].y--
	} else if direction == 'D' {
		(*rope)[0].y++
	} else if direction == 'L' {
		(*rope)[0].x--
	} else {
		(*rope)[0].x++
	}

	for i := 1; i < len(*rope); i++ {
		if abs((*rope)[i-1].x-(*rope)[i].x) <= 1 &&
			abs((*rope)[i-1].y-(*rope)[i].y) <= 1 {
			return
		}

		if (*rope)[i-1].x > (*rope)[i].x {
			(*rope)[i].x++
		} else if (*rope)[i-1].x < (*rope)[i].x {
			(*rope)[i].x--
		}

		if (*rope)[i-1].y > (*rope)[i].y {
			(*rope)[i].y++
		} else if (*rope)[i-1].y < (*rope)[i].y {
			(*rope)[i].y--
		}

		(*visited)[newPoint((*rope)[len((*rope))-1].x, (*rope)[len((*rope))-1].y)] = true
	}
}

func main() {
	var lines []string

	file, err := os.Open("./input.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		lines = append(lines, strings.TrimSpace(scanner.Text()))
	}

	visited := make(map[point]bool)

	var rope []point
	for i := 0; i < 2; i++ {
		rope = append(rope, newPoint(0, 0))
	}

	visited[newPoint(0, 0)] = true

	for _, line := range lines {
		split := strings.Split(line, " ")
		direction := split[0][0]
		distance, _ := strconv.Atoi(split[1])
		for i := 0; i < distance; i++ {
			processMove(&visited, &rope, direction)
		}
	}

	fmt.Println(len(visited))

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
