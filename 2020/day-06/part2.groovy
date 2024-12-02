import java.io.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

String line = fin.readLine();

int sum = 0;

Set group = null;

while (line != null) {
	if (line.equals("")) {
		sum += group.size();
		group = null;
		
	}
	else {
		if (group == null) {
			group = new HashSet();			
			group.addAll(line.toCharArray());
		}
		else {
			group.retainAll(line.toCharArray());
		}
	}

	line = fin.readLine();
}

sum += group.size();

System.out.println(sum);
