use std::fs::File;
use std::io::{self, BufRead};

fn main() {
    let input_file = File::open("./input.txt").expect("Failed to open input file");

    let mut result_score: i32 = 0;
    let mut current_sum: i32 = 0;

    let lines = io::BufReader::new(input_file).lines();
    for line in lines {
        let line_bytes = line.as_bytes();
        let opponent_choice: i32 = 
        let opponent_choice
        if parse_result.is_ok() {
            current_sum += parse_result.unwrap();
        } else {
            if current_sum > max {
                max = current_sum;
            }
            current_sum = 0;
        }
    }

    if current_sum > max {
        max = current_sum;
    }

    println!("{max}");

}