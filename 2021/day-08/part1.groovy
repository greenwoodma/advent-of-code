long count = 0;

new File("input.txt").eachLine { line ->

	String[] data = line.split("\\s*\\|\\s*");
	
	output = data[1].split("\\s+");
	
	output.each {
		len = it.length();
		
		if (len == 2 || len == 3 || len == 4 || len == 7) {
			++count;
		}
	}

}

System.out.println(count);
