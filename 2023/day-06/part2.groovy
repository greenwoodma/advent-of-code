// strangely my solution to part 1 just works for part 2
// when you tweak the input file as described in the question
// so yes this code is the same as part1

def lines = (new File("input2.txt")).readLines()

times = lines[0].split("\\s+")[1..-1]*.toLong();
distances = lines[1].split("\\s+")[1..-1]*.toLong();

Long total = 1;

for (int r = 0 ; r < times.size() ; ++r) {

	Long wins = 0;

	for (Long h = 1 ; h < times[r] ; ++h) {
		distance = h * (times[r]-h);
		
		if (distance > distances[r]) wins++;
	}
	
	total = total * wins;
}

System.out.println(total);
