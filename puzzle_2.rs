use std::fs::File;
use std::io::{self, BufRead};

fn main() {
    let input_file = File::open("./input.txt").expect("Failed to open input file");

    let mut lines = io::BufReader::new(input_file).lines();

    let mut stacks = [(); 9].map(|_| Vec::new());

    let mut initial_state = [[' ' as u8; 9]; 8];

    for i in 0..8 {
        let line = lines.next().unwrap().unwrap();
        let line_bytes = line.as_bytes();
        for j in 0..9 {
            initial_state[i][j] = line_bytes[j * 4 + 1];
        }
    }

    for i in (0..8).rev() {
        for j in 0..9 {
            if initial_state[i][j] != ' ' as u8 {
                stacks[j].push(initial_state[i][j]);
            }
        }
    }

    lines.next();
    lines.next();

    for line in lines {
        let line_unwrapped = line.unwrap();
        let line_split = line_unwrapped.split(" ").collect::<Vec<&str>>();
        
        let num_moves = line_split[1].parse::<i32>().unwrap();
        let from_stack = line_split[3].parse::<i32>().unwrap() as usize - 1;
        let to_stack = line_split[5].parse::<i32>().unwrap() as usize - 1;

        let mut temp_stack = Vec::<u8>::new();

        for _ in 0..num_moves {
            let popped_val = stacks[from_stack].pop().unwrap();
            temp_stack.push(popped_val);
        }

        for _ in 0..num_moves {
            let popped_val = temp_stack.pop().unwrap();
            stacks[to_stack].push(popped_val);
        }
    }

    for i in 0..9 {
        print!("{}", stacks[i].pop().unwrap() as char);
    }
    println!();
}