def lines = (new File("input.txt")).readLines()

Map map = new HashMap();
def directions = lines[0];

for (int d = 2 ; d <  lines.size() ; ++d) {
	//System.out.println(lines[d]);
	
	def i = lines[d].split("[\\s,=()]+");


	map.put(i[0],[i[1],i[2]]);
}

long steps = 0;

String next = "AAA";

while (!next.equals("ZZZ")) {

	int offset = steps % directions.length();
	
	//System.out.println(offset);
	
	int index = directions.charAt(offset) == 'R' ? 1 : 0;
	
	next = map.get(next)[index];

	++steps;
}

System.out.println(steps);
