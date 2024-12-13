def data = (new File("input.txt")).readLines()

long total = 0;

for (int d = 0 ; d < data.size() ; d = d+4) {
	(aX, aY) = extract(data[d])
	(bX, bY) = extract(data[d+1])
	(X, Y) = extract(data[d+2])
	
	long minCost = 0;

	for (int i = 0 ; i < 100 ; ++i) {
		int j1 = check(X,i,aX,bX)
		int j2 = check(Y,i,aY,bY)
		
		if (j1 != -1 && j1 == j2) {
			long cost = 3*i + j1
			
			if (minCost == 0 || cost < minCost)
				minCost = cost;
		}
	}
	
	total += minCost
}

println total

long check(total, i, m, n) {
	long rest = total - (i * m)
	
	if (rest % n != 0) return -1;
	
	return rest / n
}

def extract(String d) {
	parts = d.split("[^0-9]+")
	
	return [parts[1] as Integer, parts[2] as Integer]
}
