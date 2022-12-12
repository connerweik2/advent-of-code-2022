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
		var first [256]bool
		var second [256]bool
		var third [256]bool

		thisLine := scanner.Text()
		for i := 0; i < len(thisLine); i++ {
			first[thisLine[i]] = true
		}

		scanner.Scan()
		thisLine = scanner.Text()
		for i := 0; i < len(thisLine); i++ {
			second[thisLine[i]] = true
		}

		scanner.Scan()
		thisLine = scanner.Text()
		for i := 0; i < len(thisLine); i++ {
			third[thisLine[i]] = true
		}

		for i := 0; i < 256; i++ {
			if first[i] && second[i] && third[i] {
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
