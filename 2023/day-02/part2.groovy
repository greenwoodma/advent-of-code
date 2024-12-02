long total = 0;



new File("input.txt").eachLine { line ->
	long id = Long.valueOf(line.split(":")[0].split("\\s")[1])
		
	String[] games = line.split(":")[1].split(";")

	def min = [red: 1, green: 1, blue: 1]
	
	for (String game : games) {
		
		for (String balls : game.split(",")) {
			long value = Long.valueOf(balls.trim().split("\\s")[0]);
			String color = balls.trim().split("\\s")[1];
			
			min[color] = Math.max(value, min[color])
		}
	}
	
	
	total += min["red"]*min["green"]*min["blue"]

}

System.out.println(total);
