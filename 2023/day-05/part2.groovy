def lines = (new File("input.txt")).readLines()

seeds = lines.remove(0);
seeds = seeds.substring(seeds.indexOf(":")+2).split("\\s+")*.toLong()

lines.remove(0);

List mappings = new ArrayList();

while (lines.size() > 0) {
	String type = lines.remove(0);
	System.out.println(type);
	
	Almanac a = new Almanac(type);
	
	String map = lines.remove(0);
	while (!map.equals("") ) {
		a.addRule(map);
		
		if (lines.size()==0) break;
		map = lines.remove(0);
		
	}
	a.close();
	
	mappings.add(a);
}


Long result = Long.MAX_VALUE;

for (int s = 0 ; s < seeds.size() ; s=s+2) {
	Long next = seeds.get(s);
	Long last = next + seeds.get(s+1)-1;
	
	//System.out.println("Seeds " + next+" to " + last);
	
	List spans = new ArrayList();
	spans.add([next,last]);
		
	for (Almanac a : mappings) {
		// split the current spans based on input ranges
		List split = new ArrayList();
		
		for (def span : spans) {
			Long start = span.get(0);
			Long end = span.get(1);
			
			next = a.next(start);
			
			if (next == null || next > end) {
				split.add(span);
			} else {
			
				while (true) {
					split.add([start, next-1]);
					
					start = next;
					next = a.next(start);
					
					if (next == null || next > end) {
						split.add([start,end]);
						break;
					}
				}
			}
		}
		
		//System.out.println(split);
		
		// convert the split spans
		
		for (def span : split) {
			span[0] = a.map(span[0]);
			span[1] = a.map(span[1]);
		}
		
		//System.out.println(split);
		
		spans = split;
	}
	
	// get minimum value from spans	
	
	for (def span : spans) {
		result = Math.min(result, Math.min(span[0],span[1]));
	}
		
}

System.out.println(result);

class Almanac {
	public String from, to;
	
	List rules = new ArrayList();
	
	Long start, end;

	public Almanac(String desc) {
		def parts = desc.split("-");
		from = parts[0];
		to = parts[2];
	}

	void addRule(String rule) {
		rules.add(new Rule(rule));
	}
	
	void close() {
		Collections.sort(rules);
		
		List newRules = new ArrayList();

		start = rules.get(0).data[2];
		end = rules.get(rules.size()-1).data[3];
		
		for (int r = 0 ; r <= rules.size()-2 ; ++r) {
			Rule rule1 = rules.get(r);
			Rule rule2 = rules.get(r+1);
			
			
			Rule rule1Half = new Rule([rule1.data[3],rule2.data[2],rule1.data[3],rule2.data[2]])
			
			newRules.add(rule1);
			if (rule1Half.data[2] != rule1Half.data[3]) newRules.add(rule1Half);
		}
		
		newRules.add(rules.get(rules.size()-1));
		//System.out.println(newRules);
		
		rules = newRules;
	}
	
	public Long map(Long source) {
		if (source < start) return source;
		if (source >= end) return source;
	
		for (Rule r : rules) {
			def val = r.getMapping(source);
			if (val != null) return val;
		}
		
		return source;
	}
	
	public Long next(Long previous) {
		if (previous < start) return start;
		if (previous >= end) return null;
		
		Rule rule = null;
		
		for (Rule r: rules) {
			def val = r.getMapping(previous);
			if (val != null) {
				rule = r;
				break;
			}
		}
		
		return rule.data[3];
	}

	class Rule implements Comparable {
		def data;
		
		public Rule(def data) {
			this.data = data;
		}
		
		public Rule(String rule) {
			data = rule.split("\\s+")*.toLong();
			//data[2] = data[1]+data[2];
			
			data = [data[0], data[0]+data[2], data[1],data[1]+data[2]]
			
		}
		
		public getMapping(Long source) {
		
			if (source >= data[2] && source < data[3]) {
				def offset = source - data[2];
				
				return data[0]+offset;
			}
			
			return null;
		}
		
		
		public int compareTo(Object other) {
			return data[2].compareTo(((Rule)other).data[2]);
		}
		
		public String toString() {
			return data[2]+"->"+data[3] +" : " + data[0]+"->"+data[1];
		}
	}
}
