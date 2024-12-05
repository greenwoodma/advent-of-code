import groovy.transform.Memoized

rules = new HashMap<Long,Set<Long>>()
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

long count = 0;

for (manual : manuals) {
	
	if (!isValid(manual)) {
		
		for (p : generatePerm(manual)) {
			if (isValid(p)) {
				total += p[p.size()/2]
				break;
			}
		}
		
		
	}
	
	++count;
}

println total

def boolean isValid(manual) {
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
	
	return valid;
}

// method "borrowed" from https://stackoverflow.com/a/10305419
def <E> List<List<E>> generatePerm(List<E> original) {
  if (original.isEmpty()) {
    List<List<E>> result = new ArrayList<>();
    result.add(new ArrayList<>());
    return result;
  }
  E firstElement = original.remove(0);
  List<List<E>> returnValue = new ArrayList<>();
  List<List<E>> permutations = generatePerm(original);
  for (List<E> smallerPermutated : permutations) {
    for (int index = 0; index <= smallerPermutated.size(); index++) {
      List<E> temp = new ArrayList<>(smallerPermutated);
      temp.add(index, firstElement);
      
      // only add the shorter piece if it is itself valid
      // this is the trick to make it work, otherwise we'd
      // run for ever generating all possible permutations
      // and if I wanted that I could have just done
      // list.permutations() in groovy (I tried it never
      // finished!)
      if (isValid(temp)) returnValue.add(temp);
    }
  }
  return returnValue;
}
