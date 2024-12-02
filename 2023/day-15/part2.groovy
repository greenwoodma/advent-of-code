

Map boxes = new HashMap();

new File("input.txt").eachLine { line ->
	def commands = line.split(",");
	
	for (String command : commands) {
		
		if (command.endsWith("-")) {
			command = command.substring(0,command.length()-1);
			
			long hash = hash(command);
			
			Map box = boxes.getOrDefault(hash,new LinkedHashMap());
			box.remove(command);
		} else {
			(command, val) = command.split("=");
			
			long hash = hash(command);
			
			Map box = boxes.getOrDefault(hash, new LinkedHashMap());
			
			box.put(command,Long.valueOf(val));
			
			boxes.put(hash,box);
		}		
	}
}

long total = 0;

for (Map.Entry box : boxes.entrySet()) {
	long num = box.getKey()+1;
	
	long slot = 1;
	
	for (Long focal : box.getValue().values()) {
		total += num * slot * focal;
		
		++slot;
	}
}

System.out.println(total);



def long hash(String command) {
	long hash = 0;

	for (Character c : command.toCharArray()) {
		long code = (int)c;
		
		hash += code;
		hash = hash*17;
		hash = hash % 256
	}
	
	return hash;
}
