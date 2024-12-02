


Map<Position,Boolean> grid = new HashMap<Position,Boolean>();

load:{
	int y = 0;
	new File("input.txt").eachLine {line ->
		for (int x = 0 ; x < line.length() ; ++x) {
			Boolean active = (line.charAt(x) == '#');

			grid.put(new Position(x,y,0,0),active);
		}

		++y;
	}
}

long cycle = 1;

int ix = 8;
int iy = 8;
int iz = 1;
int iw = 1;


//System.out.println(grid);

//System.out.println(isActive(0,0,0,grid));

//System.out.println(countActive(0,0,0,grid));


private int countActive(long x, long y, long z, long w, Map<Position,Boolean> grid) {
	
	int count = 0;

	for (long x1 = x-1 ; x1 <= x+1 ; ++x1) {
		for (long y1 = y-1 ; y1 <= y+1 ; ++y1) {
			for (long z1 = z-1 ; z1 <= z+1 ; ++z1) {
				for (long w1 = w-1 ; w1 <= w+1 ; ++w1) {
					if (x1==x && y1==y && z1==z && w1==w) continue;

					if (isActive(x1,y1,z1,w1,grid)) ++count;
				}
			}
		}
	}

	return count;
}

while (cycle <= 6) {
	Map<Position,Boolean> newGrid = new HashMap<Position,Boolean>();

	for (int x = (-1 * cycle) ; x <= ix + cycle ; ++x) {
		for (int y = (-1 * cycle) ; y <= iy + cycle ; ++y) {
			for (int z = (-1 * cycle) ; z <= iz + cycle ; ++z) {
				for (int w = (-1 * cycle) ; w <= iw + cycle ; ++w) {
					Boolean previous = isActive(x,y,z,w,grid);
					int surrounding = countActive(x,y,z,w,grid);

					if (previous && (surrounding != 2 && surrounding != 3))
						previous = false;
					else if (!previous && surrounding == 3)
						previous = true;				
				
					newGrid.put(new Position(x,y,z,w),previous);
				}
				
			}
		}
	}

	++cycle;

	grid = newGrid;

	//System.out.println(grid);
}

long totalActive = 0;

for (Boolean state : grid.values()) {
	if (state) ++totalActive;
}

System.out.println(totalActive);




private Boolean isActive(long x, long y, long z, long w, Map<Position,Boolean> grid) {
	return grid.getOrDefault(new Position(x,y,z,w),false);	
}

class Position {
	long x,y,z,w;

	public Position(long x, long y, long z, long w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (x ^ (x >>> 32));
		result = prime * result + (int) (y ^ (y >>> 32));
		result = prime * result + (int) (z ^ (z >>> 32));
		result = prime * result + (int) (w ^ (w >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		if (w != other.w)
			return false;
		return true;
	}

	public String toString() {
		return "x="+x+",y="+y+",z="+z+",w="+w;
	}
}
