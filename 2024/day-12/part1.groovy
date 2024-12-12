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
			
			// putting it all into a set and then into
			// a list is messy here but gives nicer code
			// elsewhere so I'll live with it
			region = new HashSet<Tuple2>()
			flood(region,x,y,map[y][x])
			region = new ArrayList<Tuple2>(region)
			//print map[y][x]+": "+region
			
			used.addAll(region)
			
			long area = region.size();
			long perimeter = region.size()*4;
			while (region.size() > 1) {
				Tuple2 next = region.remove(0);
				for (Tuple2 check : region) {
					if (next[0]==check[0] && Math.abs(next[1]-check[1]) == 1) perimeter = perimeter-2
					if (next[1]==check[1] && Math.abs(next[0]-check[0]) == 1) perimeter = perimeter-2
				}
			}
			
			//println " "+perimeter
			
			total += area*perimeter
		}
	}
}

println total

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
