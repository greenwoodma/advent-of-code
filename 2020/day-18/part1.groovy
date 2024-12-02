import java.util.regex.*;

String test1 = "1 + 2 * 3 + 4 * 5 + 6"; //71
String test2 = "1 + (2 * 3) + (4 * (5 + 6))"; //51
String test3 = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"; //13632

long sum = 0;

new File("input.txt").eachLine {line ->
	sum = sum + Long.valueOf(simplify(line));
}

System.out.println(sum);


public String simplify(String input) {
	

	Pattern p = Pattern.compile("\\(([^\\)\\(]*)\\)");

	while (input.indexOf("(") != -1) {
System.out.println(input);
		StringBuffer simpler = new StringBuffer();

		Matcher m = p.matcher(input);

		while (m.find()) {
			m.appendReplacement(simpler, simplify(m.group(1)));
		}

		m.appendTail(simpler);

		input = simpler.toString();
	}

	return doMaths(input).toString();
}

public long doMaths(String input) {
	String[] steps = input.split("\\s+");

	long value = Long.valueOf(steps[0]);

	for (int i = 1 ; i < steps.length ; i = i +2) {
		long next = Long.valueOf(steps[i+1]);

		if ("+".equals(steps[i])) {
			value = value + next;
		}
		else {
			value = value * next;
		}
	}


	return value;
}
