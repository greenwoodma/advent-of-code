import groovy.transform.Memoized

// 399 is too high

def input = (new File("input.txt")).readLines()

hashGaz = new HashMap<String,Map<String,String>>()


for (towel : input[0].split(",\\s+")) {
	Map current = hashGaz
	for (int i = 0 ; i < towel.length() ; ++i) {
		Map next = current.getOrDefault(towel[i], new HashMap<String,String>())
		
		if (i == towel.length()-1) next.put("END","END")
		
		current.put(towel[i],next)
		current = next
	}
}

long total = 0

for (int i = 2 ; i < input.size() ; ++i) {
	//println i
	//if (isValid(input[i], 0, hashGaz)) ++total
	total += isValid(input[i],0,hashGaz)
	//println ""
	
	//if (true) break;
}

println total

@Memoized
long isValid(String sequence, int offset, Map gaz) {
	
	//println sequence.length()+ " "+ sequence + " " + offset + " " + sequence.substring(offset) 
	
	
	if (offset == sequence.length()) {
		// we've got to the last character so if there is an END tag
		// or we've just finished a term then we've found a complete sequence
		//println (gaz!= null && gaz.containsKey("END")) || hashGaz.equals(gaz)
		if ((gaz!= null && gaz.containsKey("END")) || hashGaz.equals(gaz)) return 1
		return 0
	}
	
	// if we've requested something that isn't possible then
	// this can't be a path
	if (gaz == null) return 0;
	
	long r = isValid(sequence,offset+1,gaz.get(sequence[offset]))
	
	
	
	if (gaz.containsKey("END"))
		r += isValid(sequence,offset,hashGaz)
	
	
	
	return r
}
