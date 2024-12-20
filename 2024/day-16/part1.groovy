
def input = (new File("input.txt")).readLines()

walls = new HashSet<Tuple2>()

List vertices = new ArrayList();

end = null


for (int y = 0 ; y < input.size() ; ++y) {
	for (int x = 0 ; x < input[y].size() ; ++x) {
		symbol = input[y][x]
		
		if (symbol.equals("#")) {
			walls.add(new Tuple3(x,y,0))
			walls.add(new Tuple3(x,y,1))
		} else if (symbol.equals(".")) {
			vertices.add(new Vertex(new Tuple3(x,y,0)))
			vertices.add(new Vertex(new Tuple3(x,y,1)))
		} else if (symbol.equals("S")) {
			vertices.add(new Vertex(new Tuple3(x,y,0),0))
			vertices.add(new Vertex(new Tuple3(x,y,1)))
		} else if (symbol.equals("E")) {
			vertices.add(new Vertex(new Tuple3(x,y,0)))
			vertices.add(new Vertex(new Tuple3(x,y,1)))
			end = [
				new Tuple3(x,y,0),
				new Tuple3(x,y,1)
			]
		}
	}	
}

width = input[0].length()
height = input.size()


Comparator comparator = new Comparator() {
	public int compare(v1, v2) {
		return v1.value.compareTo(v2.value);
	}
	
};

// this is Dijkstra
while (vertices.size() > 0) {
	Collections.sort(vertices,comparator);

	Vertex u = vertices.remove(0);

	//println u

	
	if (end.contains(u.pos)) {
		System.out.println(u.value);
		//System.exit(0);
	}
	
	Set neighbours = getNeighbours(u.pos);
	
	//println neighbours
	
	
	
	neighbours.retainAll(vertices)
	
	//println neighbours
	
	for (Vertex n : neighbours) {
		n = vertices.get(vertices.indexOf(n));
		value = u.value + (u.pos[2] == n.pos[2] ? 1 : 1000);
		
		if (value < n.value) {
			n.value = value;
		}
		
		//println n
	}
	
	//if (true) System.exit(0)
}

public Set getNeighbours(Tuple3 pos) {

	Set neighbours = new HashSet() 
	
	if (pos[2] == 0) {
		// this is horizontal so
		if (pos.first < width-1) neighbours.add(new Vertex(new Tuple3(pos.first+1,pos.second,0)));
		if (pos.first > 0) neighbours.add(new Vertex(new Tuple3(pos.first-1,pos.second,0)));
		neighbours.add(new Vertex(new Tuple3(pos.first,pos.second,1)))
	} else {
		if (pos.second < height-1) neighbours.add(new Vertex(new Tuple3(pos.first,pos.second+1,1)));
		if (pos.second > 0 ) neighbours.add(new Vertex(new Tuple3(pos.first,pos.second-1,1)));
		neighbours.add(new Vertex(new Tuple3(pos.first,pos.second,0)))
	}
	
	Iterator it = neighbours.iterator()
	while (it.hasNext()) {
		Vertex v = it.next();
		if (walls.contains(v.pos)) it.remove()
	}
	
	return neighbours
}

public class Vertex {
	Tuple3 pos;
	Integer value = Integer.MAX_VALUE
	
	public Vertex(Tuple3 pos) {
		this.pos = pos;
		value = Integer.MAX_VALUE;
	}
	
	public Vertex(Tuple3 pos, Integer value) {
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

