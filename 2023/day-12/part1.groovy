long total = 0;

new File("input.txt").eachLine { line ->
	def data = line.split("\\s+");
	
	// add a space at the end so we can always make sure
	// we have completed the final block without having to
	// check if we are at the end of the sequence
	def row = data[0]+".";

	def counts = data[1].split(",")*.toInteger();
	
	//System.out.println(row);
	//System.out.println(counts);
	//System.out.println(count(row,counts,0,0,0));
	
	total += count(row,counts,0,0,0);
}

System.out.println(total);

def long count(String line, def counts, int pos, int current_length, int completed) {
	if (pos == line.length()) {
		// we've got to the end of the line so it's
		// valid and we should count it if we've
		// completed the right number of blocks
		return completed == counts.size() ? 1 : 0
	}
	
	if (line.charAt(pos) == '#') {
	    // extend the current block (we will worry about
	    // it's length when we see the end
		return count(line, counts, pos + 1, current_length + 1, completed);
	}
	
	if (line.charAt(pos) == '.' || completed == counts.size()) {
		// either we are on a dot or we want to be because we don't want
		// any more # symbols	
	
		if (completed < counts.size() && current_length == counts[completed]) {
			// we've not completed all the blocks yet, but we've just counted
			// enough # symbols to complete the current block
			return count(line, counts, pos+1, 0, completed + 1);
		}
		else if (current_length == 0) {
			// we aren't currently in a block so just move on
			return count(line, counts, pos+1, 0, completed);
		}
		else {
			// we've prematurely finished a block so this sequence isn't valid
			return 0;
		}			
	}
	
	
	// at this point we must be on a ? and don't know if it should be a . or a #
	
	// let's move on by assuming it's a #
	long hashCount = count(line, counts, pos + 1, current_length + 1, completed)

	long dotCount = 0;

	if (current_length == counts[completed]) {
		// we are at the end of a block so see how many we can get from moving
		// on by assuming the next symbol is a .
        dotCount = count(line, counts, pos + 1, 0, completed+1)
	} else if (current_length == 0) {
		// we aren't currently in a block so assume the dot just moves us on one
        dotCount = count(line, counts, pos + 1, 0, completed)
	}
    
    // total number of possible sequences is then the number we get from assuming
    // a hash added to assuming a dot
    return hashCount + dotCount;
	
}
