def input = new File("input.txt").collect();

numbers = input[0].split(",").collect{ it as int }

//System.out.println(numbers);

input = input.tail().tail()

def boards = new ArrayList<Board>();

for (int i = 0 ; i < input.size() ; i = i + 6) {
	boards.add(new Board(input, i));
}

numbers.each { n ->
	boards.each { b ->
		if (b.mark(n)) {
			System.out.println(b);
			
			System.out.println(b.score()*n);
			
			System.exit(0);
		}
	}
}

class Board {

	def data = new int[5][5];

	public Board(List input, int offset) {
	
		for (int i = offset ; i < offset+5 ; ++i) {
			data[i-offset] = input[i].trim().split("\\s+").collect { it as int }
		}
	
	}
	
	public boolean mark(int value) {
		for (int i = 0 ; i < 5 ; ++i) {
			for(int j = 0 ; j < 5 ; ++j) {
				if (data[i][j] == value) {
					data[i][j] = 0;
					
					if (checkRow(i) || checkColumn(j)) {
						return true;
					}
				}
			}
		}
		
		return false;
		
	}
	
	private boolean checkRow(int r) {
		return data[r].sum() == 0;
	}
	
	private boolean checkColumn(int c) {
		boolean won = true;
		
		for (int i = 0 ; i < 5 && won ; ++i) {
			won = won && data[i][c] == 0;
		}
		
		return won;
	}
	
	public int score() {
		int answer = 0;
		
		for (int i = 0 ; i < 5 ; ++i) {
			answer += data[i].sum();
		}
		
		return answer;
	}
	
	public String toString() {
		return data.toString();	
	}

}


