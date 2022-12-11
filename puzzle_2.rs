use std::fs::File;
use std::io::{self, BufRead};

fn main() {
    let input_file = File::open("./input.txt").expect("Failed to open input file");

    let mut result_score: i32 = 0;

    let lines = io::BufReader::new(input_file).lines();
    for line in lines {
        let line_unwrap = line.unwrap();
        let line_bytes = line_unwrap.as_bytes();

        // The opponent's choice: 0 for rock, 1 for paper, 2 for scissors.
        // And the round result: 0 for loss, 1 for draw, 2 for win.
        let opponent_choice = line_bytes[0] as i32 - 'A' as i32;
        let round_result = line_bytes[2] as i32 - 'X' as i32;

        // Update the result score based on the round result.
        if round_result == 1 {result_score += 3;}
        if round_result == 2 {result_score += 6;}

        // Based on the round result, we know which we will pick:
        // if loss, pick 1 to the left of the opponent in [rock, paper, scissors],
        // wrapping around at the ends; if draw, pick the same as the
        // opponent; if win, pick 1 to the right. Update score accordingly:
        // 1 for rock, 2 for paper, 3 for scissors.
        result_score += (opponent_choice + round_result - 1 + 3) % 3 + 1;
    }

    println!("{result_score}");
}