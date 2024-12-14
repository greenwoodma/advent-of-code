
width = 101
height = 103

seconds = 100

int qw = width/2
int qh = height/2

counts = [0, 0, 0, 0]

new File("input.txt").eachLine { line ->
	(x,y,dx,dy) = line.split("[^0-9-]+").tail()*.toLong()
	
	X = Math.floorMod(x + dx*seconds,width)
	Y = Math.floorMod(y + dy*seconds,height)

	if (X < qw) {
		if (Y < qh) ++counts[0]
		else if (Y > qh) ++counts[2]
	} else if (X > qw) {
		if (Y < qh) ++counts[1]
		else if (Y > qh) ++counts[3]
	}
	
}

total = counts[0]*counts[1]*counts[2]*counts[3];

println total

