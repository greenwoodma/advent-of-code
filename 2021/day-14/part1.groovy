String sequence = null;
Map insert = new HashMap()

new File("input.txt").each{line ->
	if (sequence == null) {
		sequence = line;
	} else if (line.indexOf("->") != -1) {
		(from, to) = line.split(" -> ");
		insert.put(from,to);
	}
}

System.out.println(sequence)
System.out.println(insert)

Map counts = new HashMap()

sequence.toCharArray().each { c  ->
	c = c as String
	counts.put(c,counts.getOrDefault(c,0)+1);
}

System.out.println(counts)

for (int s = 1 ; s <= 10 ; ++s) {
	StringBuilder next = new StringBuilder(sequence.charAt(0) as String);
	
	for (int c = 0 ; c < sequence.length()-1 ; ++c) {
		String from = sequence.substring(c,c+2);
		String to = insert.get(from)
		
		counts.put(to, counts.getOrDefault(to,0)+1);
		
		//System.out.println(sequence+"("+c+") "+from+"->"+to)
		
		next.append(to).append(sequence.charAt(c+1) as String)
	}
	//System.out.println(next)
	sequence = next.toString()
}

System.out.println(counts)

max = counts.max { it.value }.value
min = counts.min {it.value}.value

System.out.println(max-min)
