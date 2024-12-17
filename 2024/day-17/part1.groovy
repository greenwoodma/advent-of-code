def input = (new File("input.txt")).readLines()

A = input[0].split("\\s+")[2] as Long
B = input[1].split("\\s+")[2] as Long
C = input[2].split("\\s+")[2] as Long

app = input[4].split("\\s+")[1].split(",")*.toInteger()
registers = [A, B, C]

pointer = 0

output = []

while (pointer < app.size()) {
	opcode = app[pointer]
	operand = app[pointer+1]
	combo  = (operand <= 3 ? operand : registers[operand-4])
	
	switch (opcode) {
	    case 0:
	    		registers[0] = (Long)Math.floor(registers[0]/Math.pow(2,combo))
	    		break;
	    	case 1:
	    		registers[1] = (registers[1] ^ operand)
	    		break;
		case 2:
			registers[1] = combo % 8
			break;
		case 3:
			if (registers[0] == 0) break;
			pointer = operand
			continue
		case 4:
			registers[1] = (registers[1] ^ registers[2])
			//pointer = pointer + 2
			break
		case 5:
			output.add((combo % 8))
			//pointer = pointer + 2
			break
		case 6:
			registers[1] = (Long)Math.floor(registers[0]/Math.pow(2,combo))
			break;
		case 7:
			registers[2] = (Long)Math.floor(registers[0]/Math.pow(2,combo))
	}
	
	pointer = pointer+2
}

println output.join(",")
