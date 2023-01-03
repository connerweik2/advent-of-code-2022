lines = []

with open('./input.txt', 'r') as f:
    lines = [line.strip() for line in f.readlines()]

grid = [[ord(c) - ord('0') for c in line] for line in lines]

def visible(target_row, target_col):
    visible_up, visible_down, visible_left, visible_right = True, True, True, True
    for row in range(target_row):
        if grid[row][target_col] >= grid[target_row][target_col]:
            visible_up = False
            break
    for row in range(target_row + 1, len(grid)):
        if grid[row][target_col] >= grid[target_row][target_col]:
            visible_down = False
            break
    for col in range(target_col):
        if grid[target_row][col] >= grid[target_row][target_col]:
            visible_left = False
            break
    for col in range(target_col + 1, len(grid[0])):
        if grid[target_row][col] >= grid[target_row][target_col]:
            visible_right = False
            break
    return visible_up or visible_down or visible_left or visible_right

result = 0

for target_row in range(len(grid)):
    for target_col in range(len(grid[0])):
        if visible(target_row, target_col):
            result += 1
            
print(result)