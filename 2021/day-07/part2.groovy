input = (new File("input.txt")).collect()[0].split(",").collect{ it as Integer };

floor = (int)Math.floor(input.sum()/input.size());
ceiling = (int)Math.ceil(input.sum()/input.size());

public calcCost(int position) {
	long cost = 0;	

	input.each {
		steps = Math.abs(position-it)

		
		for (long c = 1 ; c <= steps ; ++c) {
			cost += c;
		}
	}		
	
	return cost;	
}

floorCost = calcCost(floor)
ceilingCost = calcCost(ceiling)

System.out.println(floor+": "+ floorCost);
System.out.println(ceiling+": "+ceilingCost);

System.out.println("\n\nanswer: " + Math.min(floorCost,ceilingCost));
