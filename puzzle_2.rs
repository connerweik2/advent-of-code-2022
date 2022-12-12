use std::fs::File;
use std::io::{self, BufRead};
use std::collections::HashSet;

fn main() {
    let input_file = File::open("./input.txt").expect("Failed to open input file");

    let mut result: i32 = 0;
    let mut line_within_group = 1;

    let mut first = HashSet::new();
    let mut second = HashSet::new();
    let mut third = HashSet::new();

    let lines = io::BufReader::new(input_file).lines();
    for line in lines {
        let line_unwrap = line.unwrap();
        let line_bytes = line_unwrap.as_bytes();

        for i in 0..line_bytes.len() {
            if line_within_group == 1 {first.insert(line_bytes[i]);}
            else if line_within_group == 2 {second.insert(line_bytes[i]);}
            else {third.insert(line_bytes[i]);}
        }

        if line_within_group == 3 {
            for c in &first {
                if second.contains(&c) && third.contains(&c) {
                    if c >= &b'a' && c <= &b'z' {
                        result += (c - b'a' + 1) as i32;
                    } else {
                        result += (c - b'A' + 27) as i32;
                    }
    
                    break;
                }
            }

            first = HashSet::new();
            second = HashSet::new();
            third = HashSet::new();
            line_within_group = 1;
        } else {
            line_within_group += 1;
        }
    }

    println!("{result}");
}