use std::fs::File;
use std::io::{self, BufRead};

fn main() {
    let input_file = File::open("./input.txt").expect("Failed to open input file");

    let mut result_score: i32 = 0;

    let lines = io::BufReader::new(input_file).lines();
    for line in lines {
        let line_unwrap = line.unwrap();
        let line_bytes = line_unwrap.as_bytes();

        // The opponent's choice and our choice.
        // 0 for rock, 1 for paper, 2 for scissors.
        let opponent_choice = line_bytes[0] as i32 - 'A' as i32;
        let my_choice = line_bytes[2] as i32 - 'X' as i32;

        // Update the result score based on our choice:
        // 1 for rock, 2 for paper, 3 for scissors.
        result_score += my_choice + 1;

        // Draw: add 3.
        if opponent_choice == my_choice {
            result_score += 3;
        }
        // Win: Add 6. In a win, our choice will be
        // one to the right of the opponent's in the list
        // [rock, paper, scissors], wrapping around at the ends.
        else if (opponent_choice + 1) % 3 == my_choice {
            result_score += 6;
        }
    }

    println!("{result_score}");
}