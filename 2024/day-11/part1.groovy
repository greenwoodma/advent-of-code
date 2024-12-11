import groovy.transform.Memoized

def stones = (((new File("input.txt")).readLines()[0])).split("\s+")*.toLong()

long total = 0

for (stone : stones) {
	total += count(stone,25)
}

println "\n"+total

@Memoized
def long count(Long stone, int blinks) {
	if (blinks == 0) {
		return 1;
	}
	
	if (stone == 0)
		return count(1,blinks-1)
		
	if (stone.toString().length()%2 == 0) {
		String s = stone.toString();

		Long s1 = Long.valueOf(s.substring(0,(int)(s.length()/2)))
		Long s2 = Long.valueOf(s.substring((int)(s.length()/2)))

		return count(s1,blinks-1)+count(s2,blinks-1)
		
	}
	
	return  count(stone*2024,blinks-1)
}
