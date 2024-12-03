long total = 0;

boolean enabled = true;

new File("input.txt").eachLine { line ->
	def m = line =~ /do\(\)|don't\(\)|mul\(([0-9]{1,3}),([0-9]{1,3})\)/
	
	(0..<m.count).each {
		instruction = m[it]

		if (instruction[0].equals("do()"))
			enabled = true;
		else if (instruction[0].equals("don't()"))
			enabled = false;
		else if (enabled)
			total += Long.valueOf(m[it][1]) * Long.valueOf(m[it][2])
	}
}

println total
    
