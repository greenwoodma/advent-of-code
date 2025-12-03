List<String> banks = (new File("input.txt")).readLines()

long total = 0;

for (String bank : banks) {

	// convert the string into an array of ints
	int[] batteries = Arrays.stream(bank.toCharArray() as String[]).mapToInt(Integer::parseInt).toArray();
	
	// get the max number, ignoring the last digit
	(d1, p1) = findMax(batteries,0, batteries.length-1);
	
	// get the max number after the number we found above
	(d2, p2) = findMax(batteries,p1+1, batteries.length);
	
	// make the number by adding the two digits consecutively
	total += Long.valueOf(d1+""+d2)
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
