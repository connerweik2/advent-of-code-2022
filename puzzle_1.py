class Node:
    def __init__(self, parent, is_dir, size):
        self.parent = parent
        self.is_dir = is_dir
        self.size = size
        self.children = {}

lines = []

with open('./input.txt', 'r') as f:
    lines = f.readlines()

lines = [line.strip() for line in lines]

root = Node(None, True, 0)
current_node = root

i = 1
while i < len(lines):
    split = lines[i].split(' ')
    i += 1
    if i == len(lines):
        break
    if split[1] == 'ls':
        while i < len(lines) and '$' not in lines[i]:
            split = lines[i].split(' ')
            if split[0] == 'dir':
                current_node.children[split[1]] = Node(current_node, True, 0)
            else:
                current_node.children[split[1]] = Node(current_node, False, int(split[0]))
            i += 1
    elif split[1] == 'cd':
        if split[2] == '..':
            current_node = current_node.parent
        else:
            current_node = current_node.children[split[2]]

dir_sizes = []

def set_dir_sizes(root):
    if not root.is_dir:
        return root.size
    root.size = sum([set_dir_sizes(child) for child in root.children.values()])
    dir_sizes.append(root.size)
    return root.size

set_dir_sizes(root)

print(sum([dir_size for dir_size in dir_sizes if dir_size <= 100000]))