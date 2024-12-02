def sample1 = [0,3,6];
def myInput = [1,0,16,5,17,4];

def input = myInput;

Map<Integer,List<Integer>> lastSpoken = new HashMap<Integer,List<Integer>>();

for (int i = 0 ; i < input.size() ; ++i) {
	List<Integer> positions = new ArrayList<Integer>();
	positions.add(i+1);
	lastSpoken.put(input.get(i), positions);
}

int previous = input.get(input.size()-1);

for (int i = input.size()+1 ; i <= 2020 ; ++i) {
	List<Integer> positions = lastSpoken.getOrDefault(previous, new ArrayList<Integer>());

	int current = 0;

	if (positions.size()>1) {
		current = positions.get(0)-positions.get(1);
	}
	
	positions = lastSpoken.getOrDefault(current, new ArrayList<Integer>());

	positions.add(0,i)

	if (positions.size() > 2)
		positions.removeRange(2,positions.size());

	lastSpoken.put(current,positions);

	//System.out.println(i+"="+current+"\t"+previous+":"+lastSpoken);

	previous = current;

	System.out.println(i);
}

System.out.println("***"+previous)
