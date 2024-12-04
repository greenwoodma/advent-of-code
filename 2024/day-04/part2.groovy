
def puzzle = (new File("input.txt")).readLines()

long total = 0;

for (int r = 1 ; r < puzzle.size()-1 ; ++r) {
	String row = puzzle[r];
	for (int c = 1 ; c < row.length()-1 ; ++c) {
		if (row[c] == 'A') {
		
			boolean lr = (puzzle[r-1][c-1] == "M" && puzzle[r+1][c+1] == "S") || (puzzle[r-1][c-1] == "S" && puzzle[r+1][c+1] == "M")
			
			boolean rl = (puzzle[r-1][c+1] == "M" && puzzle[r+1][c-1] == "S") || (puzzle[r-1][c+1] == "S" && puzzle[r+1][c-1] == "M")
			
			if (lr && rl) ++total
		
		}
	}
}

println total
