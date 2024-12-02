def sample = [3,8,9,1,2,5,4,6,7];
def myinput = [5,8,9,1,7,4,2,6,3];

input = myinput;

System.out.println(input);

for (int i = 0 ; i < input.size() ; ++i) {
	input.set(i, new Cup(input.get(i)));
}

while (input.size() < 1000000) {
	input.add(new Cup(input.size()+1));
}

for (int i = 0 ; i < input.size()-1 ; ++i) {
	input.get(i).next = input.get(i+1)
}

input.get(input.size()-1).next = input.get(0);

//System.out.println(input);

Cup current = input.get(0);

//display();

for (int i = 0 ; i < (1000000*10) ; i++) {
	List<Cup> nextThree = new ArrayList<Cup>();

	nextThree.add(current.next);
	nextThree.add(current.next.next);
	nextThree.add(current.next.next.next);

	//System.out.println(nextThree);

	destination = current.label == 1 ? input.size() : current.label - 1;

	while (nextThree.contains(new Cup(destination))) {
		destination = destination -1;

		if (destination == 0) destination = input.size();
	}

	//System.out.println(current.label+"|"+destination);

	if (destination < 10) {	
		destination = input.get(input.indexOf(new Cup(destination)));
	}
	else {
		destination = input.get(destination-1);
	}

	current.next = nextThree.get(2).next;

	current = current.next;	

	//System.out.println(current);

	nextThree.get(2).next = destination.next;
	destination.next = nextThree.get(0);

	//System.out.println(nextThree.get(2));
	//System.out.println(destination);

	//System.out.println(nextThree);

	//display();
	//System.out.println(input.toString()+"\n\n");

	if (i%1000==0) System.out.println(i);
	
}

cup1 = input.get(input.indexOf(new Cup(1)));

System.out.println(cup1);
System.out.println(cup1.next);
System.out.println(cup1.next.next);


public void display() {

cup = input.get(input.indexOf(new Cup(1)));
String result = "";
for (int i = 0 ; i < input.size() ; ++i) {
	result = result + cup.label;
	cup = cup.next;
}
System.out.println(result);

}

class Cup implements Comparable<Cup>{
	Integer label;

	Cup next;

	public Cup(label) {
		this.label = label;
	}

	public int hashCode() {
		return label;
	}

	public boolean equals(Object o) {
		return label.equals(o.label);
	}

	public int compareTo(Cup o) {
		return label.compareTo(o.label);
	}

	public String toString() {
		return label + " --> " + next.label;
	}

}
