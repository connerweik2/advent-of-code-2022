lines = []

with open('./input.txt', 'r') as f:
    for line in f:
        lines.append(line.strip())

result = 0

x = 1

i = 0
cycle = 1
halfway_through_addx = False

while i < len(lines):
    line = lines[i]
    split = line.split(' ')
        
    if cycle % 40 == 20:
        result += cycle * x

    if split[0] == 'noop':
        i += 1
    elif halfway_through_addx:
        x += int(split[1])
        halfway_through_addx = False
        i += 1
    elif split[0] == 'addx':
        halfway_through_addx = True

    cycle += 1

print(result)