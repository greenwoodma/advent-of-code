import java.io.*;
import java.util.regex.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

String mask = "";
System.out.println(mask);

Map memory = new HashMap<Integer, Integer>();

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

	int address = Integer.valueOf(m.group(1));

	int value = Long.valueOf(m.group(2));

	binaryString = new BigInteger(m.group(2));

	binaryString = binaryString.toString(2);

	binaryString = ("0"*(36-binaryString.length()))+binaryString;

	binaryString = binaryString.toCharArray();

	for (int i = 0 ; i < 36 ; ++i) {
		if (mask.charAt(i) != 'X')
			binaryString[i] = mask.charAt(i);
	}

	binaryString = new String(binaryString);

	newValue = new BigInteger(binaryString,2);

	memory.put(address,newValue);

	System.out.println(address + " --> " + value + " -- " + binaryString + " -- "+ newValue);

	command = fin.readLine();
}

System.out.println(memory);
System.out.println(memory.values().sum());
