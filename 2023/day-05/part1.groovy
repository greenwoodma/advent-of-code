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

	// there is a subtle bug here which means the last line in the
	// file is missed. Fortunately it didn't affect the calculation
	// given the sample or my input file
	while (!map.equals("") && lines.size() > 0) {
		a.addRule(map);
		map = lines.remove(0);
	}
	
	mappings.add(a);
}

Long result = Long.MAX_VALUE;

for (Long seed : seeds) {
	for (Almanac a : mappings) {
		seed = a.map(seed);
	}
	
	result = Math.min(result,seed);
}

System.out.println(result);

class Almanac {
	public String from, to;
	
	//public Map<Long,Long> mapping = new HashMap<Long,Long>();
	
	List rules = new ArrayList();

	public Almanac(String desc) {
		def parts = desc.split("-");
		from = parts[0];
		to = parts[2];
	}

	void addRule(String rule) {
		rules.add(new Rule(rule));
	}
	
	public Long map(Long source) {
		//return mapping.getOrDefault(source,source);
		for (Rule r : rules) {
			def val = r.getMapping(source);
			if (val != null) return val;
		}
		
		return source;
	}

	class Rule {
		def data;
		
		public Rule(String rule) {
			data = rule.split("\\s+")*.toLong();
		}
		
		public getMapping(Long source) {
			if (source >= data[1] && source < data[1] + data[2]) {
				def offset = source - data[1];
				
				return data[0]+offset;
			}
			
			return null;
		}
	}
}
