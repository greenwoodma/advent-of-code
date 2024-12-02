long position = 0;
long depth = 0;

new File("input.txt").eachLine { line ->

	String[] data = line.split("\\s+");
	
	long amount = Long.valueOf(data[1])
	
	if (data[0].equals("forward")) {
		position += amount;
	}
	else if (data[0].equals("down")) {
		depth += amount;
	}
	else {
		depth -= amount;
	}

}

System.out.println(position*depth);
