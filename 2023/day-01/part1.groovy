long total = 0;

new File("input.txt").eachLine { line ->
	line = line.replaceAll("[^0-9]","");
	
	long value = Long.valueOf(line[0]+line[-1]);
	
	total += value;
}

System.out.println(total);
