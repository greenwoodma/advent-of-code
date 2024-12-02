def listing = []

new File("input.txt").eachLine {line ->
    listing << line
}

System.out.println(listing);

int line = 0;

Set linesRun = new HashSet();

int global = 0;

while (!linesRun.contains(line)) {
	linesRun.add(line);

	String[] parts = listing.get(line).split("\\s+");

	if (parts[0].equals("nop")) {
		++line;
	}
	else if (parts[0].equals("acc")) {
		global = global + Integer.valueOf(parts[1]);
		++line;
	}
	else {
		line = line + Integer.valueOf(parts[1]);
	}
}

System.out.println(global);
