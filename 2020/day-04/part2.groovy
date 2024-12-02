import java.io.*;
import java.util.regex.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

int valid = 0;

Map passport = new HashMap();

String line  = fin.readLine();

while (line != null) {
	if (line.equals("")) {
		if (isValid(passport)) ++valid;
		passport = new HashMap();
	}
	else {

		for (String part : line.split("\\s+")) {
			String[] data = part.split(":");

			passport.put(data[0],data[1]);
		
		}
	}

	line  = fin.readLine();
}

if (passport.size() > 0 && isValid(passport)) ++valid;

System.out.println(valid);


private boolean isValid(Map passport) {

	Set fields = ["byr","iyr","eyr","hgt","hcl","ecl","pid"];
	Set eyes = ["amb", "blu", "brn", "gry", "grn", "hzl", "oth"];

	Pattern hp = Pattern.compile("([0-9]+)(cm|in)");


	if (!passport.keySet().containsAll(fields)) return false;

	try {
		if (!validNumber(passport.get("byr"),1920,2002)) return false;

		if (!validNumber(passport.get("iyr"),2010,2020)) return false;

		if (!validNumber(passport.get("eyr"),2020,2030)) return false;

		Matcher m = hp.matcher(passport.get("hgt"));
		if (!m.matches()) return false;

		if (m.group(2).equals("cm") && !validNumber(m.group(1),150,193)) return false;
		if (m.group(2).equals("in") && !validNumber(m.group(1),59,76)) return false;

		if (!passport.get("hcl").matches("#[0-9a-f]{6}")) return false;

		if (!eyes.contains(passport.get("ecl"))) return false;

		if (!passport.get("pid").matches("[0-9]{9}")) return false;
	}
	catch (Exception e) {
		// any exception means the values didn't convert
e.printStackTrace();
		return false;
	}

	return true;
}

private boolean validNumber(String input, int min, int max) {
	int value = Integer.valueOf(input);

	return value >= min && value <= max;
}
