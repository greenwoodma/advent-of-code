counts = null;
sequence = null
insert = new HashMap()

new File("input.txt").each{line ->
	if (counts == null) {
		sequence = line;
		counts = new HashMap();
		
		// split the input line into the overlapping pairs
		// and record the number of each unique pairing
		for (int i = 0 ; i < line.length()-1 ; ++i) {
			String pair = line.substring(i,i+2);
			counts.put(pair,counts.getOrDefault(pair,0L)+1L);
		}
		
	} else if (line.indexOf("->") != -1) {
		(from, to) = line.split(" -> ");
		
		// for each rule, work out what the two new pairs
		// would be, and store the pair instead of the 
		// single character in the original rule
		toL = from.charAt(0).toString()+to;
		toR = to+from.charAt(1).toString()
		
		insert.put(from,new Tuple2(toL,toR));
	}
}

for (s = 1 ; s <= 40 ; ++s) {
	next = new HashMap()
	
	for (Map.Entry entry : counts.entrySet()) {
		// for each unique pair in the current string....
		
		// get the two pairs it expands to	
		Tuple2 tuple = insert.get(entry.getKey());
		
		// the count for each is the count of the starting pair at the current
		// step plus the count for any of this pair we've already added to the next step
		next.put(tuple.first, entry.getValue()+next.getOrDefault(tuple.first,0L));
		next.put(tuple.second, entry.getValue()+next.getOrDefault(tuple.second,0L));
		
	}	
	
	counts = next;
}

// now take the counts of each pair and work out
// the counts for each individual letter
letters2 = new HashMap()
for (Map.Entry entry : counts.entrySet()) {
	(l, r) = entry.getKey().toCharArray();
	letters2.put(l, letters2.getOrDefault(l,0)+entry.getValue());
	letters2.put(r, letters2.getOrDefault(r,0)+entry.getValue());
}

// the letter counts are doubled because the pairs overlap
// in the string, so divide the count for each letter in half
letters = new HashMap();
for (Map.Entry entry : letters2.entrySet()) {
	letters.put(entry.getKey(),(Long)(entry.getValue()/2));
}

// of course the first and last letter in the original sequence
// only occur in one pair so we need to add one to their count
// to compensate
letters.put(sequence.toCharArray()[0],letters.getOrDefault(sequence.toCharArray()[0],0L)+1L);
letters.put(sequence.toCharArray()[-1],letters.getOrDefault(sequence.toCharArray()[-1],0L)+1L);

// finally we can get the min and max values
max = letters.max { it.value }.value
min = letters.min {it.value}.value

System.out.println(max-min)
