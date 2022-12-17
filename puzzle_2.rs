use std::fs::File;
use std::io::{self, BufRead};

fn main() {
    let input_file = File::open("./input.txt").expect("Failed to open input file");

    let mut result: i32 = 0;

    let lines = io::BufReader::new(input_file).lines();
    for line in lines {
        let line_unwrap = line.unwrap();
        let split = line_unwrap.split(",").collect::<Vec<&str>>();
        let first_split = split[0].split("-").collect::<Vec<&str>>();
        let second_split = split[1].split("-").collect::<Vec<&str>>();
        let first_min = first_split[0].parse::<i32>().unwrap();
        let first_max = first_split[1].parse::<i32>().unwrap();
        let second_min = second_split[0].parse::<i32>().unwrap();
        let second_max = second_split[1].parse::<i32>().unwrap();

        if first_min <= second_max && first_max >= second_min {
            result += 1;
        }
    }

    println!("{result}");
}