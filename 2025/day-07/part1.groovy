rows = (new File("input.txt")).readLines();

beams = new HashSet();
beams.add(rows.remove(0).indexOf("S"))

println beams

int width = rows[0].length();
long splits = 0;

for (row : rows) {
	next = new HashSet();
	
	for (beam in beams) {
		if (row[beam].equals("^")) {
			++splits;
			next.add(beam-1)
			next.add(beam+1)
		} else {
			next.add(beam)
		}
	}
	
	beams = next;
}

println splits
