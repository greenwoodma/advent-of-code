long total = 0;

new File("input.txt").eachLine { line ->
	data = line.split("[^0-9]+")*.toLong()

	if (check(data[0],data[1],data.tail().tail()))
		total += data[0]
}

println total

def boolean check(total, current, rest) {
	
	if (rest.size() == 0) return total == current;
	
	// took ages to solve as I originally had this as >=
	// but that's stupid as you could have many more 1s
	// in the rest and multiplying would keep the same
	// total
	if (current > total) return false;
		
	return check(total,current+rest[0],rest.tail()) || check(total,current*rest[0],rest.tail())
		|| check(total,Long.valueOf(current+""+rest[0]),rest.tail())
}
