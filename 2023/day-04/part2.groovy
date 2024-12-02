
def counts = [];
def points = [];

new File("input.txt").eachLine { line ->

	line = line.substring(line.indexOf(":")+1);
	
	cards = line.split("\\|");
	
	winning = cards[0].trim().split("\\s+")*.toInteger()
	mine = cards[1].trim().split("\\s+")*.toInteger()
	
	mine.retainAll(winning);
		
	points.add((Long)mine.size());
		
	counts.add(1L);

}

for (int c = 0 ; c < points.size() ; ++c) {
	int p = points.get(c);
	
	int n = counts.get(c);
	
	for (int w = 1 ; w <= p ; ++w) {
		counts.set(c+w, counts.get(c+w)+n);
	}
}

System.out.println(counts.sum());
