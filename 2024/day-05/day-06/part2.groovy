def map = []

def start = null;

new File("input.txt").eachLine { line ->
	guard = line.indexOf("^")
	if (guard != -1) {
		start = new Tuple3(line.indexOf("^"), map.size(),0);
		line = line.replaceAll("\\^",".")
	}
	
	map.add(line);
}

long total = 0;

// this is a naive way of doing this, but it ran quick
// enough that I didn't have to try anything more fancy
for (int r = 0 ; r < map.size() ; ++r) {
	for (int c = 0 ; c < map[r].length() ; ++c) {
		if (map[r][c] == ".") {
			updated = new StringBuilder(map[r]);
			updated.setCharAt(c,'O' as char);
			orig = map[r]
			map[r] = updated.toString();
			if (loops(start,map)) ++total;
			map[r] = orig
		}
	}
}

// a better solution would probably be to test just each position
// visited, as to change the path the obstacle must be in a square
// the guard previously visited. So instead of looping through all
// the cells with a . we just need to check those in the visited
// list from part 1

println total

def boolean loops(previous, map) {
	def visited = new HashSet<Tuple3>()
	visited.add(previous)

	def direction = 0;

	while (true) {
		next  = null;
		
		// work out where we will be next assuming no obstacle
		switch (direction) {
			case 0:
				next = new Tuple3(previous[0], previous[1]-1,direction)
				break;
			case 1:
				next = new Tuple3(previous[0]+1, previous[1],direction)
				break;
			case 2:
				next = new Tuple3(previous[0], previous[1]+1,direction)
				break;
			case 3:
				next = new Tuple3(previous[0]-1, previous[1],direction)
				break;
		}
		
		// next move in the current direction would move us off the map
		if (next[0] < 0 || next[0] >= map[0].length() || next[1] < 0 || next[1] >= map.size())
			return false;
			
		if (map[next[1]][next[0]] != ".") {
			// the next position would be an obstacle
			direction = (direction+1)%4
		} else if (visited.contains(next)) {
			// moving into the next position takes us back to somewhere
			// we have been in the same orientation so it's a loop
			return true;
		} else {
			// we can move freely into the next position
			visited.add(next);
			previous = next;
		}
				
	}
}
