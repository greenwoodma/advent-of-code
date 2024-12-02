long total = 0;

new File("input.txt").eachLine { line ->
	def commands = line.split(",");
	
	for (String command : commands) {
		long hash = 0;

		for (Character c : command.toCharArray()) {
			long code = (int)c;
			
			hash += code;
			hash = hash*17;
			hash = hash % 256
		}
		
		total += hash
	}
}

System.out.println(total);
