long total = 0;

words = ["one","two","three","four","five","six","seven","eight","nine"]

new File("input.txt").eachLine { line ->
	
	String newLine = "";
	
	for (int i = 0 ; i < line.length() ; ++i) {
		if (Character.isDigit(line.charAt(i))) {
			newLine += line.charAt(i);
		} else {
		
			boolean done = false;
			for (int w = 0 ; w < 9 ; ++w) {
				if (line.substring(i).startsWith(words[w])) {
					newLine += ""+(w+1);
					done = true;
					break;
				}
			}
		}
	}

	
	
	long value = Long.valueOf(newLine[0]+newLine[-1]);
	
	total += value;
	
}

System.out.println("\n"+total);
