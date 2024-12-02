import java.io.*;

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

	return passport.keySet().containsAll(fields);
}
