import java.io.*
import java.util.regex.*;

Pattern pattern = Pattern.compile("([0-9]+)-([0-9]+) ([a-z]): (.*)");

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

String line = fin.readLine();

int valid = 0;

while(line != null) {

	Matcher m = pattern.matcher(line);

	if (!m.matches()) throw new RuntimeException(line);

        char repeating = m.group(3).charAt(0);

	int min = Integer.valueOf(m.group(1));
	int max = Integer.valueOf(m.group(2));

	String password = m.group(4);


	if (password.charAt(min-1) == repeating && password.charAt(max-1) != repeating)
		++valid;

	if (password.charAt(min-1) != repeating && password.charAt(max-1) == repeating)
		++valid;

	line = fin.readLine();	
}

System.out.println(valid);


