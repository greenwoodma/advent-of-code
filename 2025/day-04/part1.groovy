List<String> map = (new File("input.txt")).readLines();

int reachable = 0

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
			
			// check the cells the row above
			if (r > 0) {
				String above = map.get(r-1);
				if (c > 0 && above[c-1] == '@') ++surrounding
				if (above[c] == '@') ++ surrounding
				if (c < row.length()-1 && above[c+1] == '@') ++surrounding
			}
			
			// check the cells the row below
			if (r < map.size()-1) {
				String above = map.get(r+1);
				if (c > 0 && above[c-1] == '@') ++surrounding
				if (above[c] == '@') ++ surrounding
				if (c < row.length()-1 && above[c+1] == '@') ++surrounding
			}
			
			if (surrounding < 4) {
				// if less than four surrounding rolls then it is reachable
				++reachable
			}
		}
	}
}

println reachable
