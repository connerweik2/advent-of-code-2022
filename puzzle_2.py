import heapq

this_calorie_sum = 0
max_calorie_sum = 0

heap = [-1, -1, -1]

with open('./input.txt', 'r') as f:
    for this_line in f:
        if this_line.rstrip().isnumeric():
            # If this is a number, update the current calorie sum.
            this_calorie_sum += int(this_line)
        else:
            # We have finished an elf's calorie list.
            # Update the heap accordingly.
            if this_calorie_sum > heap[0]:
                heapq.heapreplace(heap, this_calorie_sum)
            this_calorie_sum = 0

# Update the heap for the last elf.
if this_calorie_sum > heap[0]:
    heapq.heapreplace(heap, this_calorie_sum)

result_sum = 0
for x in heap:
    result_sum += x

print(result_sum)