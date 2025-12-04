List<String> map = (new File("input.txt")).readLines();

int removed = 0;

while (true) {
	// process the current map
	(reachable, map) = process(map)
	
	// if none were reachable then we have finished
	if (reachable == 0) break;
	
	// otherwise update the total we have removed
	// and loop back around to process the updated map
	removed += reachable;
}

println removed

def process(List map) {

	int reachable = 0
	
	// this is a copy of the map that we are
	updated = new ArrayList(map);

	for (int r = 0 ; r < map.size() ; ++r) {
		String row = map.get(r);

		for (int c = 0 ; c < row.length() ; ++c) {
		
			// for each cell of the map
			
			if (row[c] == '@') {
				// if it's a roll of paper
			
				int surrounding = 0;
				
				// check the cells left and right of it
				if (c > 0 && row[c-1] == '@') ++surrounding;
				if (c < row.length()-1 && row[c+1] == '@') ++surrounding;
				
				// check the row above
				if (r > 0) {
					String above = map.get(r-1);
					if (c > 0 && above[c-1] == '@') ++surrounding
					if (above[c] == '@') ++ surrounding
					if (c < row.length()-1 && above[c+1] == '@') ++surrounding
				}
				
				// check the row below
				if (r < map.size()-1) {
					String above = map.get(r+1);
					if (c > 0 && above[c-1] == '@') ++surrounding
					if (above[c] == '@') ++ surrounding
					if (c < row.length()-1 && above[c+1] == '@') ++surrounding
				}
				
				if (surrounding < 4) {
					// this roll is reachable so....
				
					// record that it's reachable
					++reachable
					
					// and then update the map to remove it
					// probably should just store the rows as a char array to start with
					// as this looks horrible
					char[] cells = updated.get(r).toCharArray();
					cells[c] = 'x'
					updated.set(r, new String(cells))
				}
			}
		}
	}

	// return how many were reachable and the updated map
	return [reachable, updated]
}

def printMap(List map) {
	println "\n\n"
	for (String row : map) {
		println row
	}
}
