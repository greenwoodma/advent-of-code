caves = new HashMap();

new File("input.txt").collect { line ->
	(from , to) = line.split("-")
	
	Set leadsTo = caves.getOrDefault(from, new HashSet())	
	leadsTo.add(to)	
	caves.put(from, leadsTo)
	
	if (!(from.equals("start") || to.equals("end"))) {
		leadsTo = caves.getOrDefault(to, new HashSet())	
		leadsTo.add(from)	
		caves.put(to, leadsTo)
	}
}

//System.out.println(caves)

List start = new ArrayList();
start.add("start");

found = explore(start);

System.out.println(found.size());


public Set explore(List path) {
	Set furtherPaths = new HashSet()
	
	String current = path[-1]
	
	exits = caves.get(current);
	
	if (exits == null) return furtherPaths;
	
	for (String c : exits) {
		List extend = new ArrayList(path);
		extend.add(c);
		
		if (c.equals("end")) {
			furtherPaths.add(extend);
		} else {
		
			if (c.toLowerCase().equals(c) && path.contains(c)) continue;
			
			if (c.toUpperCase().equals(c) && path.contains(c)) {
				//continue;
			}
			
			furtherPaths.addAll(explore(extend));
		}
	}
	
	return furtherPaths;
}
