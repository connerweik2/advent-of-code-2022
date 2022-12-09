this_calorie_sum = 0
max_calorie_sum = 0

with open('./input.txt', 'r') as f:
    for this_line in f:
        if this_line.rstrip().isnumeric():
            # If this is a number, update the current calorie sum.
            this_calorie_sum += int(this_line)
        else:
            # We have finished an elf's calorie list.
            # Update max and reset current calorie sum.
            if this_calorie_sum > max_calorie_sum:
                max_calorie_sum = this_calorie_sum
            this_calorie_sum = 0

# Update the max for the last elf.
if this_calorie_sum > max_calorie_sum:
    max_calorie_sum = this_calorie_sum

print(max_calorie_sum)