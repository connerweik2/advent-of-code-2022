input_string = None

with open('./input.txt', 'r') as f:
    input_string = [ord(c) for c in f.readline()]

freq = [0] * 256

for i in range(13):
    freq[input_string[i]] += 1

for i in range(13, len(input_string)):
    freq[input_string[i]] += 1
    all_unique = True
    for j in range(256):
        if freq[j] > 1:
            all_unique = False
    if all_unique:
        print(i + 1)
        break
    freq[input_string[i - 13]] -= 1