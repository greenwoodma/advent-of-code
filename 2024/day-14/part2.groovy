
width = 101
height = 103

seconds = 100000

for (int s = 0 ; s < seconds ; ++s) {

	def map = new HashMap<Integer, Set<Integer>>()

	// okay this is exceptionally lazy, but it was fast enough
	// that I couldn't be bothered changing the code from part 1
	// to just read it in once
	new File("input.txt").eachLine { line ->
		(x,y,dx,dy) = line.split("[^0-9-]+").tail()*.toLong()
		
		X = Math.floorMod(x + dx*s,width)
		Y = Math.floorMod(y + dy*s,height)
		
		row = map.getOrDefault(Y,new HashSet<Integer>());
		row.add(X);
		map.put(Y,row)
	}
	
	boolean candidate = false;
	
	// not knowing exactly what I'm looking for I assumed that
	// a christmas tree would probably have a run of at least 10
	// points in a row at the bottom (I assume the trunk although
	// that didn't turn out to be the case).
	for (Set<Integer> row : map.values()) {
		if (row.size() >= 10) {
			sorted = new ArrayList<Integer>(row)
			Collections.sort(sorted)
			for (int c = 0 ; c < sorted.size()-10 ; ++c) {
				if (sorted[c]+10 == sorted[c+10]) {
					candidate = true;
					println(sorted)
					break;
					
				}
			}
			
			if (candidate) break;
		}
		
	}
	
	if (candidate)
		display(map,s)
}



def void display(map,seconds) {

	println "--------------"+seconds+"-----------------"

	for (int y = 0 ; y < height ; ++y) {
		def row = map.getOrDefault(y, new HashSet<Integer>())
		
		for (int x = 0 ; x < width; ++x) {
			if (row.contains(x)) print "#"
			else print "."
		}
		
		println ""
	}

}

