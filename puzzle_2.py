lines = []

with open('./input.txt', 'r') as f:
    lines = [line.strip() for line in f.readlines()]

visited = set()

rope = []
for i in range(10):
    rope.append([0, 0])

visited.add((0, 0))

def processMove(direction):
    if direction == 'U':
        rope[0][1] -= 1
    elif direction == 'D':
        rope[0][1] += 1
    elif direction == 'L':
        rope[0][0] -= 1
    elif direction == 'R':
        rope[0][0] += 1
    for i in range(1, len(rope)):
        if abs(rope[i - 1][0] - rope[i][0]) <= 1 and abs(rope[i - 1][1] - rope[i][1]) <= 1:
            return
        if rope[i - 1][0] > rope[i][0]:
            rope[i][0] += 1
        elif rope[i - 1][0] < rope[i][0]:
            rope[i][0] -= 1
        if rope[i - 1][1] > rope[i][1]:
            rope[i][1] += 1
        elif rope[i - 1][1] < rope[i][1]:
            rope[i][1] -= 1
    visited.add((rope[len(rope) - 1][0], rope[len(rope) - 1][1]))

for line in lines:
    split = line.split(" ")
    direction = split[0]
    distance = int(split[1])
    for i in range(distance):
        processMove(direction)

print(len(visited))