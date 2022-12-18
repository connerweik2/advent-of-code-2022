from collections import deque


lines = []
with open('./input.txt', 'r') as f:
    for line in f:
        lines.append(line)

stacks = []
for i in range(9):
    stacks.append(deque())

for i in range(7, -1, -1):
    for j in range(9):
        if lines[i][j * 4 + 1] != ' ':
            stacks[j].append(lines[i][j * 4 + 1])

for i in range(10, len(lines)):
    split = lines[i].split(' ')

    num_moves = int(split[1])
    stack_from = stacks[int(split[3]) - 1]
    stack_to = stacks[int(split[5]) - 1]

    temp_stack = deque()

    for j in range(num_moves):
        temp_stack.append(stack_from.pop())
    
    for j in range(num_moves):
        stack_to.append(temp_stack.pop())

for i in range(9):
    print(stacks[i].pop(), end='')
print()