dots = new HashSet();
instructions = new ArrayList();

boolean anInstruction = false;
new File("input.txt").each { line ->

	if (line.equals("")) {
		anInstruction = true;
	}
	else if (anInstruction) {
		boolean up = line.indexOf("y=") != -1;
		long foldLine = Long.valueOf(line.split("=")[1]);
		
		instructions.add(new Tuple2(up,foldLine));
	}
	else {
		(x, y) = line.split(",")
		
		dots.add(new Tuple2(Long.valueOf(x), Long.valueOf(y)))
	}
}

for (int s = 1 ; s <= instructions.size() ; ++s) {
	instruction = instructions[s-1];
	
	dots = instruction.first ? foldUp(instruction.second) : foldLeft(instruction.second)
	
	//System.out.println(dots.size());
}

int width = 0;
int height = 0;

dots.each { dot ->
	width = Math.max(width,dot.first);
	height = Math.max(height,dot.second);
}

char[][] display = new char[height+1][width+1];

for (int x = 0 ; x < display.length ; ++x) {
	for (int y = 0 ; y < display[x].length ; ++y) {
		display[x][y]=' ';
	}
}

dots.each { dot ->
	display[(int)dot.second][(int)dot.first] = "#"
}

for (int x = 0 ; x < display.length ; ++x) {
	System.out.println(display[x]);
}

public Set foldUp(long foldLine) {

	dotsNP1 = new HashSet();
	
	dots.each{ dot ->
		if (dot.second < foldLine) {
			dotsNP1.add(dot);
		} else if (dot.second > foldLine) {
			dotsNP1.add(new Tuple2(dot.first, foldLine-(dot.second-foldLine)));
		}
	}

	return dotsNP1;
}

public Set foldLeft(long foldLine) {

	dotsNP1 = new HashSet();
	
	dots.each{ dot ->
		if (dot.first < foldLine) {
			dotsNP1.add(dot);
		} else if (dot.first > foldLine) {
			dotsNP1.add(new Tuple2(foldLine-(dot.first-foldLine),dot.second));
		}
	}

	return dotsNP1;
}
