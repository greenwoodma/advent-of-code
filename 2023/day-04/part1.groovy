long total = 0;

new File("input.txt").eachLine { line ->

	line = line.substring(line.indexOf(":")+1);
	
	cards = line.split("\\|");
	
	winning = cards[0].trim().split("\\s+")*.toInteger()
	mine = cards[1].trim().split("\\s+")*.toInteger()
	
	mine.retainAll(winning);
	
	if (mine.size() > 0)
		total += Math.pow(2,mine.size()-1);

}

System.out.println(total);
