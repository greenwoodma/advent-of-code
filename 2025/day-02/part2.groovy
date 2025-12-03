String ranges = (new File("input.txt")).readLines()[0];

// this will hold all the valid sequences
// note that holding them outside the loop means we are assuming that
// none of the ranges we are given overlap, but that seems reasonable
valid = new HashSet();

for (String range : ranges.split(",")) {
	
	// for each range we get the start and end as numbers
	(start, end) =  Arrays.stream(range.split("-")).mapToLong(Long::parseLong).toArray();
	
	// this is the maximum number of chunks we can split the number into
	// i.e. it will be a string of identical digits 
	int max = end.toString().length();
	
	// we could re-use the start variable but for clarity this is the number
	// we are going to check next
	long next = start
	
	while (next <= end) {
	
		// convert the number back into a string so we can chop it up etc.
		String string = next.toString();
		
		for (int i = 2 ; i <= max ; ++i) {
			// lets check splitting it into pieces starting with 2
			// and working up to the max
			
			int length = string.length()
			
			if (length % i == 0) {
				// if the string is perfectly divisible by i then we can split it into i equal chunks
				
				// \G is a zero-width assertion that matches the position where the previous match ended.
				// If there was no previous match, it matches the beginning of the input. The look behind
				// matches the position that's X characters along from the end of the last match, in
				// this case that's the length of the chunks we want to check
				parts = new HashSet(string.split("(?<=\\G.{"+(length/i)+"})") as List);
				
				// as we stuck all the chunks into a set, if the set only has one element then
				// all the chunks were the same and this is a valid sequence
				if (parts.size() == 1) valid.add(next)
			}
		}
		
		// move on to the next number
		++next;
	}

}

// display the total by summing all the valid sequences
System.out.println(valid.sum());
