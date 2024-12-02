def lines = (new File("input.txt")).readLines()

Map map = new HashMap();
def directions = lines[0];

List start = new ArrayList();

for (int d = 2 ; d <  lines.size() ; ++d) {
	
	def i = lines[d].split("[\\s,=()]+");


	map.put(i[0],[i[1],i[2]]);
	
	if (i[0].endsWith("A")) start.add(i[0]);
}

List<Long> lengths = new ArrayList();

for (String next : start) {

	long steps = 0;
	while (!next.endsWith("Z")) {

		int offset = steps % directions.length();
		
		int index = directions.charAt(offset) == 'R' ? 1 : 0;
		
		next = map.get(next)[index];

		++steps;
	}
	
	lengths.add(steps);

}

System.out.println(lcm(lengths));

// the maths bits were "borrowed" from https://stackoverflow.com/a/4202114
// to avoid me having to remember how to find LCM from scratch


private static long gcd(long a, long b)
{
    while (b > 0)
    {
        long temp = b;
        b = a % b; // % is remainder
        a = temp;
    }
    return a;
}

private static long lcm(long a, long b)
{
    return a * (b / gcd(a, b));
}

private static long lcm(List input)
{
    long result = input[0];
    for(int i = 1; i < input.size(); i++) result = lcm(result, input[i]);
    return result;
}
