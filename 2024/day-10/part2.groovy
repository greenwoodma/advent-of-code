import groovy.transform.Memoized

map = []

def trailheads = []

new File("input.txt").eachLine { line ->
	
	for (int x = 0 ; x < line.length() ; ++x) {
		if (line[x] == "0") trailheads.add(new Tuple2(x,map.size()))
	}
	
	map.add(line*.toInteger())
	
}

width = map[0].size()

long total = 0

for (trailhead : trailheads) {
	total += peaks(trailhead[0],trailhead[1]).size() 
}

println total

@Memoized
def List<Tuple2> peaks(x, y) {

	List<Tuple2> summits = new ArrayList<Tuple2>()

	int val = map[y][x]
	
	if (val == 9) {
		summits.add(new Tuple2(x,y))
		return summits;
	}
	
	++val
	
	// up
	if (y > 0 && map[y-1][x] == val) summits.addAll(peaks(x,y-1))
	
	// left
	if (x > 0 && map[y][x-1] == val) summits.addAll(peaks(x-1,y))
	
	// down
	if (y < map.size()-1 && map[y+1][x] == val) summits.addAll(peaks(x,y+1))
	
	// right
	if (x < width && map[y][x+1] == val) summits.addAll(peaks(x+1,y))
	
	return summits
}
