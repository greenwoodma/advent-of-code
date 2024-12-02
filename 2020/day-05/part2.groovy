import java.io.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

String line = fin.readLine();

int max = 0;
int min = Integer.MAX_VALUE;

Set seats = new HashSet();

for (int i = 0 ; i < 128 ; ++i) {
	for (int j = 0 ; j < 8 ; ++j) {
		seats.add(i*8+j);
	}
}


while(line != null) {
	int row = getRow(line.substring(0,7),0,127);
	int column = getRow(line.substring(7),0,7);

	int id = row * 8 + column;

	max = Math.max(max,id);
	min = Math.min(min,id);

	seats.remove(id);

	line = fin.readLine();
}

//System.out.println(max);

for (int remaining : seats) {
	if (remaining >= min && remaining <= max)
		System.out.println(remaining);
}

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
