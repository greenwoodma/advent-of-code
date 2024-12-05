def rules = new HashMap<Long,Set<Long>>()
def manuals = [];
new File("input.txt").eachLine { line ->
	if (line.indexOf("|") != -1) {
		// process the rule
		parts = line.split("\\|")*.toLong()
		
		others = rules.getOrDefault(parts[0], new HashSet<Long>());
		others.add(parts[1]);
		
		rules.put(parts[0],others);
	}
	else if (line.indexOf(",") != -1) {
		// process the manual
		manuals.add(line.split(",")*.toLong())
	}
}

long total = 0;

for (manual : manuals) {
	boolean valid = true;

	for (int p = 0 ; p < manual.size() && valid ; ++p) {
		others = rules.get(manual[p]);
		
		if (others == null) continue;
		
		for (page : others) {
			i = manual.indexOf(page);
			if (i != -1 && i < p) {
				valid = false;
				break;
			}
		}
	}
	
	if (valid) {
		// println manual
		total += manual[manual.size()/2]
	}
}

println total
