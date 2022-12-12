package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
)

func main() {
	file, err := os.Open("./input.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	result := 0

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		thisLine := (scanner.Text())

		var left [256]bool
		var right [256]bool

		for i := 0; i < len(thisLine)/2; i++ {
			left[thisLine[i]] = true
		}
		for i := len(thisLine) / 2; i < len(thisLine); i++ {
			right[thisLine[i]] = true
		}

		for i := 0; i < 256; i++ {
			if left[i] && right[i] {
				if i >= 'a' && i <= 'z' {
					result += int(i - 'a' + 1)
				} else {
					result += int(i - 'A' + 27)
				}
				break
			}
		}
	}

	fmt.Println(result)

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
