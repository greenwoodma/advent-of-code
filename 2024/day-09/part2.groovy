def map = (((new File("input.txt")).readLines()[0])+"0")*.toInteger()

// 6361138221734 is too low

Map<Integer,List<FileChunk>> gaps = new HashMap<Integer,List<FileChunk>>();

FileChunk first = null
FileChunk last = null;

List<FileChunk> origOrder = new ArrayList<FileChunk>()
List<FileChunk> allChunks = new ArrayList<FileChunk>()

for (int p = 0 ; p < map.size()-1 ; p = p+2) {

	// the start of this chunk is either 0 or the end of the previous chunk
	long start = (last == null ? 0 : last.start+last.length+last.gap); 
	
	// the file ID is simply p/2
	FileChunk current = new FileChunk(start,map[p],(int)(p/2),map[p+1],last)
	
	if (map[p+1] > 0) {
		List<FileChunk> gapList = gaps.getOrDefault(map[p+1], new ArrayList<FileChunk>())
		gapList.add(current)
		gaps.put(map[p+1],gapList)
	}
		
	// so we now where the list starts store this as the first chunk
	// if we don't have a first chunk as yet
	if (first == null) first = current;
	
	//  set the next chunk on what was the end of the list to this chunk
	if (last != null) last.next = current
	
	origOrder.add(current)
	allChunks.add(current)
	
	// this chunk is now the end of the list
	last = current;
}

println count(first)

//while (!last.equals(first)) {
while (origOrder.size() > 0) {
	last = origOrder.removeLast();

	//println "\n"+last
	//println gaps
	
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
	
	/*
	for (int i = last.length ; i <= 9 ; ++i) {
		List<FileChunk>  gapList = gaps.get(i);
		//println gapList
		if (gapList != null && gapList.size() > 0) {
			if (gapList[0].start < last.start && (gap == null || gapList[0].start < gap.start))
				gap = gapList[0]
		}
	}*/
	
	
	
	if (gap == null) {
		//println "want to move "+last.id+" but no gaps of size "+last.length
		// we've failed to find a gap big enough so leave this one where it is
		last = last.previous;
		continue;
	}
	
	//println "moving into "+gap

	// before we start moving files around make sure we know where we are going
	// next with the outer loop
	FileChunk next = last.previous
	
	// remove the gaps we are going to update
	//if (last.gap != 0) gaps.get(last.gap).remove(last)
	//if (next.gap != 0) gaps.get(next.gap).remove(next)
	
	// start by extending the empty space in next by the length of the
	// block we are moving plus it's free space and updating which
	// gap list it's now in
	next.gap = next.gap+last.length+last.gap
	next.next = last.next
	/*List<FileChunk>  gapList = gaps.getOrDefault(next.gap,new ArrayList<FileChunk>())
	gapList.add(next);
	Collections.sort(gapList)
	gaps.put(next.gap,gapList)*/
	
	// set the empty space in the gap to zero and have it point to the
	// block we are moving, and then update the gap list
	//gaps.get(gap.gap).remove(gap)
	gapLength = gap.gap
	gap.gap = 0;
	
	gapNext = gap.next;
	gap.next = last
	
	gapNext.previous = last
	last.previous = gap
	
	// the next pointer in next should now point to the next pointer
	// of the block we are going to move to cut this one out
	
	//next.next = last.next
	last.next = gapNext
	
	// calculate the new start position for the block we are moving and
	// it's start position, update it's next pointer to whatever the gap
	// was followed by, and update the gap lists
	//println "gap is now "+gap
	//println "last.start will be "+gap.start+"+"+gap.length+"="+(gap.start+gap.length)
	last.start = gap.start+gap.length
	last.gap = gapLength-last.length
	//println "chunk is now at "+last
	
	/*if (last.gap >0) {
		gapList = gaps.getOrDefault(last.gap,new ArrayList<FileChunk>())
		gapList.add(last);
		Collections.sort(gapList)
		gaps.put(last.gap,gapList)
	}*/
	
	
	
	// now we've moved everything move on to the next chunk to check
	//last = next
	
	//display(first)
}

//println gaps

println count(first)

println checksum(first)

FileChunk c = first;
while (true) {
	allChunks.remove(c);
	
	c = c.next
	
	if (c == null) break;
}

println allChunks

void display(next) {
	while (true) {
		println next
		
		next = next.next
		if (next == null) break;
	}
}

long count(next) {
	long total = 0;
	
	while(true) {
		++total;
		
		next = next.next;
		if (next == null) break;
	}
	
	return total;
}

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

class FileChunk implements Comparable<FileChunk> {

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
	
	@Override
    public int compareTo(FileChunk other) {
    		return this.start - other.start;
    }
    
    public String toString() {
    		return id+":"+start+"|"+length+"|"+gap
    }
}
