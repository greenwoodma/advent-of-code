def lines = (new File("input.txt")).readLines()

def columns = [false] * lines[0].size();
def rows = [false] * lines.size();

for (int r = 0 ; r < rows.size() ; ++r) {
	def matcher = lines[r] =~ /#/

	while (matcher.find()) {
		rows[r] = true;
		
		columns[matcher.start()] = true;
	}
}

for (int r = 0 ; r < rows.size() ; ++r) {
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
}



def galaxies = [];

for (int r = 0 ; r < lines.size() ; ++r) {
	def matcher = lines[r] =~ /#/

	while (matcher.find()) {
		galaxies.add([matcher.start(),r])	
	}
}

long total = 0;

for (int i = 0 ; i < galaxies.size()-1 ; ++i) {
	for (int j = i+1 ; j < galaxies.size() ; ++j) {
		int down = galaxies[j][1]-galaxies[i][1];
		int across = Math.abs(galaxies[j][0]-galaxies[i][0]);
		
		total += (down+across);	
	}
}

System.out.println(total);
