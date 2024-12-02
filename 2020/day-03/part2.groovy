import java.io.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

List<String> rows = new ArrayList<String>();




String line = fin.readLine();

int width = line.length();

while (line != null) {
	rows.add(line);
	line = fin.readLine();
}

System.out.println(countTrees(rows, width, 1, 1));
System.out.println(countTrees(rows, width, 3, 1));
System.out.println(countTrees(rows, width, 5, 1));
System.out.println(countTrees(rows, width, 7, 1));
System.out.println(countTrees(rows, width, 1, 2));

long total = countTrees(rows, width, 1, 1) * countTrees(rows, width, 3, 1) * countTrees(rows, width, 5, 1) * countTrees(rows, width, 7, 1) * countTrees(rows, width, 1, 2);

System.out.println(total);

private long countTrees(List<String> rows, int width, int right, int down) {
	int column = 0;

	long trees = 0;

	for (int row = 0 ; row < rows.size() ; row += down) {
		char cell = rows.get(row).charAt(column);

		if (cell == '#') ++trees;

		//System.out.println(row+"/"+column+"/"+cell);

		column = column + right;
		if (column >= width) column = column - width;
	}

	return trees;
}
