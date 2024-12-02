List rows = new ArrayList();

new File("input.txt").eachLine {line ->
    rows.add("."+line+".");
}



rows.add(0,"."*rows.get(0).length());

rows.add("."*rows.get(0).length());

System.out.println(rows);

while(true) {
	List updated = updateSeats(rows);

	display(rows);

	if (updated.equals(rows)) break;

	rows = updated;
}

int occupied = 0;
for (int i = 1 ; i < rows.size()-1 ; ++i) {
	String row = rows.get(i);

	for (int j = 1 ; j < row.length()-1 ; ++j) {
		if (row.charAt(j) == '#') ++occupied;
	}
}

System.out.println(occupied);

private void display(rows) {
	System.out.println("\n\n");

	for (String row : rows) {
		System.out.println(row);
	}
}

private List updateSeats(List rows) {
	List newRows = new ArrayList();

	newRows.add(rows.get(0));

	for (int i = 1 ; i < rows.size()-1 ; ++i) {
		String row = rows.get(i);
		String newRow = "";

		for (int j = 1 ; j < row.length()-1 ; ++j) {
			char seat = row.charAt(j);

			int occupied = getOccupied(rows,i,j);

			if (seat == 'L' && occupied == 0) {
				seat = '#';
			}
			else if (seat == '#' && occupied >= 5) {
				seat = 'L';
			}

			newRow = newRow + seat;

		}

		newRows.add("."+newRow+".");	
	}

	newRows.add(rows.get(rows.size()-1));
	
	return newRows;
}

private boolean isOccupied(List rows, int row, int column) {
	return rows.get(row).charAt(column) == '#';
}

//priavte boolean isSeat(List rows, int row, int column) {
//	return rows.get(row).charAt(column) != '.';
//}

private int getOccupied(List rows, int row, int column) {
	int count = 0;

	if (findSeat(rows,row,column,-1,-1) == '#') ++count;
	if (findSeat(rows,row,column,-1,0) == '#') ++count;
	if (findSeat(rows,row,column,-1,1) == '#') ++count;

	if (findSeat(rows,row,column,0,-1) == '#') ++count;
	if (findSeat(rows,row,column,0,1) == '#') ++count;

	if (findSeat(rows,row,column,1,-1) == '#') ++count;
	if (findSeat(rows,row,column,1,0) == '#') ++count;
	if (findSeat(rows,row,column,1,1) == '#') ++count;

	return count;
}

private char findSeat(List rows, int row, int column, int rChange, int cChange) {
	while (true) {
		row = row + rChange;
		column = column + cChange;
		try {
			char seat = rows.get(row).charAt(column);

			if (seat != '.') return seat;
		}
		catch (Exception e) {
			return '.';
		}
	}
}
