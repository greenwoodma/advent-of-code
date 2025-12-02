@Grab(group='org.apache.commons', module='commons-lang3', version='3.20.0')
import org.apache.commons.lang3.StringUtils;

String ranges = (new File("input.txt")).readLines()[0];

long total = 0;

for (String range : ranges.split(",")) {
	
	(start, end) = range.split("-")
	
	// both numbers are odd and the same length so no possibility
	// they could be made up from a repeating sequence
	if (start.length() == end.length() && start.length() % 2 == 1) continue;
	
	// start is odd so increase it to the next power of ten
	if (start.length() % 2 == 1) start = "1"+StringUtils.repeat('0', start.length());
	
	// end is odd then reduce it to the largest number
	// in the lower power of ten
	if (end.length() % 2 == 1) end = StringUtils.repeat('9', end.length()-1);
	
	// if the start string is now longer than the end skip this range
	if (start.length() > end.length()) continue;
	
	long max = Long.valueOf(end);
	
	// make a number from the first half of the start
	// why is groovy turning a simple divide into a BigDecimal
	Long number = Long.valueOf(start.substring(0,(start.length()/2).toInteger()))
	
	// convert the start to a number instead of a string
	start = Long.valueOf(start)
	
	while (true) {
		
		// the next number to check is made by concatenating two copies
		// of the number we have stored together
		Long next = Long.valueOf(number.toString() + number.toString());
		
		// increase the number for the next go around the loop
		++number;
		
		// if we've ended up making a number smaller than the start
		// then move on to the next number
		if (next < start) continue;
		
		// if we've gone past the end then stop
		if (next > max) break;
		
		// otherwise we have a number made of two repeating sequences
		// so add it to the total
		total += next
	}
}

// display the total
System.out.println(total);
