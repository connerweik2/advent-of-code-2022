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
			if visible(targetRow, targetCol, grid) {
				result++
			}
		}
	}

	fmt.Println(result)

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}

func visible(targetRow int, targetCol int, grid [][]int) bool {
	visibleUp := true
	visibleDown := true
	visibleLeft := true
	visibleRight := true
	for row := 0; row < targetRow; row++ {
		if grid[row][targetCol] >= grid[targetRow][targetCol] {
			visibleUp = false
			break
		}
	}
	for row := targetRow + 1; row < len(grid); row++ {
		if grid[row][targetCol] >= grid[targetRow][targetCol] {
			visibleDown = false
			break
		}
	}
	for col := 0; col < targetCol; col++ {
		if grid[targetRow][col] >= grid[targetRow][targetCol] {
			visibleLeft = false
			break
		}
	}
	for col := targetCol + 1; col < len(grid[0]); col++ {
		if grid[targetRow][col] >= grid[targetRow][targetCol] {
			visibleRight = false
			break
		}
	}
	return visibleUp || visibleDown || visibleLeft || visibleRight
}
