result = 0

first = set()
second = set()
third = set()

current_line_within_group = 0

with open('./input.txt', 'r') as f:
    for line in f:
        line_strip = line.strip()
        for c in line_strip:
            if current_line_within_group == 0:
                first.add(c)
            elif current_line_within_group == 1:
                second.add(c)
            elif current_line_within_group == 2:
                third.add(c)
        
        if current_line_within_group == 2:
            for c in first:
                if c in second and c in third:
                    if c.islower():
                        result += ord(c) - ord('a') + 1
                    else:
                        result += ord(c) - ord('A') + 27
                    break
            first = set()
            second = set()
            third = set()
        
        current_line_within_group = (current_line_within_group + 1) % 3
    
print(result)