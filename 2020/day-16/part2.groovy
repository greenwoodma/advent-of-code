import java.io.*;
import java.util.regex.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

String rule = fin.readLine();

rules = new HashMap<String,List<Integer>>();

Pattern p = Pattern.compile("([0-9]+)-([0-9]+)");

valid = new HashMap<String,Set<Integer>>();
invalid = new HashMap<String,Set<Integer>>();

while (!rule.equals("")) {
	Matcher m = p.matcher(rule);

	def valid = [];

	while (m.find()) {
		valid.add(Integer.valueOf(m.group(1)));
		valid.add(Integer.valueOf(m.group(2)));
	}

	rules.put(rule.substring(0,rule.indexOf(":")), valid);

	rule=fin.readLine();
}

System.out.println(rules);

fin.readLine();

String[] myTicket = fin.readLine().split(",");

fin.readLine();
fin.readLine();

String ticket = fin.readLine();

while (ticket != null) {

	String[] codes = ticket.split(",");

	if (isValid(codes)) {
		System.out.println(codes);

		for(int i = 0 ; i < codes.length ; ++i) {
			code = Integer.valueOf(codes[i]);

			for (Map.Entry<String,List<Integer>> ruleInfo : rules.entrySet()) {
				String meaning = ruleInfo.getKey();

				ranges = ruleInfo.getValue();

				

				if (isValid(code,ranges)) {
					System.out.println("Valid " +meaning+" "+ code + ":"+ranges+":"+i);
					Set<Integer> possibles = valid.getOrDefault(meaning,new HashSet<Integer>());				
					possibles.add(i+1);
					valid.put(meaning,possibles);
				}
				else {
					Set<Integer> possibles = invalid.getOrDefault(meaning,new HashSet<Integer>());				
					possibles.add(i+1);
					invalid.put(meaning,possibles);
				}
			}

		}
	}
	
	ticket = fin.readLine();
}

System.out.println(valid);
System.out.println(invalid);

for (Map.Entry<String,Set<Integer>> not : invalid.entrySet()) {
	valid.getOrDefault(not.getKey(), new HashSet<Integer>()).removeAll(not.getValue());
}

System.out.println(valid);

while (true) {
	int count = 0;

	for (String r1 : rules.keySet()) {
		Set<Integer> possibles = valid.get(r1);

		if (possibles.size() == 1) {
			++count;
			for (String r : rules.keySet()) {
				if (!r.equals(r1)) {
					valid.get(r).removeAll(possibles);
				}
			}
		}
	}

	System.out.println(valid);

	if (count == rules.size()) break;

}

long result = 1;

for (String slot : valid.keySet()) {
	

	if (slot.startsWith("departure")) {
		int slotNum = valid.get(slot).iterator().next();	
		long mine = Long.valueOf(myTicket[slotNum-1]);

		System.out.println(slot + " is " + slotNum + " which is " + mine);
		result = result * mine;
	}
}

System.out.println(result);


private boolean isValid(String[] codes) {
	for (def code : codes) {
		code = Integer.valueOf(code);

		boolean valid = false;

		for (Map.Entry<String,List<Integer>> ruleInfo : rules.entrySet()) {

			ranges = ruleInfo.getValue();
			
			valid = valid || isValid(code,ranges);

			if (valid) break;

		}	

		if (!valid) return false;
	}

	return true;
}

private boolean isValid(Integer code, ranges) {
	return ((code >= ranges.get(0) && code <= ranges.get(1)) || (code >= ranges.get(2) && code <= ranges.get(3)));
}

