package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strings"
)

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

	var grid [][]int

	for _, line := range lines {
		var thisRow []int
		for i, _ := range line {
			thisRow = append(thisRow, int(line[i]-'0'))
		}
		grid = append(grid, thisRow)
	}

	result := 0

	for targetRow := 0; targetRow < len(grid); targetRow++ {
		for targetCol := 0; targetCol < len(grid[0]); targetCol++ {
			score := scenicScore(targetRow, targetCol, grid)
			if score > result {
				result = score
			}
		}
	}

	fmt.Println(result)

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}

func scenicScore(targetRow int, targetCol int, grid [][]int) int {
	distanceUp := 0
	distanceDown := 0
	distanceLeft := 0
	distanceRight := 0
	for row := targetRow - 1; row >= 0; row-- {
		distanceUp++
		if grid[row][targetCol] >= grid[targetRow][targetCol] {
			break
		}
	}
	for row := targetRow + 1; row < len(grid); row++ {
		distanceDown++
		if grid[row][targetCol] >= grid[targetRow][targetCol] {
			break
		}
	}
	for col := targetCol - 1; col >= 0; col-- {
		distanceLeft++
		if grid[targetRow][col] >= grid[targetRow][targetCol] {
			break
		}
	}
	for col := targetCol + 1; col < len(grid[0]); col++ {
		distanceRight++
		if grid[targetRow][col] >= grid[targetRow][targetCol] {
			break
		}
	}
	return distanceUp * distanceDown * distanceLeft * distanceRight
}
