String input = new File("input.txt").collect()[0];

mapping = new HashMap();
mapping.put("0", "0000");
mapping.put("1", "0001");
mapping.put("2", "0010");
mapping.put("3", "0011");
mapping.put("4", "0100");
mapping.put("5", "0101");
mapping.put("6", "0110");
mapping.put("7", "0111");
mapping.put("8", "1000");
mapping.put("9", "1001");
mapping.put("A", "1010");
mapping.put("B", "1011");
mapping.put("C", "1100");
mapping.put("D", "1101");
mapping.put("E", "1110");
mapping.put("F", "1111");

System.out.println(input)

String decoded = "";

input.toCharArray().each { decoded += mapping.get(it as String) }

System.out.println(decoded)

versionSum = 0;

System.out.println(parse(decoded,0));



System.out.println("answer = " + versionSum)


public int parse(String input, int start) {
	int version = Integer.parseInt(input.substring(start,start+3),2);
	int type = Integer.parseInt(input.substring(start+3,start+6),2);
	
	System.out.println("\nVersion: " + version);
	System.out.println("Type: " + type);
	
	versionSum += version;

	start += 6;
	
	if (type == 4) {

		
		String block = input.substring(start,start+5);
		start += 5;
		
		String literal = "";
		
		while (true) {
			literal += block.substring(1);
			
			if (block.charAt(0) == '0') break;
			
			block = input.substring(start,start+5);
			start += 5;
		}
		
		System.out.println("Literal: " + Long.parseLong(literal,2));
		
		return start;
	}
	
	if (input.charAt(start) == '0') {
		System.out.println("total length");
		
		int length = Integer.parseInt(input.substring(start+1,start+16),2);
		start +=16;
		System.out.println(length);
		
		int end = start+length;
		
		int substart = start;
		
		while (substart < end) {
			substart = parse(input,substart);
		}
		
		return start+length;
		
	}
	
	
	System.out.println("number packets");
	int num = Integer.parseInt(input.substring(start+1,start+12),2);
	start += 12;
	System.out.println(num);
	int end = 0;
	for (int p = 0 ; p < num ; ++p) {
		end = parse(input,start);
		start = end;
	}
	
	return end;
}
