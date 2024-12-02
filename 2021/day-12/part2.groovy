caves = new HashMap();

new File("input.txt").collect { line ->
	(from , to) = line.split("-")
	
	// so loading the map was buggy in part1 as it could end
	// up cycling back to start, if start was in the to position
	// ths didn't affect the result in part1 but did in part2
	
	if (!from.equals("end") && !to.equals("start")) {
		Set leadsTo = caves.getOrDefault(from, new HashSet())	
		leadsTo.add(to)	
		caves.put(from, leadsTo)
	}

	if (!from.equals("start") && !to.equals("end")) {
		Set leadsTo = caves.getOrDefault(to, new HashSet())	
		leadsTo.add(from)	
		caves.put(to, leadsTo)
	}

}

//System.out.println(caves)

List start = new ArrayList();
start.add("start");

found = explore(start,false);

System.out.println(found.size())



public Set explore(List path, boolean twice) {
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
		
			if (c.toLowerCase().equals(c)) {
				occurances = path.findAll{e -> e.equals(c)}.size();

				if (occurances > 1) continue;

				if (occurances == 1) {
				 	if (!twice)	furtherPaths.addAll(explore(extend,true)); 
			 	}
			 	else {
					furtherPaths.addAll(explore(extend,twice)); 
			 	}
			} else {
				furtherPaths.addAll(explore(extend,twice));
			}
		}		
	}
	
	return furtherPaths;
}

/**
public boolean isValid(List path) {
	lower = path.findAll{ it.toLowerCase().equals(it) }

	long count = 0;
	
	lower.each { l ->
		occurances = lower.findAll(e -> e.equals(l));
		
		if (occurances.size() > 1) ++count;
	}
	
	return count < 2;	
}
**/
