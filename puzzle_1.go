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

	scanner := bufio.NewScanner(file)
	scanner.Scan()
	text := scanner.Text()

	var freq [256]int

	for i := 0; i < 3; i++ {
		freq[text[i]]++
	}

	for i := 3; i < len(text); i++ {
		freq[text[i]]++

		allUnique := true

		for j := 0; j < 256; j++ {
			if freq[j] > 1 {
				allUnique = false
				break
			}
		}

		if allUnique {
			fmt.Println(i + 1)
			break
		}

		freq[text[i-3]]--
	}

	if err := scanner.Err(); err != nil {
		log.Fatal(err)
	}
}
