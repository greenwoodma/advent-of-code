import groovy.transform.*;

@Field
Map rules = new HashMap();

boolean inrules = true;

String xmas = "xmas";

new File("input.txt").eachLine { line ->
	if (line.equals("")) {
		inrules = false;
		return
	}
	
	if (inrules) {
		def rule = [];
		
		String rname = line.substring(0,line.indexOf("{"));
		def parts = line.substring(line.indexOf("{")+1,line.length()-1).split(",");	
		
		for (String p : parts) {
			def data = p.split(":");
			
			if (data.size() == 1) {
				rule.add([data[0]]);
			} else {		
				def matcher = data[0] =~ /([xmas])(<|>)([0-9]+)/;
				
				rule.add([xmas.indexOf(matcher[0][1]),"<".equals(matcher[0][2]), Integer.valueOf(matcher[0][3]), data[1]]);
			}
		}
		
		rules.put(rname,rule);
	}
}



def start = []


for (int i = 0 ; i < 4 ; ++i) {
	BitSet bs = new BitSet(4001);
	bs.set(1,4001);
	
	start.add(bs);

}

System.out.println(check(start,"in"));


public long check(def values, String rule) {
	if (rule.equals("A")) {
		long total = 1;
		
		for (BitSet bs : values) {
			total = total * bs.cardinality();
		}
				
		return total;
	}
	
	if (rule.equals("R")) return 0;
	
	def parts = rules.get(rule);
	
	long total = 0;
	
	for (int i = 0 ; i < parts.size() ; ++i) {
	
		def copy = []
			
		for (BitSet bs : values) {
			copy.add(bs.clone());
		}

		for (int j = 0 ; j < i ; ++j) {
			BitSet mangle = copy[parts[j][0]]			
			
			if (!parts[j][1]) {
				// everything greater than part[2] is invalid
				mangle.clear(parts[j][2]+1,4001);
			}
			else {
				// everything less than part[2] is invalid
				mangle.clear(0,parts[j][2]);
			}
		}
	
		if (parts[i].size() == 1) {
			total += check(copy,parts[i][0]);
		} else {

			BitSet mangle = copy[parts[i][0]]
			
			if (parts[i][1]) {
				// everything less than part[2] is valid
				
				// so >= part[2] is invalid
				mangle.clear(parts[i][2],4001);
			}
			else {
				// everything greater than part[2] is valid
				
				// so <= part[2] is invalid
				mangle.clear(0,parts[i][2]+1);
			}
			
			total += check(copy,parts[i][3])
		}
		
	}

	return total;
}
