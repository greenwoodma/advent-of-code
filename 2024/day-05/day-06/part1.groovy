def map = []

def previous = null;

new File("input.txt").eachLine { line ->
	guard = line.indexOf("^")
	if (guard != -1) {
		previous = new Tuple2(line.indexOf("^"), map.size());
		line = line.replaceAll("\\^",".")
	}
	
	map.add(line);
}

def visited = new HashSet<Tuple2>()
visited.add(previous)

def direction = 0;

while (true) {
	next  = null;
	
	// work out where we will be next assuming no obstacle
	switch (direction) {
		case 0:
			next = new Tuple2(previous[0], previous[1]-1)
			break;
		case 1:
			next = new Tuple2(previous[0]+1, previous[1])
			break;
		case 2:
			next = new Tuple2(previous[0], previous[1]+1)
			break;
		case 3:
			next = new Tuple2(previous[0]-1, previous[1])
			break;
	}
	
	// next move in the current direction would move us off the map
	if (next[0] < 0 || next[0] >= map[0].length() || next[1] < 0 || next[1] >= map.size())
		break;
		
	if (map[next[1]][next[0]] != ".") {
		// the next position would be an obstacle
		direction = (direction+1)%4
	} else {
		// we can move freely into the next position
		visited.add(next);
		previous = next;
	}
			
}

println visited.size()
