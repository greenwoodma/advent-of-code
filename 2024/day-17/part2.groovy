result = 0

def input = (new File("input.txt")).readLines()
app = input[4].split("\\s+")[1].split(",")*.toInteger()

// while A != 0
//   2,4: B = A % 8
//   1,1: B = B XOR 1
//   7,5: C = A / 2^B
//   0,3: A = A / 2^3
//   4,3: B = B XOR C
//   1,6: B = B XOR 6
//   5,5: print B % 8

