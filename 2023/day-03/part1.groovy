def lines = (new File("input.txt")).readLines()

long total = 0;



for (int r = 0 ; r < lines.size() ; ++r) {

	def matcher = lines[r] =~ /\d+/
	
	while (matcher.find()) {
		int start = matcher.start()-1;
		int end = matcher.end();

		outer:
		for (int i = Math.max(r-1,0) ; i <= Math.min(lines.size()-1,r+1) ; ++i) {
			def symbols = lines[i] =~ /[^0-9.]/	
		
			while (symbols.find()) {
				int pos = symbols.start();

				if (pos >= start && pos <= end) {
					total +=  Long.valueOf(matcher.group()); 
					break outer;
				}
			}
		}
	}
}


System.out.println(total);
