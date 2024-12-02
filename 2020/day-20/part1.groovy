import java.io.*;

BufferedReader fin = new BufferedReader(new FileReader("input.txt"));

List<Tile> tiles = new ArrayList<Tile>();

Map<String,Integer> counts = new HashMap<String,Integer>();

while (true) {
	String id = fin.readLine();

	id = id.substring(5,id.length()-1);

	List<String> rows = new ArrayList<String>();

	String row = fin.readLine();

	while (row != null && !row.equals("")) {
		rows.add(row);
		row = fin.readLine();
	}

	tiles.add(new Tile(id,rows));

	if (row == null) break;
}

for (int i = 0 ; i < tiles.size()-1 ; ++i) {
	Tile ti = tiles.get(i);
	for (int j = i + 1 ; j < tiles.size() ; ++j) {
		Tile tj = tiles.get(j);

		boolean align = ti.anyAlign(tj);

		if (align) {
			counts.put(ti.id,counts.getOrDefault(ti.id,0)+1);
			counts.put(tj.id,counts.getOrDefault(tj.id,0)+1);
		}

		//System.out.println(ti.id+"|"+tj.id+"|"+align);
	}
}

System.out.println(counts);

long total = 1;

for (Map.Entry<String,Integer> tile : counts.entrySet()) {
	if (tile.getValue() <= 2) {
		System.out.println(tile.getKey());
		total = total * Long.valueOf(tile.getKey());
	}
}

System.out.println(total);



class Tile {
	String id;

	String top, left, bottom, right;

	public Tile(String id, List<String> data) {
		this.id = id;

		top = data.get(0);
		bottom = data.get(data.size()-1);

		left = "";
		right = "";

		for (String row : data) {
			left = left + row.charAt(0);
			right = right + row.charAt(row.length()-1);
		}
	}

	public boolean align(Tile other) {
		if (top.equals(other.bottom)) return true;

		if (bottom.equals(other.top)) return true;

		if (left.equals(other.right)) return true;

		if (right.equals(other.left)) return true;

		return false;
	}

	public void flipHorizontal() {
		String temp = left;
		left = right;
		right = temp;

		top = new StringBuilder(top).reverse().toString();
		bottom = new StringBuilder(bottom).reverse().toString();
	}

	public void flipVertical() {
		String temp = top;
		top = bottom;
		bottom = temp;

		left = new StringBuilder(left).reverse().toString();
		right = new StringBuilder(right).reverse().toString();
	}

	public void rotate() {
		String temp = top;

		top = new StringBuilder(left).reverse().toString();
		left = bottom;
		bottom = new StringBuilder(right).reverse().toString();
		right = temp;
	}

	public boolean anyAlign(Tile other) {
		if (align(other)) return true;

		rotate();
		if (align(other)) return true;

		rotate();
		if (align(other)) return true;

		rotate();
		if (align(other)) return true;

		flipHorizontal();
		if (align(other)) return true;

		rotate();
		if (align(other)) return true;

		rotate();
		if (align(other)) return true;

		rotate();
		if (align(other)) return true;

		flipVertical();
		if (align(other)) return true;

		rotate();
		if (align(other)) return true;

		rotate();
		if (align(other)) return true;

		rotate();
		if (align(other)) return true;

		return false;
	}

	public String toString() {
		return id+":"+top+"|"+left+"|"+bottom+"|"+right;
	}
}
