links = [:]

(new File("input.txt")).eachLine{line ->
	data = line.split("[\\s:]+") as List
	
	from = data.remove(0)
	links.put(from,data)
}

paths = [["you"]]

found = []

// this finds all possible paths through the graph, which is
// helpful for debugging etc., but is not very efficient, but
// it's what my mind jumped for initially so I could compare
// the paths I was finding with the sample (I should know better
// by now!
while (!paths.isEmpty()) {
	
	// remove the next path to extend
	path = paths.remove(0);
	
	// this is the last element of the path
	end = path.last()
	
	if (end.equals("out")) {
		// if we are at the end then add it to the
		// set of full paths
		found.add(path)
		continue
	}
	
	for (next : links.get(end)) {
		// for each possible next step
		if (!path.contains(next)) {
			// if it won't introduce a loop then
			// add it to the end of the current path
			// as a new path to check
			
			// note that having completed part 2 I know
			// that there are no loops in the data so the
			// check is redundant as we always end up here
			paths.add([*path, next])			
		}
	}
}

println found.size()
