import re

lines = []

with open('./input.txt', 'r') as f:
    for line in f:
        lines.append(line.strip())

i = 1

monkeys = []

class Monkey:
    def __init__(self, starting_items, operation, divisor, throw_to_if_true, throw_to_if_false):
        self.items = starting_items
        self.operation = operation
        self.divisor = divisor
        self.throw_to_if_true = throw_to_if_true
        self.throw_to_if_false = throw_to_if_false
        self.inspect_count = 0

while i < len(lines):
    split = re.split('Starting items: |, ', lines[i])
    starting_items = [int(s) for s in split[1:]]
    i += 1

    operation = lines[i].replace('Operation: new = ', '')
    i += 1

    divisor = int(lines[i].split(' ')[-1])
    i += 1

    throw_to_if_true = int(lines[i].split(' ')[-1])
    i += 1

    throw_to_if_false = int(lines[i].split(' ')[-1])
    i += 1

    monkeys.append(Monkey(starting_items, operation, divisor, throw_to_if_true, throw_to_if_false))

    i += 2

num_rounds = 20

for this_round in range(num_rounds):
    for monkey_number in range(len(monkeys)):
        monkey = monkeys[monkey_number]
        for item in monkey.items:
            split = monkey.operation.split(' ')
            left_operand = item if split[0] == 'old' else int(split[0])
            operator = split[1]
            right_operand = item if split[2] == 'old' else int(split[2])
            
            if operator == '+':
                new_worry_level = left_operand + right_operand
            else:
                new_worry_level = left_operand * right_operand
                        
            new_worry_level //= 3

            monkey.inspect_count += 1

            if new_worry_level % monkey.divisor == 0:
                monkeys[monkey.throw_to_if_true].items.append(new_worry_level)
            else:
                monkeys[monkey.throw_to_if_false].items.append(new_worry_level)

        monkey.items.clear()

monkey_activity = [monkey.inspect_count for monkey in monkeys]

monkey_activity.sort()

print(monkey_activity[len(monkey_activity) - 2] * monkey_activity[len(monkey_activity) - 1])