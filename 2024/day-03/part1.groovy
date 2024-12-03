long total = 0;

new File("input.txt").eachLine { line ->
	def m = line =~ /mul\(([0-9]{1,3}),([0-9]{1,3})\)/
	
	(0..<m.count).each {
		total += Long.valueOf(m[it][1]) * Long.valueOf(m[it][2])
	}
}

println total
    
