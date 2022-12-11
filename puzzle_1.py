result_score = 0

with open('./input.txt', 'r') as f:
    for this_line in f:
        this_line_stripped = this_line.lstrip().rstrip()
        opponent_choice = ord(this_line_stripped[0]) - ord('A')
        my_choice = ord(this_line_stripped[2]) - ord('X')

        result_score += my_choice + 1
        if opponent_choice == my_choice:
            result_score += 3
        elif (opponent_choice + 1) % 3 == my_choice:
            result_score += 6
    
print(result_score)