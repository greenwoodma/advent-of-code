import java.io.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

List<String> rows = new ArrayList<String>();

int column = 0;

int right = 3;
int down = 1;

String line = fin.readLine();

int width = line.length();

while (line != null) {
	rows.add(line);
	line = fin.readLine();
}

System.out.println(width+"/"+rows.size());

int trees = 0;

for (int row = 0 ; row < rows.size() ; row += down) {
	char cell = rows.get(row).charAt(column);

	if (cell == '#') ++trees;

	System.out.println(row+"/"+column+"/"+cell);

	column = column + right;
	if (column >= width) column = column - width;
}

System.out.println(trees);
