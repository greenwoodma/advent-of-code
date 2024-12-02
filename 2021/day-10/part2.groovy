char[] opening = "({[<".toCharArray();
char[] closing = ")}]>".toCharArray();

List<Long> scores = new ArrayList<Long>();

new File("input.txt").eachLine { line ->
	Stack<Character>  stack = new Stack<Character>();

	boolean valid = true;
	
	//System.out.println("\n\n"+line)

 	for (int i = 0 ; i < line.length() ; ++i){
 		char c = line.charAt(i);
		
		int oi = opening.findIndexOf{ it == c }
		int ci = closing.findIndexOf{ it == c }
		//System.out.println(c)
		//System.out.println(oi+"/"+ci)
		
		if (oi != -1) {
			stack.push(c);
		} else {
			if (stack.empty) {
				valid = false;
				break;
			} else {
			
			char symbol = stack.pop();
			int si = opening.findIndexOf { it == symbol }
			
			if (si != ci) {
				valid = false;
				 
				
				break;
			}
			}
		}
		
		
	}
	
	if (valid) {
		System.out.println(stack)
		long score = 0;
		
		for (int i = stack.size()-1 ; i >= 0 ; --i) {
			score = score * 5;
			
			switch (stack.get(i)) {
				case '(':
					score += 1;
					break;
				case '[':
					score += 2;
					break;
				case '{':
					score += 3;
					break;
				case '<':
					score += 4;
					break;
			}
		}
		
		System.out.println(score);
		
		scores.add(score);
	}
	
}

	scores.sort();
	
	System.out.println(scores);
	
	int index = scores.size()/2;
	
	System.out.println(scores.get(index));


