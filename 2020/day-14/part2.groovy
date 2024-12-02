import java.io.*;
import java.util.regex.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

String mask = "";
System.out.println(mask);

Map memory = new HashMap<BigInteger, Long>();

System.out.println(memory);
String command = fin.readLine();

Pattern pattern = Pattern.compile("mem\\[([0-9]+)\\] = ([0-9]+)")

while (command != null) {

	if (command.startsWith("mask")) {
		mask = command.split("=")[1].trim();
		System.out.println(mask);
		command = fin.readLine();
		continue;
	}	

	Matcher m = pattern.matcher(command);

	m.matches();

	long address = Long.valueOf(m.group(1));

	long value = Long.valueOf(m.group(2));

	binaryString = new BigInteger(m.group(1));

	binaryString = binaryString.toString(2);

	binaryString = ("0"*(36-binaryString.length()))+binaryString;

	binaryString = binaryString.toCharArray();

	System.out.println(address + " == " + binaryString)

	for (int i = 0 ; i < 36 ; ++i) {
		if (mask.charAt(i) != '0')
			binaryString[i] = mask.charAt(i);
	}

	System.out.println(binaryString);

	binaryString = new String(binaryString);

	List<String> exploded = explode(binaryString);

	for (String newAddress : exploded) {
		address = new BigInteger(newAddress,2);
		
		System.out.println(address + " --> " + value);
		memory.put(address,value);
	}

	

	command = fin.readLine();
}

System.out.println(memory);
System.out.println(memory.values().sum());


private List<String> explode(String value) {
	List<String> exploded = new ArrayList<String>();

	int firstX = value.indexOf("X");

	if (firstX == -1)
		exploded.add(value);
	else {
		char[] chars = value.toCharArray();

		chars[firstX] = "0";

		exploded.addAll(explode(new String(chars)));

		chars[firstX] = "1";

		exploded.addAll(explode(new String(chars)));
	}

	return exploded;
}
