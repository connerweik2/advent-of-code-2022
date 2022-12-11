result_score = 0

with open('./input.txt', 'r') as f:
    for this_line in f:
        this_line_stripped = this_line.lstrip().rstrip()
        opponent_choice = ord(this_line_stripped[0]) - ord('A')
        round_result = ord(this_line_stripped[2]) - ord('X')

        if round_result ==  1:
            result_score += 3
        elif round_result == 2:
            result_score += 6
        result_score += (opponent_choice + round_result - 1 + 3) % 3 + 1
    
print(result_score)