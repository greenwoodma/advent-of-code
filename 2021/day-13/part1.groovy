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

for (int s = 1 ; s <= 1 ; ++s) {
	instruction = instructions[s-1];
	
	dots = instruction.first ? foldUp(instruction.second) : foldLeft(instruction.second)
	
	System.out.println(dots.size());
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
