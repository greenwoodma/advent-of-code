def map = (new File("input.txt")).readLines()[0]*.toInteger()

long checksum = 0;

long pointer = 0;

// remove the last item which is the length for numFiles-1
long lastID = (map.size()-1)/2
int lastLength = map.removeLast()
	
// remove the last item which is empty space which we can ignore now it's at the end
map.removeLast()

//while (map.size() > 0) {
for (int file = 0 ; file < map.size() ; ++file) {
	
	// write out the full next block
	for (int i = 0 ; i < map[file] ; ++i) {
		checksum += (file/2)*pointer
		++pointer
	}
	
	// at the end of the block we then need to find out how much free space
	// we need to fill
	space = map[++file]
	
	for (int i = 0 ; i < space ; ++i) {
		if (lastLength == 0) {
			//update the last block that we are moving into the space
			lastID = (map.size()-1)/2
			lastLength = map.removeLast()
			map.removeLast()
		}
		
		checksum += lastID*pointer
		--lastLength
		++pointer
	}
}

while (lastLength > 0) {
	checksum += lastID*pointer
	--lastLength
	++pointer
}

println checksum
