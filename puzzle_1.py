from enum import Enum

class CompareResult(Enum):
    IN_ORDER = 1
    OUT_OF_ORDER = 2
    INCONCLUSIVE = 3

lines = []

def construct_packet_from_string(s):
    current_list = []
    parent_stack = []
    i = 1
    while i < len(s):
        if i == len(s) - 1:
            return current_list
        elif s[i] == '[':
            new_child_list = []
            parent_stack.append(current_list)
            current_list.append(new_child_list)
            current_list = new_child_list
            i += 1
        elif s[i] == ']':
            current_list = parent_stack.pop()
            i += 1
        elif s[i] == ',':
            i += 1
        else:
            current_number_str = ''
            while s[i].isdigit():
                current_number_str += s[i]
                i += 1
            current_list.append(int(current_number_str))

def convert_to_string(x):
    if type(x) == int:
        return str(x)
    return_string = '['
    for i in range(len(x)):
        return_string += convert_to_string(x[i])
        if i < len(x) - 1:
            return_string += ','
    return_string += ']'
    return return_string

def compare(left, right):
    # print('Compare', convert_to_string(left), 'with', convert_to_string(right))
    if type(left) == int and type(right) == int:
        if left < right:
            # print('Left side is smaller, so inputs are in the right order')
            return CompareResult.IN_ORDER
        elif right < left:
            # print('Right side is smaller, so inputs are not in the right order')
            return CompareResult.OUT_OF_ORDER
        else:
            return CompareResult.INCONCLUSIVE

    elif type(left) == list and type(right) == list:
        for i in range(min(len(left), len(right))):
            result = compare(left[i], right[i])
            if result != CompareResult.INCONCLUSIVE:
                return result
        if len(left) < len(right):
            # print('Left side ran out of items, so inputs are in the right order')
            return CompareResult.IN_ORDER
        elif len(right) < len(left):
            # print('Right side ran out of items, so inputs are not in the right order')
            return CompareResult.OUT_OF_ORDER
        else:
            return CompareResult.INCONCLUSIVE
    
    else:
        if type(left) == int and type(right) == list:
            return compare([left], right)
        else:
            return compare(left, [right])

with open('./input.txt', 'r') as f:
    for line in f:
        lines.append(line.strip())

result = 0

i = 0

pair_index = 1

while i < len(lines):
    first_line = lines[i]
    i += 1
    second_line = lines[i]
    i += 2

    first_packet = construct_packet_from_string(first_line)
    second_packet = construct_packet_from_string(second_line)

    if compare(first_packet, second_packet) == CompareResult.IN_ORDER:
        result += pair_index
    
    pair_index += 1

print(result)