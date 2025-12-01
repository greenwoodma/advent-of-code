int pos = 50;

List<String> instructions = (new File("input.txt")).readLines()

int result = 0

for (String line : instructions) {
	dir = line.charAt(0);
	count = Integer.valueOf(line.substring(1));
	
	// if the number of clicks is more than 100 then we certainly
	// pass 0 this many times
	result += Math.floor(count/100f)
	
	// now just deal with the none looping clicks
	count = count % 100
	
	// if going left and the count is greater than the current
	// position, but we aren't starting from 0, then we pass zero
	if (dir == 'L' && count > pos && pos != 0) ++result;
	
	// update to the new position without having to worry about going
	// negative
	pos = dir == 'L' ? pos + (100-count) : pos + count;
	
	// if we went right and are over 100 then we went past zero
	if (dir =='R' && pos > 100) ++result;
	
	// get the position back to within the dial
	pos = pos % 100;
	
	// if we are at zero then add one to the result
	if (pos == 0) ++result;
}

System.out.println(result)
