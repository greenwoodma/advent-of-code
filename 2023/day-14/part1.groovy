long total = 0;

def rows = (new File("input.txt")).readLines()

def columns = rotate(rows);
//System.out.println(columns);

for (String column : columns) {
	def rocks = column.toCharArray()*.toString();
	
	int empty = 0;
	//System.out.println(rocks);
	for (int r = 0 ; r < rocks.size() ; ++r) {
		if (rocks[r].equals("#")) empty = r+1;
		
		if (rocks[r].equals("O")) {
			if (r != empty) {
				rocks[empty] = "O";
				rocks[r] = ".";
			}
			empty = empty+1;
		}
	}
	
	for (int i = 0 ; i < rocks.size() ; ++i) {
		if (rocks[i].equals("O")) total += rocks.size()-i;
	}
	
	//System.out.println(rocks);
}

System.out.println(total);

public List rotate(def rows) {
	def columns = [""] * rows[0].size();
	
	for (int r = 0 ; r < rows.size() ; ++r) {
		for (int i = 0 ; i < rows[r].length() ; ++i) {
			columns[i] += rows[r].charAt(i);
		}
	}
	
	return columns;
}

