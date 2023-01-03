lines = []

with open('./input.txt', 'r') as f:
    lines = [line.strip() for line in f.readlines()]

grid = [[ord(c) - ord('0') for c in line] for line in lines]

def scenic_score(target_row, target_col):
    distance_up, distance_down, distance_left, distance_right = 0, 0, 0, 0
    for row in range(target_row - 1, -1, -1):
        distance_up += 1
        if grid[row][target_col] >= grid[target_row][target_col]:
            break
    for row in range(target_row + 1, len(grid)):
        distance_down += 1
        if grid[row][target_col] >= grid[target_row][target_col]:
            break
    for col in range(target_col - 1, -1, -1):
        distance_left += 1
        if grid[target_row][col] >= grid[target_row][target_col]:
            break
    for col in range(target_col + 1, len(grid[0])):
        distance_right += 1
        if grid[target_row][col] >= grid[target_row][target_col]:
            break
    return distance_up * distance_down * distance_left * distance_right

result = 0

for target_row in range(len(grid)):
    for target_col in range(len(grid[0])):
        score = scenic_score(target_row, target_col)
        if score > result:
            result = score
            
print(result)