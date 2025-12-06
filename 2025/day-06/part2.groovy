numbers = (new File("input.txt")).readLines();
operands = numbers.removeLast();

long total = 0;

while (operands.length() > 0) {

	// get the substring with the operand for the next question
	// and then remove it so we do each question once
	section = operands.find("[*+]\\s+")
	operands = operands.substring(section.length())
	
	// build a column of numbers by cutting of the same bits
	// of string from the rows of numbers
	column = []
	for (int n = 0 ; n < numbers.size() ; ++n) {
		column.add(numbers[n].substring(0,section.length()))		
		numbers[n] = numbers[n].substring(section.length());
	}
	
	// trim this down to just the operand
	section = section[0]
	
	// if we are doing + then start with the answer 0
	// but if * start with answer 1
	answer = section.equals("*") ? 1 : 0
	
	for (int n = 0 ; n < column[0].length() ; ++n) {
		// now build a string by adding all the digits
		// in a column together working down from the
		// top and skipping empty spaces
		number = "";
		for (int r = 0 ; r < column.size() ; ++r) {
			d = column[r][n] as char
			if (d != ' ') number += d
		}
		
		if (!number.equals("")) {
			// assuming we have a number convert
			// it to an actual number
			number = Long.valueOf(number)
			
			// and finally do the maths
			if (section.equals("*"))
				answer = answer * number
			else
				answer = answer + number
		}
	}

	// add the answer to this puzzle to the total
	total += answer
}

println total
