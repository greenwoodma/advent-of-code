import java.io.*;
import java.util.regex.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

String rule = fin.readLine();

Map rules = new HashMap<String,List<Integer>>();

Pattern p = Pattern.compile("([0-9]+)-([0-9]+)");

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

String myTicket = fin.readLine();

fin.readLine();
fin.readLine();

String ticket = fin.readLine();


long count = 0;

while (ticket != null) {
	System.out.println(ticket);
	String[] codes = ticket.split(",");

	for (def code : codes) {
		code = Integer.valueOf(code);

		boolean valid = false;

		for (Map.Entry<String,List<Integer>> ruleInfo : rules.entrySet()) {

			ranges = ruleInfo.getValue();
			
			valid = valid || ((code >= ranges.get(0) && code <= ranges.get(1)) || (code >= ranges.get(2) && code <= ranges.get(3)));

			if (valid) break;

		}	
		System.out.println(code+": "+valid);

		if (!valid) count += code;
	}
	
	ticket = fin.readLine();
}

System.out.println(count);
