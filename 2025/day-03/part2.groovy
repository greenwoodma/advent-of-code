List<String> banks = (new File("input.txt")).readLines()

long total = 0;

for (String bank : banks) {

	// convert the string into an array of ints
	int[] batteries = Arrays.stream(bank.toCharArray() as String[]).mapToInt(Integer::parseInt).toArray();
		
	// we are looking for a sequence of twelve so start by not looking
	// at the last 11 batteries
	int end = batteries.length - 11;
	
	// but we can look from the beginning of the bank
	int start = 0;
	
	// this is the 12 digit number we are building
	String n = "";
	
	// for each of the twelve digits
	for (int p = 0 ; p < 12 ; ++p) {
		
		// find biggest digit in the current valid range
		(d1, next) = findMax(batteries, start, end);
		
		// add the digit to the end of the number
		n = n+""+d1;
		
		// next time start from the digit after the one we
		// have just found...
		start = next+1
		
		// ... but we can look at one more digit at the end
		// of the battery bank
		++end;
		
	}

	// add the number to the total
	total += Long.valueOf(n)
}

System.out.println(total)

// returns the max digit (and it's position) between two
// offsets into the array
def findMax(int[] batteries, int start, int end) {
	int max = -1;
	int pos = -1;
	
	for (int j = start ; j < end ; ++j) {
		if (batteries[j] > max) {
			max = batteries[j];
			pos = j
		}
	}
	
	return [max, pos]
}
