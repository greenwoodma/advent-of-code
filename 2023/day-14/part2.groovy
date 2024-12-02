import groovy.transform.Memoized

long total = 0;

List rows = (new File("input.txt")).readLines();

for (int r = 0 ; r < rows.size() ; ++r) {
	rows[r] = rows[r].toCharArray()*.toString();
}

Map cache = new HashMap();
List seq = [];

long c = 0;

for (c = 0 ; c < 1000000000 ; ++c) {
	rows = cycle(rows);
	
	String desc = display(rows);
	
	if (cache.containsKey(desc)) {

		c= cache.get(desc);
		break;
	}
	
	cache.put(desc,c);

	
	total = 0;
	
	
	for (int r = 0 ; r < rows[0].size() ; ++r) {
		for (int col = 0; col < rows.size() ; ++col) {
			if (rows[r][col].equals("O")) total += rows.size()-r;
		}
	}
	
	seq.add(total);
}

System.out.println(c);
System.out.println(seq);
System.out.println(seq.size());

Long left = 1000000000 - (c+1);

Long repeat = seq.size()-c;

Long offset = left % repeat;

System.out.println(seq[offset+c]);


def cycle(def mirror) {
	mirror = tiltNorth(mirror);
	mirror = tiltWest(mirror);
	mirror = tiltSouth(mirror);
	mirror = tiltEast(mirror);
	
	return mirror;
}



def tiltWest(def columns) {
	for (def rocks : columns) {	
		
		int empty = 0;
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
	}
	
	return columns;
}


def tiltEast(def columns) {
	for (def rocks : columns) {	
		
		int empty = rocks.size()-1;
		for (int r = rocks.size()-1 ; r >= 0 ; --r) {
			if (rocks[r].equals("#")) empty = r-1;
			
			if (rocks[r].equals("O")) {
				if (r != empty) {
					rocks[empty] = "O";
					rocks[r] = ".";
				}
				empty = empty-1;
			}
		}
	}
	
	return columns;
}


def tiltNorth(def columns) {

	for (int c = 0 ; c < columns[0].size() ; ++c) {
	
		int empty = 0;
	
		for (int r = 0 ; r < columns.size() ; ++r) {
			if (columns[r][c].equals("#")) empty = r+1;
			
			if (columns[r][c].equals("O")) {
				if (r != empty) {
					columns[empty][c] = "O";
					columns[r][c] = ".";
				}
				
				empty = empty+1;
			} 
		}
	}
	
	return columns;
}


def tiltSouth(def columns) {

	for (int c = 0 ; c < columns[0].size() ; ++c) {
	
		int empty = columns.size()-1;
	
		for (int r = columns.size()-1 ; r >= 0 ; --r) {
			if (columns[r][c].equals("#")) empty = r-1;
			
			if (columns[r][c].equals("O")) {
				if (r != empty) {
					columns[empty][c] = "O";
					columns[r][c] = ".";
				}
				
				empty = empty-1;
			} 
		}
	}
	
	return columns;
}


public String display(rows) {
	StringBuilder result = new StringBuilder();

	rows.forEach{row ->
		result.append(row.join("")).append("\n");
	}
	
	return result.toString();
}
