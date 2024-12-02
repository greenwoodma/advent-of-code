lines = (new File("input.txt")).collect { it.toCharArray().collect { c -> (c as int) - 48 } }

Map grid = new HashMap();

height = lines.size()
width = lines[0].size();

long flashes = 0;

for (int y = 0 ; y < height ; ++y) {
	for (int x = 0 ; x < width ; ++x) {
		int value = lines[y][x];
		
		Set positions = grid.getOrDefault(value, new HashSet());
		
		positions.add(new Tuple2(x,y));
		
		grid.put(value, positions)
	}
}

//System.out.println(grid)
long s = 0;
while(true){
//for (int s = 1 ; s <= 200 ; ++s) {
	++s;
	Map next = new HashMap();
	
	List above9 = new ArrayList();
	
	for (Map.Entry e : grid.entrySet()) {
		if (e.getKey() == 9) {
			above9.addAll(e.getValue());
		}
		else {
			next.put(e.getKey()+1,e.getValue())
		}
	}

	//System.out.println(above9);

	for (int i = 0 ; i < above9.size() ; ++i) {
		Tuple2 pos = above9[i];
		
		Set neighbours = getNeighbours(pos);
		
		//System.out.println("\t"+neighbours);
		
		neighbours.removeAll(above9);
		
		//System.out.println("\t"+neighbours);

		for (Tuple2 adj : neighbours) {
			int e = getEnergy(next,adj);
			++e
			
			if (e > 9) {
				above9.add(adj);
			}
			else {
				Set positions = next.getOrDefault(e,new HashSet());
				positions.add(adj);
				next.put(e,positions);
			}
		}
	}
	
	if (above9.size() == 100) {
	
	
	break;
	}
	
	flashes += above9.size();
	
	next.put(0,new HashSet(above9));
	
	//System.out.println(next)
	grid = next;
}

System.out.println(s);

public int getEnergy(Map data, Tuple2 pos) {
	for (Map.Entry e : data.entrySet()) {
		if (e.getValue().remove(pos))
			return e.getKey();	
	}
	
	return -1;
}


public Set getNeighbours(Tuple2 pos) {
	Set neighbours = new HashSet()
	for (int x = pos.first-1 ; x <= pos.first+1 ; ++x) {
		if (x >= 0 && x < width) {
			for (int y = pos.second-1 ; y <= pos.second+1 ; ++y) {
				if (y >= 0 && y < height) {
					neighbours.add(new Tuple2(x,y));
				}		
			}
		}
	}
	
	neighbours.remove(pos)
	
	return neighbours
}
