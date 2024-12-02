def sample = [3,8,9,1,2,5,4,6,7];
def myinput = [5,8,9,1,7,4,2,6,3];

def input = myinput;

System.out.println(input);

for (int i = 0 ; i < 100 ; i++) {
	int current = input.head();

	input = input.tail();

	int nextCup = current;

	threeCups = new ArrayList<Integer>(input.subList(0,3));

	input.removeAll(threeCups);

	while (input.indexOf(nextCup) == -1) {
		nextCup = nextCup -1;

		if (nextCup == 0) nextCup = 9;
	}

	input.add(current);
	
	nextCup = input.indexOf(nextCup);

	input.addAll(nextCup+1,threeCups);
	

	System.out.println(input);

	
}
