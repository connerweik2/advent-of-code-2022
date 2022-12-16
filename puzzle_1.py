result = 0

with open('./input.txt', 'r') as f:
    for line in f:
        line_processed = line.strip().split(',')
        first_min = int(line_processed[0][0:line_processed[0].index('-')])
        first_max = int(line_processed[0][line_processed[0].index('-')+1:])
        second_min = int(line_processed[1][0:line_processed[1].index('-')])
        second_max = int(line_processed[1][line_processed[1].index('-')+1:])

        if first_min >= second_min and first_max <= second_max\
        or second_min >= first_min and second_max <= first_max:
           result += 1

            
print(result)