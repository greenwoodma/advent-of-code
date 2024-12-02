import groovy.transform.*;

@Field
Map rules = new HashMap();

boolean inrules = true;

long total = 0;

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
		//System.out.println(parts);
		
		for (String p : parts) {
			def data = p.split(":");
			
			if (data.size() == 1) {
				rule.add([data[0]]);
			} else {		
				def matcher = data[0] =~ /([xmas])(<|>)([0-9]+)/;
				//System.out.println(matcher[0]);
				
				rule.add([xmas.indexOf(matcher[0][1]),"<".equals(matcher[0][2]), Long.valueOf(matcher[0][3]), data[1]]);
			}
		}
		
		//System.out.println(rule);
		rules.put(rname,rule);
	
	} else {
		//System.out.println(line);
		
		def values = (line =~ /[0-9]+/)*.toLong();
		
		if (check(values,"in"))
			total += values.sum();
		
		//System.out.println(values.sum())
		
		
	}
}

System.out.println(total);

@TailRecursive
public boolean check(def values, String rule) {
	if (rule.equals("A")) return true;
	
	if (rule.equals("R")) return false;
	
	def parts = rules.get(rule);
	
	for (def part : parts) {
		if (part.size() == 1) {
			return check(values,part[0]);
		}
		
		if (part[1] && values[part[0]] < part[2]) return check(values,part[3]);
		
		if (!part[1] && values[part[0]] > part[2]) return check(values,part[3]);
	}

	return false;
}
