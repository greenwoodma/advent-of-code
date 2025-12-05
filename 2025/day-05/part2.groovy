ingredients = (new File("input.txt")).readLines();
ranges = []

while (true) {

	// remove the range ready for processing
	String line = ingredients.remove(0);
	
	// if this is blank we've read them all so move on
	if (line.equals("")) break;
	
	// get the endpoints as long values
	(start,end) = Arrays.stream(line.split("-")).mapToLong(Long::parseLong).toArray();
	
	// store the range (using Groovy's built in Range type)
	ranges.add(start..end);
}

// now sort the ranges by their start offset
ranges.sort{a,b -> a.getFrom() <=> b.getFrom()}

valid = 0;

// the first valid ingredient is the start of the
// first range so let's start from there
long ingredient = ranges.get(0).getFrom()

while (!ranges.isEmpty()) {
	// while we have ranges to check
	
	// remove the next range from the list
	range = ranges.remove(0);
	
	// if the ingredient is less than the start move it up
	// to skip over the gap
	if (ingredient < range.getFrom()) ingredient = range.getFrom();
	
	if (range.containsWithinBounds(ingredient)) {
		// if the ingredient is in a range, then everything
		// from here until the end of the range is valid
		valid += range.getTo() - ingredient + 1;
		
		// and the next ingredient to check is the one
		// after the end of this range
		ingredient = range.getTo()+1
	}
}

println valid
