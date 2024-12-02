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
             for (k = j+1 ; k < numbers.size() ; ++k) {
		if (numbers.get(i)+numbers.get(j)+numbers.get(k) == 2020) {
			System.out.println(numbers.get(i));
			System.out.println(numbers.get(j));
			System.out.println(numbers.get(k));
			System.out.println(numbers.get(i)*numbers.get(j)*numbers.get(k));
		}
		}
	}
}
