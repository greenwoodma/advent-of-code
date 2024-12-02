def sequence = []

new File("input.txt").eachLine {line ->
    sequence << Long.valueOf(line)
}

System.out.println(sequence);

int invalid = 530627549;

for (int i = 0 ; i < sequence.size() ; ++i) {
	long total = sequence.get(i);
	long min = total;
	long max = total;

	for (int j = i+1 ; j < sequence.size() ; ++j) {
		total += sequence.get(j);
		min = Math.min(min,sequence.get(j));
		max = Math.max(max,sequence.get(j));

		if (total > invalid) break;

		if (total == invalid) {
			System.out.println(min+max);
			return;
		}
	}
}

System.out.println("failed!");
