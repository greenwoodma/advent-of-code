values = new TreeMap<String,Integer>()

boolean inRules  = false;

List rules = new ArrayList()

new File("input.txt").eachLine { line ->
	
	if (line.equals("")) {
		inRules = true;
	} else if (!inRules) {
		(wire, value) = line.split(":\\s+")
		//println wire + " --> "+value
		values.put(wire,value == "1")
	} else {
		// ignore this for now
		(wire1, op, wire2, x, out) = line.split("\\s+")
		rules.add(new Tuple4(wire1,op,wire2,out))
	}
}

//println values

while (rules.size() > 0) {
	for (int i = 0 ; i < rules.size() ; ++i) {
		r = rules[i];
		
		if (values.containsKey(r[0]) && values.containsKey(r[2])) {
			rules.remove(i);
			
			v1 = values.get(r[0]);
			v2 = values.get(r[2]);
			
			if (r[1].equals("AND")) {
				values.put(r[3], v1 && v2)
			} else if (r[1].equals("OR")) {
				values.put(r[3], v1 || v2)
			} else if (r[1].equals("XOR")) {
				values.put(r[3], v1 ^ v2)	
			}
			
			break;
		}
	}
	
	//println rules.size()
}

//println values

String result = "";

for (v : values.entrySet()) {
	if (v.getKey().startsWith("z")) {
		result = (v.getValue() ? "1" : 0) + result	
	}
}

println result
println Long.parseLong(result,2)
