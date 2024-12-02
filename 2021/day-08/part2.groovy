long answer = 0;

new File("input.txt").eachLine { line ->

	String[] decoded = new String[10];

	String[] data = line.split("\\s*\\|\\s*");

	codes = data[0].split("\\s+").collect { it -> char[] letters = it.toCharArray(); Arrays.sort(letters); return new String(letters) }
	
	System.out.println("\n\n"+codes);

	decoded[1] = codes.find { it.length() == 2 }
	codes.remove(decoded[1]);
	
	decoded[4] = codes.find { it.length() == 4 }
	codes.remove(decoded[4]);
	
	decoded[7] = codes.find { it.length() == 3 }
	codes.remove(decoded[7]);
	
	decoded[8] = codes.find { it.length() == 7 }
	codes.remove(decoded[8]);

	decoded[3] = codes.find { modified = it; decoded[7].toCharArray().each { c -> modified = modified.minus(c) }; modified.length() == 2 }
	codes.remove(decoded[3]);

	decoded[6] = codes.find { modified = it; decoded[1].toCharArray().each { c -> modified = modified.minus(c) }; modified.length() == 5 }
	codes.remove(decoded[6]);

	decoded[5] = codes.find { modified = it; decoded[6].toCharArray().each { c -> modified = modified.minus(c) }; modified.length() == 0 }
	codes.remove(decoded[5]);
		
	decoded[2] = codes.find { it.length() == 5 }
	codes.remove(decoded[2]);
	
	decoded[9] = codes.find { modified = it; decoded[3].toCharArray().each { c -> modified = modified.minus(c) }; modified.length() == 1 }
	codes.remove(decoded[9]);
	
	decoded[0] = codes[0];
	codes.remove(decoded[0]);

	
	//top = code7;
	
	/*System.out.println(code1+"\t"+code7);
	
	code1.toCharArray().each { top = top.minus(it) };
	System.out.println(top);*/
	
	
	//System.out.println(codes);
	System.out.println(decoded);

	output = data[1].split("\\s+").collect { it -> char[] letters = it.toCharArray(); Arrays.sort(letters); return new String(letters) };
	
	System.out.println(output);
	
	String code = "";
	
	output.each { code += decoded.findIndexOf{d -> d.equals(it) } }
	
	System.out.println(code);
	
	answer += Long.valueOf(code);
}

System.out.println("\n\nanswer: " + answer);

