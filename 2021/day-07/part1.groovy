def input = (new File("input.txt")).collect()[0].split(",").collect{ it as Integer }.sort();

midNumber = (int)(input.size()/2)
median = input.size() %2 != 0 ? input[midNumber] : (input[midNumber] + input[midNumber-1])/2 

long cost = 0;

input.each {
	cost += Math.abs(median-it);
}



System.out.println(median);
System.out.println(cost);
