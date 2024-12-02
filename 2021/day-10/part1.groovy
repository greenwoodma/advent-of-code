char[] opening = "({[<".toCharArray();
char[] closing = ")}]>".toCharArray();

long score = 0;

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
				//valid = false;
				break;
			} else {
			
			char symbol = stack.pop();
			int si = opening.findIndexOf { it == symbol }
			
			if (si != ci) {
				//valid = false;
				System.out.println(line);
				//System.out.println(c)
				//System.out.println(oi)
				//System.out.println(si)
				//System.out.println(symbol)
				
				switch (c) {
					case ')':
						score += 3;
						break;
					case']':
						score += 57;
						break;
					case '}':
						score += 1197;
						break;
					case '>':
						score += 25137;
						break;
				} 
				
				break;
			}
			}
		}
		
		//System.out.println(stack)
	}
	
	}
	
	System.out.println(score);
