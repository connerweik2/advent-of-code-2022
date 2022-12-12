use std::fs::File;
use std::io::{self, BufRead};
use std::collections::HashSet;

fn main() {
    let input_file = File::open("./input.txt").expect("Failed to open input file");

    let mut result: i32 = 0;

    let lines = io::BufReader::new(input_file).lines();
    for line in lines {
        let mut left = HashSet::new();
        let mut right = HashSet::new();
        let line_unwrap = line.unwrap();
        let line_bytes = line_unwrap.as_bytes();

        for i in 0..line_bytes.len()/2 {
            left.insert(line_bytes[i]);
        }
        for i in line_bytes.len()/2..line_bytes.len() {
            right.insert(line_bytes[i]);
        }

        for c in left {
            if right.contains(&c) {
                if c >= b'a' && c <= b'z' {
                    result += (c - b'a' + 1) as i32;
                } else {
                    result += (c - b'A' + 27) as i32;
                }

                break;
            }
        }

    }

    println!("{result}");
}