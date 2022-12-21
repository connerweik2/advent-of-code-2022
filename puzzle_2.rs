use std::fs::File;
use std::io::{self, BufRead};

fn main() {
    let input_file = File::open("./input.txt").expect("Failed to open input file");

    let _input = io::BufReader::new(input_file).lines().next().unwrap().unwrap();
    let input = _input.as_bytes();

    let mut freq = [0; 500];

    for i in 0..13 {
        freq[input[i as usize] as usize] += 1;
    }

    for i in 13..input.len() {
        freq[input[i as usize] as usize] += 1;

        let mut all_unique = true;

        for j in 0..256 {
            if freq[j] > 1 {
                all_unique = false;
                break;
            }
        }

        if all_unique {
            println!("{}", i + 1);
            break;
        }

        freq[input[(i - 13) as usize] as usize] -= 1
    }
}