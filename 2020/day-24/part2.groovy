//https://www.redblobgames.com/grids/hexagons/

Set<FloorTile> black = new HashSet<FloorTile>();

new File("input.txt").eachLine {line ->
	long col = 0;
	long row = 0;

	//e, se, sw, w, nw, and ne

	System.out.println(line);

	while (!line.equals("")) {
		if (line.startsWith("e")) {
			++col;
			line = line.substring(1);
		}
		else if (line.startsWith("se")) {
			if (row % 2 != 0) ++col
			++row;
			line = line.substring(2);
		}
		else if (line.startsWith("sw")) {
			if (row % 2 == 0) --col
			++row;
			line = line.substring(2);
		}
		else if (line.startsWith("w")) {
			--col;
			line = line.substring(1);
		}
		else if (line.startsWith("nw")) {
			if (row % 2 == 0) --col
			--row;
			line = line.substring(2);
		}
		else if (line.startsWith("ne")) {
			if (row % 2 != 0) ++col
			--row;
			line = line.substring(2);
		}
		else {
			System.err.println("invalid: " + line);
		}

		System.out.println("\t"+col+","+row);
	}



	tile = new FloorTile(col,row);

	System.out.println(tile);

	if (black.contains(tile))
		black.remove(tile);
	else
		black.add(tile);
}

System.out.println(black);
System.out.println(black.size());

for (int i = 0 ; i < 100 ; ++i) {

	white = new HashSet<FloorTile>();

	nextDay = new HashSet<FloorTile>();

	for (FloorTile b in black) {
		adjacent = b.getAdjacent();

		white.addAll(adjacent);

		adjacent.retainAll(black);

		if (adjacent.size() == 1 || adjacent.size() == 2)
			nextDay.add(b);
	}

	white.removeAll(black);

	for (FloorTile w in white) {
		adjacent = w.getAdjacent();

		adjacent.retainAll(black);

		if (adjacent.size() == 2)
			nextDay.add(w);
	}

	

	System.out.println("Day "+(i+1)+": "+nextDay.size());

	black = nextDay;
}

class FloorTile {
	long col;
	long row;

	public FloorTile(long col, long row) {
		this.col = col;
		this.row = row;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (col ^ (col >>> 32));
		result = prime * result + (int) (row ^ (row >>> 32));
		return result;
	}

	public boolean equals(Object obj) {
		FloorTile other = (FloorTile) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	public String toString() {
		return col+","+row;
	}

	public Set<FloorTile> getAdjacent() {
		Set<FloorTile> adjacent = new HashSet<FloorTile>();

		// east
		adjacent.add(new FloorTile(col+1,row));

		// west
		adjacent.add(new FloorTile(col-1,row));

		// north west
		adjacent.add(new FloorTile(col - (row % 2 == 0 ? 1 : 0), row-1));

		// north east
		adjacent.add(new FloorTile(col + (row % 2 != 0 ? 1 : 0), row-1));

		// south west
		adjacent.add(new FloorTile(col - (row % 2 == 0 ? 1 : 0), row+1));

		// south east
		adjacent.add(new FloorTile(col + (row % 2 != 0 ? 1 : 0), row+1));

		return adjacent;
	}
}


