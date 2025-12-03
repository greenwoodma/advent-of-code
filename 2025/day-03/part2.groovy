List<String> banks = (new File("input.txt")).readLines()

long total = 0;

for (String bank : banks) {
	int[] batteries = Arrays.stream(bank.toCharArray() as String[]).mapToInt(Integer::parseInt).toArray();
		
	int end = batteries.length - 11;
	int start = 0;
	
	String n = "";
	
	
	
	for (int p = 0 ; p < 12 ; ++p) {
		(d1, next) = findMax(batteries, start, end);
		
		n = n+""+d1;
		++end;
		start = next+1
	}
	
	

	//System.out.println(d1+""+d2)
	total += Long.valueOf(n)
}

System.out.println(total)

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
