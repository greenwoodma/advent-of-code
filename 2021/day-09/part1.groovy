lines = (new File("input.txt")).collect { it.toCharArray().collect { c -> (c as int) - 48 } }

long score = 0;

for (int y = 0 ; y < lines.size() ; ++y) {
	for (int x = 0 ; x < lines[y].size() ; ++x) {

		int current = lines[y][x]
		
		boolean lowPoint = getValue(x,y-1) > current;
		lowPoint = lowPoint && getValue(x,y+1) > current;
		lowPoint = lowPoint && getValue(x-1,y) > current;
		lowPoint = lowPoint && getValue(x+1,y) > current;
		
		if (lowPoint) {
			//System.out.println(j+","+i);
			score += (1+current);
		}
	}
}

System.out.println("answer: " + score);

public int getValue(int x, int y) {

	// grr, turns out that if you use a negative index when
	// accessing a list it works backwards from the end rather
	// than throwing an exception, so catch the negatives ones
	// and assume max height
	if (x < 0 || y < 0) return 9;

	try {
		return lines[y][x];
	} catch (Exception) {
		return 9;
	}
}
