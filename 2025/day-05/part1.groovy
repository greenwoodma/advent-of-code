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

// convert the rest of the input into a list of lons
ingredients = ingredients.stream().mapToLong(Long::parseLong).toArray()

long valid = 0;

for (ingredient : ingredients) {
	// for each ingredient....
	
	for (range : ranges) {
		//for each range
		
		// don't use contains as it actually checks for the presence
		// of the Object literal whereas we just need bounds checking
		if (range.containsWithinBounds(ingredient)) {
			// the ingredient is in this range so increment the total
			++valid
			break;
		}
	}
}

println valid
