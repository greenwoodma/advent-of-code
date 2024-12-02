def lines = (new File("input.txt")).readLines()

long total = 0;



for (int r = 0 ; r < lines.size() ; ++r) {


	def matcher = lines[r] =~ /[*]/
	
	while (matcher.find()) {
		
		def adj = new ArrayList();
		int pos = matcher.start();
		
		for (int i = Math.max(r-1,0) ; i <= Math.min(lines.size()-1,r+1) ; ++i) {
			def numbers = lines[i] =~ /\d+/
			
			while (numbers.find()) {
				int start = numbers.start()-1
				int end = numbers.end()
				
				if (pos >= start && pos <= end) {
					adj.add(Long.valueOf(numbers.group()));
				}
			}
		}
		
		if (adj.size() == 2) {
			total += adj.get(0)*adj.get(1);
		}
	}
}


System.out.println(total);
