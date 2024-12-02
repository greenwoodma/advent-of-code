long total = 0;

new File("input.txt").eachLine { line ->
	System.out.println("\n");
	

	def previous = line.split("\\s+")*.toLong()

	
	System.out.println(previous);

	def last = [];	

	while (true) {

		last.add(previous[-1]);

		def next = [];
		
		boolean allZero = true;

		for (int i = 0 ; i < previous.size()-1 ; ++i) {
			next.add(previous[i+1]-previous[i]);
			allZero = allZero && next[-1] == 0;
		}
	
		System.out.println(next);
			
		previous = next;
		
		if (allZero) break;
	}

	System.out.println(last);
	
	long current = 0;
	
	while (last.size() > 0) {
		current += last.remove(last.size()-1);
	}
	
	System.out.println(current);
	
	total += current;
}

System.out.println(total);
