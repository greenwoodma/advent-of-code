//Note this has a bug in it where the rows aren't offset.
//Strangely it doesn't affect finding a correct solution to part 1
//but while the number of black tiles is right their location isn't
//so it doesn't work as input to part 2


Set<String> black = new HashSet<String>();


new File("sample.txt").eachLine {line ->
	long col = 0;
	long row = 0;

	//e, se, sw, w, nw, and ne

	while (!line.equals("")) {
		if (line.startsWith("e")) {
			++col;
			line = line.substring(1);
		}
		else if (line.startsWith("se")) {
			++row;
			line = line.substring(2);
		}
		else if (line.startsWith("sw")) {
			++row;
			--col;
			line = line.substring(2);
		}
		else if (line.startsWith("w")) {
			--col;
			line = line.substring(1);
		}
		else if (line.startsWith("nw")) {
			--row;
			line = line.substring(2);
		}
		else if (line.startsWith("ne")) {
			++col;
			--row;
			line = line.substring(2);
		}
		else {
			System.err.println("invalid: " + line);
		}
	}

	String tile = col+","+row;

	if (black.contains(tile))
		black.remove(tile);
	else
		black.add(tile);
}

System.out.println(black.size());
System.out.println(black);

