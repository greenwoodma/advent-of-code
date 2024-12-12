map = (new File("input.txt")).readLines()

width = map[0].length()

def used = new HashSet<Tuple2>()

long total = 0;

for (int x = 0 ; x < map[0].length() ; ++x) {
	for (int y = 0 ; y < map.size() ; ++y) {
		def pos = new Tuple2(x,y)
		
		if (!used.contains(pos)) {
			// This is a plot which we haven't yet visited
			// so flood out from here to find the extent
			// of this region
			region = new HashSet<Tuple2>()
			flood(region,x,y,map[y][x])
			
			used.addAll(region)
			
			total += (region.size()*(countVertical(region)+countHorizontal(region))) 
		}
	}
}

println total

def Integer countVertical(region) {
	long total = 0;

	List<String> previous = new ArrayList<String>()
	for (int y = 0 ; y < map.size() ; ++y) {
		List<String> current = findEdges(getOneDimension(region,1,y))
		
		List<String> keep = new ArrayList<String>(current)
		keep.removeAll(previous);
		
		total += keep.size()
		
		previous = current
	}
	
	return total;
}

def Integer countHorizontal(region) {
	long total = 0;

	List<String> previous = new ArrayList<String>()
	for (int x = 0 ; x < width ; ++x) {
		List<String> current = findEdges(getOneDimension(region,0,x))
		
		List<String> keep = new ArrayList<String>(current)
		keep.removeAll(previous);
		
		total += keep.size()
		
		previous = current
	}
	
	return total;
}

def List<String> findEdges(dimension) {
	List<String> result = new ArrayList<Integer>();

	for (int i = 0 ; i < dimension.size() ; ++i) {
		if (i == 0 || dimension[i-1] != dimension[i]-1) result.add("B"+dimension[i])
		if (i == dimension.size()-1 || dimension[i+1] != dimension[i]+1) result.add("E"+(dimension[i]+1))
	}
	
	return result;
}

def List<Integer> getOneDimension(region, dimension, value) {
	List<Integer> result = new ArrayList<Integer>()

	for (Tuple2 plot : region) {
		if (plot[dimension]==value) {
			result.add(plot[Math.abs(dimension-1)])
		}
	}
	
	Collections.sort(result)

	return result;
}

def void flood(Set<Tuple2> region, int x, int y, String label) {
	if (map[y][x] != label) return

	if (!region.add(new Tuple2(x,y))) return;
	
	// check left
	if (x > 0) flood(region, x-1, y, label)
	
	// check right
	if (x < width-1) flood(region, x+1, y, label)
	
	// check up
	if (y > 0) flood(region, x, y-1, label)
	
	// check down
	if (y < map.size()-1) flood(region, x, y+1, label)
}
