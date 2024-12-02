@Grab(group='com.github.florianingerl.util', module='regex', version='1.1.9')


import java.io.*;
import com.florianingerl.util.regex.*;

BufferedReader fin = new BufferedReader(new FileReader("input2.txt"));

Map<String,String> rules = new HashMap<String,String>();

String rule0 = null;

System.out.println(rule0);

String rule = fin.readLine();

while (!rule.equals("")) {
	String[] parts = rule.split(":");

	String ruleID = parts[0].trim();
	String ruleText = parts[1].trim();

	if (ruleText.startsWith("\"")) {
		ruleText = ruleText.substring(1,ruleText.length()-1);
	} else if (ruleText.indexOf("|") != -1) {
		parts = ruleText.split("\\|");

		ruleText = "("+parts[0].trim()+")|("+parts[1].trim()+")";
	}

	if ("0".equals(ruleID)) {
		rule0 = ruleText;
	}
	else {
		rules.put(ruleID,ruleText);
	}

	rule = fin.readLine();
}

System.out.println(rule0);

Pattern p = Pattern.compile("[0-9]+");



while (true) {
	StringBuffer simpler = new StringBuffer();
	Matcher m = p.matcher(rule0);

	while(m.find()) {
		m.appendReplacement(simpler,"("+rules.get(m.group(0))+")");
	}

	m.appendTail(simpler);

	String newRule0 = simpler.toString();

	if (rule0.equals(newRule0)) break;

	rule0 = newRule0;
}

rule0 = rule0.replaceAll("\\s+","");

System.out.println(rule0);

p = Pattern.compile(rule0);

String pattern = fin.readLine();

int valid = 0;

while (pattern != null) {
	Matcher m = p.matcher(pattern);

	boolean matches = m.matches();

	System.out.println(pattern+": "+matches);

	if (matches) ++valid;

	pattern = fin.readLine();
}

System.out.println(valid);

