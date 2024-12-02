def listing = []

new File("input.txt").eachLine {line ->
    listing << line
}

for (int i = 0 ; i < listing.size() ; ++i) {
	try {
		String[] parts = listing.get(i).split("\\s+");

		if (parts[0].equals("acc"))
			continue;

		String newLine = (parts[0].equals("jmp") ? "nop" : "jmp") + " " + parts[1];
		List altered = new ArrayList(listing);

		altered.set(i,newLine);
		System.out.println(runApplication(altered));

		break;
	}
	catch (Exception e) {
		System.err.println(e.getMessage());
	}
}

private runApplication(List listing) {

	int line = 0;

	Set linesRun = new HashSet();

	int global = 0;

	while (true) {
		if (linesRun.contains(line))
			throw new RuntimeException("infinite loop: "+global);

		if (line > listing.size())
			throw new RuntimeException("jumped too far: "+global);

		if (line == listing.size())
			return global;

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
}
