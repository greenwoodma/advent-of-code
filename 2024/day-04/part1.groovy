
def puzzle = (new File("input.txt")).readLines()

long total = 0;

for (int r = 0 ; r < puzzle.size() ; ++r) {
	String row = puzzle[r];
	for (int c = 0 ; c < row.length() ; ++c) {
		if (row[c] == 'X') {
			if (row.substring(c).startsWith("XMAS")) {
				// forwards
				++total
			}
			
			if (c >= 3 && row.substring(c-3).startsWith("SAMX")) {
				// backwards
				++total
			}
			
			if (r <= puzzle.size()-4) {
				// downwards
				if (puzzle[r+1][c] == 'M' && puzzle[r+2][c] == 'A' && puzzle[r+3][c] == "S")
					++total
					
				// down left
				if (c >= 3 && puzzle[r+1][c-1] == 'M' && puzzle[r+2][c-2] == 'A' && puzzle[r+3][c-3] == "S")
					++total
				
				// down right
				if (c <= row.length()-4  && puzzle[r+1][c+1] == 'M' && puzzle[r+2][c+2] == 'A' && puzzle[r+3][c+3] == "S")
					++total
			}
			
			if (r >= 3) {
				// upwards
				if (puzzle[r-1][c] == 'M' && puzzle[r-2][c] == 'A' && puzzle[r-3][c] == "S")
					++total
					
				// up left
				if (c >= 3 && puzzle[r-1][c-1] == 'M' && puzzle[r-2][c-2] == 'A' && puzzle[r-3][c-3] == "S")
					++total
					
				// up right
				if (c <= row.length()-4 && puzzle[r-1][c+1] == 'M' && puzzle[r-2][c+2] == 'A' && puzzle[r-3][c+3] == "S")
					++total
			}
		}
	}
}

println total
