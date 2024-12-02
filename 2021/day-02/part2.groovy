long position = 0;
long aim = 0;
long depth = 0;

new File("input.txt").eachLine { line ->

	String[] data = line.split("\\s+");
	
	long amount = Long.valueOf(data[1])
	
	if (data[0].equals("forward")) {
		position += amount;
		depth += aim * amount;
	}
	else if (data[0].equals("down")) {
		aim += amount;
	}
	else {
		aim -= amount;
	}

}

System.out.println(position*depth);
