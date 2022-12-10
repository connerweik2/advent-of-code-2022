use std::fs::File;
use std::io::{self, BufRead};

fn main() {
    let input_file = File::open("./input.txt").expect("Failed to open input file");
    let lines = io::BufReader::new(input_file).lines();
    for line in lines {
        println!("{line}");
    }
}