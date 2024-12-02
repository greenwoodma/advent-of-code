long total = 0;

def max = [red:12, green: 13, blue: 14] 

new File("input.txt").eachLine { line ->
	long id = Long.valueOf(line.split(":")[0].split("\\s")[1])
		
	String[] games = line.split(":")[1].split(";")
	
	boolean valid = true;
	
	for (String game : games) {
		
		for (String balls : game.split(",")) {
			long value = Long.valueOf(balls.trim().split("\\s")[0]);
			String color = balls.trim().split("\\s")[1];
			
			if (value > max[color]) {
				valid = false;
			}
		}
	}
	
	if (valid) total += id;

}

System.out.println(total);
