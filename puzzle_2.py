lines = []

with open('./input.txt', 'r') as f:
    for line in f:
        lines.append(line.strip())

x = 1

i = 0
cycle = 1
halfway_through_addx = False

num_noop = 0
num_addx = 0

while i < len(lines):
    line = lines[i]
    split = line.split(' ')

    if abs(x - ((cycle - 1) % 40)) <= 1:
        print('#', end='')
    else:
        print('.', end='')

    if cycle % 40 == 0:
        print()

    if split[0] == 'noop':
        i += 1
    elif halfway_through_addx:
        x += int(split[1])
        halfway_through_addx = False
        i += 1
    elif split[0] == 'addx':
        halfway_through_addx = True
    
    cycle += 1