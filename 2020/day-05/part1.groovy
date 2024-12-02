import java.io.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

String line = fin.readLine();

int max = 0;

while(line != null) {
	int row = getRow(line.substring(0,7),0,127);
	int column = getRow(line.substring(7),0,7);

	int id = row * 8 + column;

	max = Math.max(max,id);

	line = fin.readLine();
}

System.out.println(max);

private int getRow(String code, int min, int max) {
	
	//System.out.println(code);

	if (code.length() == 1) {
		if (code.equals("F") || code.equals("L")) return min;
		if (code.equals("B") || code.equals("R")) return max;
	}

	char letter = code.charAt(0);

	code = code.substring(1);


	int middle = ((max-min)/2)+min;
//System.out.println(letter);
//System.out.println("/"+min+"/"+max+"/"+middle);

	return getRow(code, letter == 'F' || letter == 'L' ? min : middle+1, letter == 'F' || letter == 'L' ? middle : max);
}
