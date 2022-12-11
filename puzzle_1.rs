use std::fs::File;
use std::io::{self, BufRead};
use std::collections::BinaryHeap;
use std::cmp::Reverse;


fn main() {
    let input_file = File::open("./input.txt").expect("Failed to open input file");

    let mut heap = BinaryHeap::new();
    heap.push(Reverse(-1));
    heap.push(Reverse(-1));
    heap.push(Reverse(-1));

    let mut current_sum: i32 = 0;

    let lines = io::BufReader::new(input_file).lines();
    for line in lines {
        let line_unwrapped = line.unwrap();
        let parse_result = line_unwrapped.parse::<i32>();
        if parse_result.is_ok() {
            current_sum += parse_result.unwrap();
        } else {
            let Reverse(peek_value) = *heap.peek().unwrap();
            if current_sum > peek_value {
                heap.pop();
                heap.push(Reverse(current_sum));
            }
            current_sum = 0;
        }
    }

    let Reverse(peek_value) = *heap.peek().unwrap();
    if current_sum > peek_value {
        heap.pop();
        heap.push(Reverse(current_sum));
    }

    let mut result_sum = 0;
    while let Some(Reverse(pop_value)) = heap.pop() {
        result_sum += pop_value;
    }

    println!("{result_sum}");

}