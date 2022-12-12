result = 0

current_line_within_group = 0

with open('./input.txt', 'r') as f:
    for line in f:
        line_strip = line.strip()
        
        left = set()
        right = set()

        for i in range(len(line_strip) // 2):
            left.add(line_strip[i])
        for i in range(len(line_strip) // 2, len(line_strip)):
            right.add(line_strip[i])

        for c in left:
            if c in right:
                if c.islower():
                    result += ord(c) - ord('a') + 1
                else:
                    result += ord(c) - ord('A') + 27
                break
            
print(result)