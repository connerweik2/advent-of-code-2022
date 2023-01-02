package main

import (
	"bufio"
	"fmt"
	"log"
	"math"
	"os"
	"strconv"
	"strings"
)

type Node struct {
	parent   *Node
	isDir    bool
	size     int
	children map[string]*Node
}

func NewNode(parent *Node, isDir bool, size int) *Node {
	n := Node{parent, isDir, size, make(map[string]*Node)}
	return &n
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
		lines = append(lines, scanner.Text())
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}

	root := &Node{nil, true, 0, make(map[string]*Node)}
	currentNode := root

	i := 1
	for i < len(lines) {
		split := strings.Split(strings.TrimSpace(lines[i]), " ")
		i++
		if i == len(lines) {
			break
		}
		if split[1] == "ls" {
			for i < len(lines) && !strings.Contains(lines[i], "$") {
				split = strings.Split(lines[i], " ")
				if split[0] == "dir" {
					currentNode.children[split[1]] = NewNode(currentNode, true, 0)
				} else {
					size, _ := strconv.Atoi(split[0])
					currentNode.children[split[1]] = NewNode(currentNode, false, size)
				}
				i++
			}
		} else {
			if split[2] == ".." {
				currentNode = currentNode.parent
			} else {
				currentNode = currentNode.children[split[2]]
			}
		}
	}

	var dirSizes []int

	SetDirSizes(root, &dirSizes)

	result := math.MaxInt

	totalSpace := 70000000
	unusedSpaceRequirement := 30000000
	threshold := 0
	if unusedSpaceRequirement-(totalSpace-root.size) > threshold {
		threshold = unusedSpaceRequirement - (totalSpace - root.size)
	}

	for _, dirSize := range dirSizes {
		if dirSize >= threshold && dirSize < result {
			result = dirSize
		}
	}

	fmt.Println(result)
}

func setDirSizes(root *Node, dirSizes *[]int) int {
	if !root.isDir {
		return root.size
	}
	size := 0
	for _, child := range root.children {
		size += SetDirSizes(child, dirSizes)
	}
	root.size = size
	*dirSizes = append(*dirSizes, size)
	return size
}
