lines = (new File("input.txt")).collect { it.toCharArray().collect { c -> (c as int) - 48 } }

long score = 0;

List<Set<Tuple2>> basins = new ArrayList<Set<Tuple2>>();

for (int y = 0 ; y < lines.size() ; ++y) {
	for (int x = 0 ; x < lines[y].size() ; ++x) {

		int current = lines[y][x]
		
		boolean lowPoint = getValue(x,y-1) > current;
		lowPoint = lowPoint && getValue(x,y+1) > current;
		lowPoint = lowPoint && getValue(x-1,y) > current;
		lowPoint = lowPoint && getValue(x+1,y) > current;
		
		if (lowPoint) {
			Set<Tuple2> baisin = new HashSet<Tuple2>();
			follow(baisin,new Tuple2(x,y));
			
			basins.add(baisin);			
		}
	}
}

public void follow(Set<Tuple2> baisin, Tuple2 pos) {
	baisin.add(pos);
	
	int current = getValue(pos.first,pos.second);
	isValid(baisin, current ,new Tuple2(pos.first, pos.second-1));
	isValid(baisin, current, new Tuple2(pos.first, pos.second+1));
	isValid(baisin, current, new Tuple2(pos.first-1, pos.second));
	isValid(baisin, current, new Tuple2(pos.first+1, pos.second));
}

public void isValid(Set<Tuple2> baisin, int current, Tuple2 pos) {
	int value = getValue(pos.first,pos.second);
	
	if (value > current && value != 9) {
		follow(baisin, pos);
	}
}

lengths = basins.collect { it.size() }.sort()


System.out.println("answer: " + (lengths[-1]*lengths[-2]*lengths[-3]));

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
