import groovy.transform.Memoized

input = (new File("input.txt")).readLines()

start = null;
end = null;

width = input[0].length()
height = input.size()

(corrupted,vertices) = loadData();

def loadData() {

	Set corrupted = new HashSet()
	List vertices = new ArrayList()

	for (int y = 0 ; y < input.size() ; ++y) {
		for (int x = 0 ; x < input[y].length() ; ++x) {
			Tuple2 pos = new Tuple2(x,y)
			if (input[y][x].equals("#")) {
				corrupted.add(pos)
			} else if (input[y][x].equals("S")) {
				start = pos
				vertices.add(new Vertex(pos,0));
			} else if (input[y][x].equals("E")) {
				end = pos
				vertices.add(new Vertex(pos));
			} else {
				vertices.add(new Vertex(pos));
			}
		}
	}
	
	return [corrupted,vertices]
}



orig = getFastestTime(vertices,corrupted)
orig.add(0,start)

checked = new HashSet<Tuple4>()

long total = 0;

for (step : orig) {
		Set starts = getPOSNeighbours(step[0],step[1])
		starts.retainAll(corrupted)
		
		for (s : starts) {
			Set ends = getPOSNeighbours(s[0],s[1])
			ends.remove(s)
			ends.removeAll(corrupted)
			
			for (e : ends) {
			
				if (orig.contains(e) && orig.indexOf(e) < orig.indexOf(step)) continue
			
				Tuple4 cheat = new Tuple4(s[0],s[1],e[0],e[1])
				
				if (checked.add(cheat)) {
					int si = orig.indexOf(step)
					int ei = orig.indexOf(e);
					
					int diff = ei-si-2;
					
					if (diff >= 100) ++total
				
				}
			}
			
		}
}

println total

@Memoized
Set getPOSNeighbours(x,y) {
	Set neighbours = new HashSet();
	
	if (x > 0) neighbours.add(new Tuple2(x-1,y))
	if (x < width-1) neighbours.add(new Tuple2(x+1,y))
	if (y > 0) neighbours.add(new Tuple2(x,y-1))
	if (y < height-1) neighbours.add(new Tuple2(x,y+1))
	
	return  neighbours
}

@Memoized
List getFastestTime(vertices, walls) {

	Comparator comparator = new Comparator() {
		public int compare(v1, v2) {
			return v1.value.compareTo(v2.value);
		}
		
	};

	Vertex finish = null

	// this is Dijkstra
	while (vertices.size() > 0) {
		Collections.sort(vertices,comparator);

		Vertex u = vertices.remove(0);

		
		if (u.pos.equals(end)) {
			finish = u
			break;
		}
		
		Set neighbours = getNeighbours(u.pos, walls);
		neighbours.retainAll(vertices)
		
		for (Vertex n : neighbours) {
			n = vertices.get(vertices.indexOf(n));
			value = u.value + 1;
			
			if (value < n.value) {
				n.value = value;
				n.prev = u
			}
		}
	}

	if (finish == null) return null

	List path = []
	
	while (finish.prev != null) {
		path.add(0, finish.pos)		
		finish = finish.prev
	}
	
	return path

}

public Set getNeighbours(Tuple2 pos, walls) {

	Set neighbours = new HashSet() 
	
	if (pos.first < width-1) neighbours.add(new Vertex(new Tuple2(pos.first+1,pos.second)));
	if (pos.first > 0) neighbours.add(new Vertex(new Tuple2(pos.first-1,pos.second)));
	if (pos.second < height-1) neighbours.add(new Vertex(new Tuple2(pos.first,pos.second+1)));
	if (pos.second > 0 ) neighbours.add(new Vertex(new Tuple2(pos.first,pos.second-1)));
	
	Iterator it = neighbours.iterator()
	while (it.hasNext()) {
		Vertex v = it.next();
		if (walls.contains(v.pos)) it.remove()
	}
	
	return neighbours
}

public class Vertex {
	Tuple2 pos;
	Integer value
	
	Vertex prev
	
	public Vertex(Tuple2 pos) {
		this.pos = pos;
		value = Integer.MAX_VALUE;
	}
	
	public Vertex(Tuple2 pos, Integer value) {
		this.pos = pos;
		this.value = value;
	}
	
	public int hashCode() {
		return pos.hashCode();
	}
	
	public boolean equals(v) {
		return pos.equals(v.pos)
	}
	
	public String toString() {
		return pos.toString()+":"+value
	}
}

