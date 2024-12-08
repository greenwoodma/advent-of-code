def map = (new File("input.txt")).readLines()

antennas = new HashMap<String,List<Tuple2>>()

for (int r = 0 ; r < map.size() ; ++r) {
	for (int c = 0 ; c < map[r].length() ; ++c) {
		if (map[r][c] != ".") {
			points = antennas.getOrDefault(map[r][c], new ArrayList<Tuple2>());
			points.add(new Tuple2(c,r))
			antennas.put(map[r][c],points);
		}
	}
}

width = map[0].length()

antinodes = new HashSet<Tuple2>()

for (List<Tuple2> value : antennas.values()) {
	for (int i = 0 ; i < value.size()-1 ; ++i) {
		x1 = value[i][0]
		y1 = value[i][1]
		for (int j = i+1 ; j < value.size() ; ++j) {
			x2 = value[j][0]
			y2 = value[j][1]
			
			dx = x1-x2
			dy = y1-y2
			
			// if dx < 0 then x1 to left of x2 so points are x1+dx and x2-dx
			// if dx > 0 then x1 to the right of x2 so points are x1+dx and x2-dx
			
			
			int count = 0;
			while (true) {
				a1 = new Tuple2(x1+(dx*count),y1+(dy*count))
				if (a1[0]>=0 && a1[0]<width && a1[1]>=0 && a1[1]<map.size())
					antinodes.add(a1);
				else
					break;
					
				++count;
			}
			
			count = 0;
			while (true) {
				a2 = new Tuple2(x2-(dx*count),y2-(dy*count))
				if (a2[0]>=0 && a2[0]<width && a2[1]>=0 && a2[1]<map.size())
					antinodes.add(a2);
				else
					break;
				
				++count
			}
		}
	}
}

// println antinodes
println antinodes.size()
