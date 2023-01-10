import queue

def main():
    lines = []

    with open('./input.txt', 'r') as f:
        for line in f:
            lines.append(line.strip())

    num_rows = len(lines)
    num_cols = len(lines[0])

    grid = []
    visited = []

    for row in range(num_rows):
        grid.append([])
        visited.append([])
        for col in range(num_cols):
            visited[row].append(False)
            grid[row].append(None)

    start_row, start_col, end_row, end_col = 0, 0, 0, 0

    q = queue.Queue()

    for row in range(num_rows):
        for col in range(num_cols):
            c = lines[row][col]
            if c == 'S':
                start_row = row
                start_col = col
                visited[row][col] = True
                q.put([row, col])
                grid[row][col] = ord('a')
            elif c == 'E':
                end_row = row
                end_col = col
                grid[row][col] = ord('z')
            elif c == 'a':
                visited[row][col] = True
                q.put([row, col])
                grid[row][col] = ord('a')
            else:
                grid[row][col] = ord(c)

    result = 1

    DIR = [[-1, 0], [1, 0], [0, -1], [0, 1]]

    while True:
        size = q.qsize()
        for i in range(size):
            point = q.get()
            row = point[0]
            col = point[1]
            
            for j in range(len(DIR)):
                new_row = row + DIR[j][0]
                new_col = col + DIR[j][1]

                if (new_row >= 0 and new_row < num_rows and new_col >= 0 and new_col < num_cols and
                not visited[new_row][new_col] and grid[new_row][new_col] <= grid[row][col] + 1):

                    if new_row == end_row and new_col == end_col:
                        print(result)
                        return

                    visited[new_row][new_col] = True
                    q.put([new_row, new_col])

        result += 1

if __name__ == "__main__":
    main()