import java.io.*;
import java.util.regex.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

String line = fin.readLine();

Map rules = new HashMap();

Pattern bagPattern = Pattern.compile("([0-9]+)\\s([a-z\\s]+)\\sbag");

while (line != null) {

	String colour = line.substring(0,line.indexOf("bags")).trim();
	
	Map inside = new HashMap();

	Matcher m = bagPattern.matcher(line);

	int count = 0;

	while(m.find()) {
		//inside.add(m.group(2).trim());
		//count = count + Integer.valueOf(m.group(1));
		inside.put(m.group(2),Integer.valueOf(m.group(1)));
	}
	
	rules.put(colour, inside);

	line = fin.readLine();
}

//System.out.println(rules);

System.out.println(howManyInside("shiny gold", rules));

private int howManyInside(String colour, Map rules) {
	int total = 0;
	for (Map.Entry next : rules.get(colour)) {
		total = total + next.getValue() + (next.getValue() * howManyInside(next.getKey(),rules));
	}

	return total;
}

