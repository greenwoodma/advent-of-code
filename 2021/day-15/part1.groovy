lines = (new File("input.txt")).collect { it.toCharArray().collect { c -> (c as int) - 48 } }

height = lines.size()
width = lines[0].size();

end = new Tuple2(width-1, height-1)

System.out.println(end)

long flashes = 0;


List path = new ArrayList();
path.add(new Tuple2(0,0));

cache = new HashMap()

System.out.println(findPath(path));




public Long findPath(List path) {
	neighbours = getNeighbours(path[-1])
	
	neighbours.removeAll(path);
	
	if (neighbours.isEmpty()) return null;
	
	Long min = null;
	
	for (Tuple2 n : neighbours) {
		if (n.equals(end)) {
			System.out.println("found a path")
			return lines[height-1][width-1]
		}		
		
		lowest = null;
		
		if (cache.containsKey(n)) {
			lowest = cache.get(n);
		} else {
		
		List extend = new ArrayList(path)
		extend.add(n);
		
		
		
		lowest = findPath(extend);
		
		cache.put(n,lowest);
		}
		
		if (lowest != null) {		
			score = lines[n.first][n.second] + lowest;
			
			min = min == null ? score : Math.min(min,score)
		}
	}
	
	return min;
}

public Set getNeighbours(Tuple2 pos) {
//System.out.println(pos)
	Set neighbours = new HashSet()
	/*for (int x = pos.first-1 ; x <= pos.first+1 ; ++x) {
		if (x >= 0 && x < width) {
			for (int y = pos.second-1 ; y <= pos.second+1 ; ++y) {
				if (y >= 0 && y < height) {
					neighbours.add(new Tuple2(x,y));
				}		
			}
		}
	}
	
	neighbours.remove(pos)*/
	
	if (pos.first < width-1) neighbours.add(new Tuple2(pos.first+1,pos.second));
	if (pos.second < height-1) neighbours.add(new Tuple2(pos.first,pos.second+1));
	
	return neighbours
}

