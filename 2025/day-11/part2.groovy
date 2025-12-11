import groovy.transform.Memoized;

links = [:]

(new File("input.txt")).eachLine{line ->
	data = line.split("[\\s:]+") as List
	
	from = data.remove(0)
	links.put(from,data)
}

// of course part 2 was never going to solve with the
// code from part 1 the search space is just too big.
// The point though is that we don't need the actual
// paths just the count so

// this is a simple recursive function to count
// but not enumerate the paths
@Memoized
def countPaths(node,target) {
	
	// if the current node is the target then
	// we have found a path so return 1
	if (node.equals(target)) return 1;
	
	// sum up the possible paths to the target
	// from following each of the next possible
	// nodes in the network
	long count = 0;	
	for (n : links.get(node)) {
		count += countPaths(n,target)
	}
	
	return count
}

// now we can use that to find the three parts of the
// path and then multiply them together. Note that this
// assumes that there are no valid paths which go to the
// dac and then the fft but that does seem to be the case
// at least in my input file
total = countPaths("svr","fft")
total = total * countPaths("fft","dac")
total = total * countPaths("dac","out")

println total


