

def input = (new File("input.txt")).readLines()

steps = 1024
height = 71
width = 71

corrupted = new HashSet<Tuple2>()
for (int s = 0 ; s < steps ; ++s) {
	(x,y) = input[s].split(",")*.toInteger()
	corrupted.add(new Tuple2(x,y))
}

start = new Tuple2(0,0);
end = new Tuple2(width-1, height-1)


// this is slow but fast enough not to drive me nuts
// what we should do is first find the initial shortest path
// and then get the actual path. We can then look for new bytes
// that would hit the path. We then recheck at that point rather
// than all the ones where it wouldn't change. And then do the
// same again until we can't find a path
for (int i = steps+1 ; i < input.size() ; ++i) {
	(x,y) = input[i].split(",")*.toInteger()
	corrupted.add(new Tuple(x,y))
	
	if (findPath() < 0) {
		println input[i]
		break;
	}
}

int findPath() {

	Comparator comparator = new Comparator() {
		public int compare(v1, v2) {
			return v1.value.compareTo(v2.value);
		}
		
	};

	vertices = new ArrayList();

	for (int y = 0 ; y < height ; ++y) {
		for (int x = 0 ; x < width ; ++x) {
			pos = new Tuple2(x,y)
			if (!corrupted.contains(pos))
				vertices.add(new Vertex(pos));
		}
		
	}

	// this is Dijkstra
	while (vertices.size() > 0) {

		Collections.sort(vertices,comparator);

		Vertex u = vertices.remove(0);

		
		if (u.pos.equals(end)) {
			return u.value
		}
		
		Set neighbours = getNeighbours(u.pos);
		neighbours.retainAll(vertices)
		
		for (Vertex n : neighbours) {
			n = vertices.get(vertices.indexOf(n));
			value = u.value + 1;
			
			if (value < n.value) {
				n.value = value;
			}
		}
	}
	
	return -1
}


public Set getNeighbours(Tuple2 pos) {

	Set neighbours = new HashSet() 
	
	if (pos.first < width-1) neighbours.add(new Vertex(new Tuple2(pos.first+1,pos.second)));
	if (pos.first > 0) neighbours.add(new Vertex(new Tuple2(pos.first-1,pos.second)));
	if (pos.second < height-1) neighbours.add(new Vertex(new Tuple2(pos.first,pos.second+1)));
	if (pos.second > 0 ) neighbours.add(new Vertex(new Tuple2(pos.first,pos.second-1)));
	
	Iterator it = neighbours.iterator()
	while (it.hasNext()) {
		Vertex v = it.next();
		if (corrupted.contains(v.pos)) it.remove()
	}
	
	return neighbours
}

public class Vertex {
	Tuple2 pos;
	Integer value
	static Tuple2 start = new Tuple2(0,0);
	
	public Vertex(Tuple2 pos) {
		this.pos = pos;
		
		if (pos.equals(start)) {
			value = 0;
		} else {
			value = Integer.MAX_VALUE;
		}
	}
	
	public Vertex(Tuple2 pos, Integer value) {
		this.pos = pos;
		
		if (pos.equals(start)) {
			this.value = 0;
		} else {
			this.value = value;
		}
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

