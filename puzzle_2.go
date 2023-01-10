package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
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

	numRows := len(lines)
	numCols := len(lines[0])

	var grid [][]int
	var visited [][]bool

	endRow, endCol := 0, 0

	var queue [][]int

	for row := 0; row < numRows; row++ {
		grid = append(grid, make([]int, numCols))
		visited = append(visited, make([]bool, numCols))
		for col := 0; col < numCols; col++ {
			c := lines[row][col]
			if c == 'S' {
				visited[row][col] = true
				queue = append(queue, []int{row, col})
				grid[row][col] = int('a')
			} else if c == 'E' {
				endRow = row
				endCol = col
				grid[row][col] = int('z')
			} else if c == 'a' {
				visited[row][col] = true
				queue = append(queue, []int{row, col})
				grid[row][col] = int('a')
			} else {
				grid[row][col] = int(c)
			}
		}
	}

	result := 1

	dir := [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

	for {
		size := len(queue)
		for i := 0; i < size; i++ {
			point := queue[0]
			queue = queue[1:]
			row := point[0]
			col := point[1]

			for j := 0; j < len(dir); j++ {
				newRow := row + dir[j][0]
				newCol := col + dir[j][1]

				if newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols &&
					!visited[newRow][newCol] && grid[newRow][newCol] <= grid[row][col]+1 {
					if newRow == endRow && newCol == endCol {
						fmt.Println(result)
						return
					}

					visited[newRow][newCol] = true
					queue = append(queue, []int{newRow, newCol})
				}
			}
		}

		result++
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
