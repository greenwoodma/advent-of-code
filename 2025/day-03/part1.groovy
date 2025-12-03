List<String> banks = (new File("input.txt")).readLines()

long total = 0;

for (String bank : banks) {
	int[] batteries = Arrays.stream(bank.toCharArray() as String[]).mapToInt(Integer::parseInt).toArray();
	//System.out.println("\n"+batteries)
	(d1, p1) = findMax(batteries,0, batteries.length-1);
	(d2, p2) = findMax(batteries,p1+1, batteries.length);
	
	//System.out.println(d1+""+d2)
	total += Long.valueOf(d1+""+d2)
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
