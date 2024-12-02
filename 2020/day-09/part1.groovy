def sequence = []

new File("input.txt").eachLine {line ->
    sequence << Long.valueOf(line)
}

System.out.println(sequence);

int preamble = 25;

for (int i = preamble ; i < sequence.size() ; ++i) {
	boolean found = false;	
	for (int x = i-preamble ; x < i ; ++x) {
		for (int y = x+1 ; y < i ; ++y) {
			found = found || (sequence.get(x)+sequence.get(y) == sequence.get(i));
		}
	}

	if (!found) {
		System.out.println(sequence.get(i));
		return;
	}
}

System.out.println("failed to find invalid integer");
