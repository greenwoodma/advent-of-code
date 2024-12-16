import groovy.transform.Memoized

def map = []

def instructions = "";
int rX, ry
int width

previous = ""

boolean inMap = true;
new File("input.txt").eachLine { line ->
	if (line.equals("")) {
		inMap = false;
	}	
	else if (inMap) {
		String doubled = "";
		
		for (cell : line.toCharArray()*.toString()) {
			if (cell.equals("@"))
				doubled = doubled + "@."
			else if (cell.equals("O"))
				doubled = doubled + "[]"
			else
				doubled = doubled + cell + cell
		}
		
		if (doubled.indexOf("@") != -1) {
			rX = doubled.indexOf("@")
			rY = map.size()
		}
		map.add(doubled.toCharArray()*.toString());
	}
	
	else instructions = instructions + line
}

width = map[0].size()

display(map)

for (int i = 0 ; i < instructions.length() ; ++i) {
	def d = instructions[i]
	
	if (d.equals("^")) {
	
		// move up
		def moves = checkUp(map,rX,rY)
		
		if (moves != null) {
			//println moves
			up = new ArrayList<Tuple3>(moves)
			moves = mergeUp(moves)
			//println moves
			
			for (Tuple3 move : moves) {
				if (map[move[1]][move[0]].equals(".")) continue;
				for (int y = move[2] ; y < move[1] ; ++y) {
					map[y][move[0]] = map[y+1][move[0]]
				}
				map[move[1]][move[0]] = "."
			}
			
			//println "moving up"
			display(map)
			
			--rY
		}
		
	} else if (d.equals("v")) {
		// move down
		
		def moves = checkDown(map,rX,rY)
		
		if (moves != null) {
			//println moves
			moves = mergeDown(moves)
			//println moves
			for (Tuple3 move : moves) {
				if (map[move[1]][move[0]].equals(".")) continue;
				for (int y = move[2] ; y > move[1] ; --y) {
					map[y][move[0]] = map[y-1][move[0]]
				}
				map[move[1]][move[0]] = "."
			}
			
			++rY
			
			//println "moving down"
			display(map)
		}
		
	} else if (d.equals("<")) {
		// move left
		
		int space = -1;
		for (int x = rX-1 ; x >= 0; --x) {
			if (map[rY][x].equals("#")) break;
			else if (map[rY][x].equals(".")) {
				space = x;
				break;
			}
		}
		
		if (space != -1) {
			//println "moving left"
			for (int x = space ; x < rX ; ++x) {
				map[rY][x] = map[rY][x+1]
			}
			map[rY][rX] = "."
			--rX
			
			//println "moving left"
			display(map)
		}
		
	} else if (d.equals(">")) {
		// move right
		int space = -1
		for (int x = rX+1 ; x < width ; ++x) {
			if (map[rY][x].equals("#")) break;
			else if (map[rY][x].equals(".")) {
				space = x;
				break;
			}
		}
		
		if (space != -1) {
			//println "move right"
			for (int x = space ; x > rX ; --x) {
				map[rY][x] = map[rY][x-1]
			}
			map[rY][rX] = "."
			++rX
			
			//println "moving right"
			display(map)
		}
	}
}

display(map)

long total = 0;

for (int y = 0 ; y < map.size() ; ++y) {
	for (int x = 0 ; x < map[y].size() ; ++x) {
		if (map[y][x].equals("[")) {
			total += 100*y + x
		}
	}
}

println total

void display(map) {

	String output = "";

	for (int y = 0 ; y < map.size() ; ++y) {
		row = map[y];
		
		for (int x = 0 ; x < row.size() ; ++x) {
			output = output + map[y][x]
		}
		
		output = output + "\n"
	}
	
	if (output.indexOf("[.") != -1 || output.indexOf(".]") != -1 ||
	    output.indexOf("[#") != -1 || output.indexOf("#]") != -1 ||
	    output.indexOf("[@") != -1 || output.indexOf("@]") != -1) {

		println previous +"\n\n" + output + "\n\n"
		System.exit(0)
	}
	
	previous = output
}

List<Tuple3> mergeUp(List<Tuple3> allMoves) {
	List<Tuple3> moves = new ArrayList<Tuple3>();
	
	Set<Tuple2> seen = new HashSet<Tuple2>()
	
	Collections.sort(allMoves, new Comparator<Tuple3>() {
		public int compare(o1, o2) {
			return o2[1] - o1[1]
		}
	})
	
	for (Tuple3 move : allMoves) {
		Tuple2 c = new Tuple2(move[0],move[2]);
		
		//println seen
		if (seen.add(c)) {
			//println "adding " + move
			moves.add(move);
		}
		
	}
	
	return moves
}

List<Tuple3> mergeDown(List<Tuple3> allMoves) {
	List<Tuple3> moves = new ArrayList<Tuple3>();
	
	Set<Tuple2> seen = new HashSet<Tuple2>()
	
	Collections.sort(allMoves, new Comparator<Tuple3>() {
		public int compare(o1, o2) {
			return o1[1] - o2[1]
		}
	})
	
	for (Tuple3 move : allMoves) {
		Tuple2 c = new Tuple2(move[0],move[2]);
		
		//println seen
		if (seen.add(c)) {
			//println "adding " + move
			moves.add(move);
		}
		
	}
	
	return moves
}

@Memoized
List<Tuple3> checkUp(map, X, Y) {
	List<Tuple3> result = new ArrayList<Tuple3>()

	int space = -1;
	for (int y = Y-1 ; y >= 0; --y) {
		if (map[y][X].equals("#")) break;
		else if (map[y][X].equals(".")) {
			space = y;
			break;
		}
		else if (map[y][X].equals("[")) {
			List<Tuple3> adjacent = checkUp(map, X+1,y)
			if (adjacent == null) break;
			result.addAll(adjacent)
		}
		else if (map[y][X].equals("]")) {
			List<Tuple3> adjacent = checkUp(map, X-1,y)
			if (adjacent == null) break;
			result.addAll(adjacent)
		}
	}
	
	if (space == -1) return null;
	
	result.add(new Tuple3(X,Y,space))
	
	return result;
}

@Memoized
List<Tuple3> checkDown(map, X, Y) {
	List<Tuple3> result = new ArrayList<Tuple3>()

	int space = -1;
	for (int y = Y+1 ; y < map.size(); ++y) {
		if (map[y][X].equals("#")) break;
		else if (map[y][X].equals(".")) {
			space = y;
			break;
		}
		else if (map[y][X].equals("[")) {
			List<Tuple3> adjacent = checkDown(map, X+1,y)
			if (adjacent == null) break;
			result.addAll(adjacent)
		}
		else if (map[y][X].equals("]")) {
			List<Tuple3> adjacent = checkDown(map, X-1,y)
			if (adjacent == null) break;
			result.addAll(adjacent)
		}
	}
	
	if (space == -1) return null;
	
	result.add(new Tuple3(X,Y,space))
	
	return result;
}
