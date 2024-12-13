def data = (new File("input.txt")).readLines()

long total = 0;

for (int d = 0 ; d < data.size() ; d = d+4) {
	(aX, aY) = extract(data[d])
	(bX, bY) = extract(data[d+1])
	(X, Y) = extract(data[d+2])
	
	def solution = solve(aX,bX,aY,bY,X+10000000000000,Y+10000000000000)
	
	if (solution != null) {
		total += 3*solution[0] + solution[1]
	}
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

def solve(a, b, c, d, e, f) {
    def det = ((a * d) - (b * c));
    def x = ((d * e) - (b * f));
    def y = ((a * f) - (c * e));
    
    if (x % det == 0 && y % det == 0)
    		return [x/det, y/det]
    		
    	return null
}
