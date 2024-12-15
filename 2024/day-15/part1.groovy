def map = []

def instructions = "";

int rX, rY
int width

boolean inMap = true;
new File("input.txt").eachLine { line ->
	if (line.equals("")) {
		inMap = false;
	}	
	else if (inMap) {
		if (line.indexOf("@") != -1) {
			rX = line.indexOf("@")
			rY = map.size()
		}
		map.add(line.toCharArray()*.toString());
	}
	else instructions = instructions + line
}

width = map[0].size()

for (int i = 0 ; i < instructions.length() ; ++i) {
	d = instructions[i]
	
	if (d.equals("^")) {
		// move up
		int space = -1;
		for (int y = rY-1 ; y >= 0; --y) {
			if (map[y][rX].equals("#")) break;
			else if (map[y][rX].equals(".")) {
				space = y;
				break;
			}
		}
		
		if (space != -1) {
			//println "moving up"
			for (int y = space ; y < rY ; ++y) {
				map[y][rX] = map[y+1][rX]
			}
			map[rY][rX] = "."
			--rY
			
			//display(map)
		}
		
	} else if (d.equals("v")) {
		// move down
		
		int space = -1;
		for (int y = rY+1 ; y < map.size() ; ++y) {
			if (map[y][rX].equals("#")) break;
			else if (map[y][rX].equals(".")) {
				space = y;
				break;
			}
		}
		
		if (space != -1) {
			//println "move down"
			for (int y = space ; y > rY ; --y) {
				map[y][rX] = map[y-1][rX]
			}
			map[rY][rX] = "."
			++rY
			
			//display(map)
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
			
			//display(map)
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
			
			//display(map)
		}
	}
}

long total = 0;

for (int y = 0 ; y < map.size() ; ++y) {
	for (int x = 0 ; x < map[y].size() ; ++x) {
		if (map[y][x].equals("O")) {
			total += 100*y + x
		}
	}
}

println total

void display(map) {
	println "\n\n"
	for (int y = 0 ; y < map.size() ; ++y) {
		row = map[y];
		
		for (int x = 0 ; x < row.size() ; ++x) {
			print map[y][x]
		}
		
		print "\n"
	}
}
