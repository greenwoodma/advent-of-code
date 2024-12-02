import java.io.*;
import java.util.regex.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

String line = fin.readLine();

Map rules = new HashMap();

Pattern bagPattern = Pattern.compile("([0-9]+)\\s([a-z\\s]+)\\sbag");

while (line != null) {

	String colour = line.substring(0,line.indexOf("bags")).trim();
	
	Set inside = new HashSet();

	Matcher m = bagPattern.matcher(line);

	while(m.find()) {
		inside.add(m.group(2).trim());
	}
	
	rules.put(colour, inside);

	line = fin.readLine();
}

System.out.println(rules);

int count = 0;

for (String top : rules.keySet()) {
	if (!top.equals("shiny gold")) {
		boolean okay = contains("shiny gold",rules.get(top), rules);
		System.out.println(top+":" + okay);
		if (okay) ++count;
	}
}

System.out.println(count);

private boolean contains(String colour, Set colours, Map rules) {
	if (colours.contains(colour)) return true;

	
	for (String next : colours) {
		if (contains(colour,rules.get(next),rules)) return true;
	}

	return false;
}
