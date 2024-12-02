import java.io.*

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

List<Integer> numbers = new ArrayList<Integer>();

String line = fin.readLine();

while(line != null) {
	numbers.add(Integer.valueOf(line));

	line = fin.readLine();	
}

for (i = 0 ; i < numbers.size()-1 ; ++i) {
	for (j = i+1 ; j < numbers.size() ; ++j) {
		if (numbers.get(i)+numbers.get(j) == 2020) {
			System.out.println(numbers.get(i));
			System.out.println(numbers.get(j));
			System.out.println(numbers.get(i)*numbers.get(j));
		}
	}
}
