// 276112055792640 too high

product = { result, number ->
	result * number
}

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

pid = 0;

input.toCharArray().each { decoded += mapping.get(it as String) }

System.out.println(decoded)

versionSum = 0;

(end, value) = parse(decoded,0);



System.out.println("answer = " + value)



def parse(String input, int start) {

	orig = start;
	id = pid++;
	int version = Integer.parseInt(input.substring(start,start+3),2);
	int type = Integer.parseInt(input.substring(start+3,start+6),2);
	
	System.out.println("\nPID: " + id);
	System.out.println("Start: " + orig);
	System.out.println("Version: " + version);
	System.out.println("Type: " + type);
	
	versionSum += version;

	start += 6;
	
	if (type == 4) {

		
		String block = input.substring(start,start+5);
		start += 5;
		
		literal = "";
		
		while (true) {
			literal += block.substring(1);
			
			if (block.charAt(0) == '0') break;
			
			block = input.substring(start,start+5);
			start += 5;
		}
		
		literal = Long.parseLong(literal,2)
		
		System.out.println("Literal: " + literal);
		
		return [start,literal];
	} else {
	
	// can't be untyped otherwise it's a global variable and
	// get's re-used in recursive calls completely changing the result
	List subpackets = new ArrayList();
	
	int end = 0;
	if (input.charAt(start) == '0') {
		System.out.println("total length");
		
		int length = Integer.parseInt(input.substring(start+1,start+16),2);
		start +=16;
		System.out.println(length);
		
		end = start+length;
		
		int substart = start;
		
		while (substart < end) {
			(substart, spv) = parse(input,substart);
			subpackets.add(spv);
		}
		
		end = start+length;
		//return [start+length,0];
		
	} else {
		
		
		System.out.println("number packets");
		int num = Integer.parseInt(input.substring(start+1,start+12),2);
		start += 12;
		System.out.println(num);

		for (int p = 0 ; p < num ; ++p) {
			(end, spv) = parse(input,start);
			subpackets.add(spv)
			start = end;
		}
	}
	
	System.out.println(id+"|"+orig+"|"+type+": "+subpackets);
	
	switch (type) {
		case 0:
			return [end,subpackets.sum()]
		case 1:
			return [end,subpackets.inject(product)]
		case 2:
			return [end,subpackets.min()]
		case 3:
			return [end,subpackets.max()]
		case 5:
			return [end, subpackets[0] > subpackets[1] ? 1L : 0L]
		case 6:
			return [end, subpackets[0] < subpackets[1] ? 1L : 0L]
		case 7:
			return [end, subpackets[0].equals(subpackets[1]) ? 1L : 0L]
	}
	
	throw new Exception("invalid type");
	}
}
