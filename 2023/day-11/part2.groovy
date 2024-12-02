def lines = (new File("input.txt")).readLines()

def columns = [false] * lines[0].size();
def rows = [false] * lines.size();

def galaxies = [];

for (int r = 0 ; r < rows.size() ; ++r) {
	def matcher = lines[r] =~ /#/

	while (matcher.find()) {
		rows[r] = true;
		
		columns[matcher.start()] = true;
		
		galaxies.add([(long)matcher.start(),r])
	}
}

/*for (int r = 0 ; r < rows.size() ; ++r) {
	for (int c = columns.size() - 1 ; c >= 0 ; --c) {
		if (!columns[c]) {
			lines[r] = lines[r].substring(0,c)+"."+lines[r].substring(c);
		}
	} 
}

for (int r = rows.size()-1 ; r >= 0 ; --r) {
	if (!rows[r]) {
		lines.add(r, lines[r]);
	}
}*/

def add2Column = []//[0L] * lines[0].size();
def add2Row = []//[0L] * line.size();

long v = 0L
long addition=1000000-1;

for (int c = 0 ; c < columns.size() ; ++c) {
	if (!columns[c]) v=v+addition;
	add2Column.add(v);
}

v = 0L;
for (int r = 0 ; r < rows.size() ; ++r) {
	if (!rows[r]) v=v+addition;
	add2Row.add(v);
}





long total = 0;

for (int i = 0 ; i < galaxies.size()-1 ; ++i) {

	def g1 = galaxies[i];
	g1 = [g1[0]+add2Column[g1[0]], g1[1]+add2Row[g1[1]]];

	for (int j = i+1 ; j < galaxies.size() ; ++j) {
	
		def g2 = galaxies[j];
		g2 = [g2[0]+add2Column[g2[0]], g2[1]+add2Row[g2[1]]];

	
		int down = g2[1]-g1[1];
		int across = Math.abs(g2[0]-g1[0]);
		
		total += (down+across);	
	}
}

System.out.println(total);
