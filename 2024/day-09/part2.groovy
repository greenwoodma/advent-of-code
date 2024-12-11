def map = (((new File("input.txt")).readLines()[0])+"0")*.toInteger()

Map<Integer,List<FileChunk>> gaps = new HashMap<Integer,List<FileChunk>>();

FileChunk first = null
FileChunk last = null;

List<FileChunk> origOrder = new ArrayList<FileChunk>()

for (int p = 0 ; p < map.size()-1 ; p = p+2) {

	// the start of this chunk is either 0 or the end of the previous chunk
	long start = (last == null ? 0 : last.start+last.length+last.gap); 
	
	// the file ID is simply p/2
	FileChunk current = new FileChunk(start,map[p],(int)(p/2),map[p+1],last)
		
	// so we now where the list starts store this as the first chunk
	// if we don't have a first chunk as yet
	if (first == null) first = current;
	
	//  set the next chunk on what was the end of the list to this chunk
	if (last != null) last.next = current
	
	origOrder.add(current)
	
	// this chunk is now the end of the list
	last = current;
}

while (origOrder.size() > 0) {
	// get the file we want to try and move
	last = origOrder.removeLast();
	
	// find the first gap, if there is one, before it
	// that's big enough
	FileChunk gap = null;
	FileChunk check = first;
	while (true) {
		if (check.equals(last)) break;
		
		if (check.gap >= last.length) {
			gap = check;
			break;
		}
		
		check = check.next
	}
	
	if (gap == null) {
		// no gap so move on to the next file
		continue;
	}
	
	FileChunk next = last.previous
	
	// start by extending the empty space in next by the length of the
	// block we are moving plus it's free space
	next.gap = next.gap+last.length+last.gap
	next.next = last.next
	
	// set the empty space in the gap to zero and have it point to the
	// block we are moving
	gapLength = gap.gap
	gap.gap = 0;
	
	gapNext = gap.next;
	gap.next = last
	
	gapNext.previous = last
	last.previous = gap
	
	last.next = gapNext
	
	// calculate the new start position for the block we are moving and
	// it's start position, update it's next pointer to whatever the gap
	// was followed by
	last.start = gap.start+gap.length
	last.gap = gapLength-last.length
}

println checksum(first)

long checksum(next) {
	long checksum = 0;

	while (true) {
		for (int i = 0 ; i < next.length ; ++i) {
			checksum += next.id * (next.start+i)
		}
		
		next = next.next;
		if (next == null) break;
	}
	
	return checksum
}

class FileChunk {

	long start;
	int length,id,gap;
	FileChunk previous;
	FileChunk next = null;

	public FileChunk(long start, int length, int id, int gap, FileChunk previous) {
		this.start = start;
		this.length = length;
		this.id = id;
		this.gap = gap;
		this.previous = previous;
	}
    
    public String toString() {
    		return id+":"+start+"|"+length+"|"+gap
    }
}
