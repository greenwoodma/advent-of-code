import groovy.transform.Memoized;

// read all the points into a list, storing the points as a Tuple
// (in retrospect we might not have needed the tuple maybe the array
// would have been good enough
points = []
(new File("input.txt")).eachLine{line ->
	(x,y,z) = Arrays.stream(line.split(",")).mapToLong(Long::parseLong).toArray()	
	points.add(new Tuple(x,y,z))
}

// now build a list of all possible edges
edges = []
for (int i = 0 ; i < points.size()-1 ; ++i) {
	for (int j = i+1 ; j < points.size(); ++j) {
		edges.add(new Tuple(points[i], points[j]))
	}
}

// sort the edges, shortest first
edges.sort{e1,e2 -> return distance(e1[0], e1[1]) - distance(e2[0], e2[1])}

// this will hold a map so we can quickly find
// the circuit any given point is in
circuits = new HashMap()

while (true) {
	// while we haven't yet connected everything together

	// get the next edge
	edge = edges.remove(0);
	
	// find any existing circuits this edge connects to
	c1 = circuits[edge[0]]
	c2 = circuits[edge[1]]
	
	// create a new circuit from this edge
	circuit = new HashSet()
	circuit.addAll(edge);
	
	// and the points from any circuits we connect to
	if (c1 != null) circuit.addAll(c1);
	if (c2 != null) circuit.addAll(c2);
	
	// update the map so all points in the circuit
	// are recorded
	for (point : circuit) {
		circuits.put(point,circuit)
	}
	
	if (circuits.size() == points.size()) {
		// if every point is in a circuit then we might
		// have finished so
		
		// find the unique circuits we've built
		merged = new HashSet(circuits.values())
		if (merged.size() == 1) {
			// if there is just one circuit then we
			// have finished so calculate the answer
			println edge[0][0] * edge[1][0]
			break
		}
	}
}

// simple function for calculating the straight line
// distance between two 3D points.
// Note that it's been memoized so that we can use
// it in the comparator to sort the edges without it
// recalculating multiple times for the same edge
@Memoized
def distance(p1, p2) {
	float distance = 0;
	
	for (int i = 0 ; i < 3 ; ++i) {
		distance += Math.pow(p1[i] - p2[i], 2);
	}
	
	return Math.sqrt(distance)
}
