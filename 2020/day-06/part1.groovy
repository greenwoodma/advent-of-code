import java.io.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

String line = fin.readLine();

int sum = 0;

Set group = new HashSet();

while (line != null) {
	if (line.equals("")) {
		sum += group.size();
		group.clear();
	}
	else {
		group.addAll(line.toCharArray());
		//System.out.println(group);
	}

	line = fin.readLine();
}

sum += group.size();

System.out.println(sum);
