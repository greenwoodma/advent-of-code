long total = 0;

// 34004 is too low

def rows = []

new File("input.txt").eachLine { line ->
	if (line.equals("")) {
		total += 100*process(rows);
		total += process(rotate(rows));
		rows = []
	} else {
		rows.add(line);
	}
}

System.out.println(total);

public List rotate(def rows) {
	def columns = [""] * rows[0].size();
	
	for (int r = 0 ; r < rows.size() ; ++r) {
		for (int i = 0 ; i < rows[r].length() ; ++i) {
			columns[i] += rows[r].charAt(i);
		}
	}
	
	//System.out.println(columns);
	
	return columns;
}

public long process(def data) {
	def before = [];
	def after = [];
	for (int i = 0 ; i < data.size()-1 ; ++i) {
		if (diff(data[i],data[i+1]) < 2) {
			before.add(i);
			after.add(i+1)
		}			
	}
	
	long result = 0;
	
	for (int i = 0 ; i < before.size() ; ++i) {
		int left = before[i];
		int right = after[i];
		
		int j = 0;
		
		boolean valid = true;
		
		int smudges = 0;
		
		while (valid) {
			if (left-j < 0 || right+j >= data.size()) break;
			
			int diff = diff(data[left-j],data[right+j]);
			
			if (diff > 2) {
				valid = false;
				break;
			}
			
			smudges += diff;
			
			valid = valid && smudges < 2;
			++j;
		}
		
		if (valid && smudges==1) result = Math.max(result,left+1);
	}
	
	return result;
}


public int diff(String line1, String line2) {
	int count = 0;

	for (int i = 0 ; i < line1.length() ; ++i) {
		if (line1[i] != line2[i]) ++count;
	}
	
	return count;
}
