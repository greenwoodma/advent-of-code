

int pos = 50;

List<String> instructions = (new File("input.txt")).readLines()

int result = 0

for (String line : instructions) {
	dir = line.charAt(0);
	count = Integer.valueOf(line.substring(1));
	
	pos = dir == 'L' ? pos + (100-count) : pos + count;
	
	pos = pos % 100;
	
	if (pos == 0) ++result;
}

System.out.println(result)
