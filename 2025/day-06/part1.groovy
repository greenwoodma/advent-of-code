numbers = []
operands = null

(new File("input.txt")).eachLine{line ->
	
	// in this version we don't care about whitespace so remove it
	line = line.trim();	
	
	// if this is a row of numbers split it into columns and convert
	// the strings to long, otherwise split it to get the operands
	if (line.charAt(0).isDigit())
		numbers.add(Arrays.stream(line.split("\\s+")).mapToLong(Long::parseLong).toArray())
	else
		operands = line.split("\\s+")
}

long total = 0

for (int s = 0 ; s < operands.length ; ++s) {
	// for each operand
	op = operands[s]
	
	// if we are doing + then start with the answer 0
	// but if * start with answer 1
	answer = op.equals("*") ? 1 : 0
	
	for (int n = 0 ; n < numbers.size() ; ++n) {
		// for each number in a column do the maths
		if (op.equals("*"))
			answer = answer * numbers[n][s]
		else
			answer = answer + numbers[n][s]
	}
	
	// add the answer to the running total
	total += answer
}

println total
